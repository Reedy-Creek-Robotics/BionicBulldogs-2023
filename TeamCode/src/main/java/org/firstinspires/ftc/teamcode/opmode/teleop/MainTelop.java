package org.firstinspires.ftc.teamcode.opmode.teleop;

import android.app.ApplicationErrorReport;
import android.util.Log;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.BatteryChecker;

import org.firstinspires.ftc.teamcode.modules.robot.*;
import org.firstinspires.ftc.teamcode.modules.drive.XDrive;
import org.firstinspires.ftc.teamcode.opmode.config.*;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;

@TeleOp
public class MainTelop extends BaseTeleOp{
    Claw claw;
    Slides slides;
    XDrive xDrive;
    Intake intake;
    HangingSlides hangingSlides;
    DroneLauncher droneLauncher;
    VoltageSensor voltageSensor;

    float driveSpeed = 1;
    float intakeSpeed = 0.8f;
    static long flickerDelay = 350;

    public enum ScoringState{
        Up,
        Down,
        Score
    }
    ScoringState scoreState = ScoringState.Down;

    int[] slidesPosition = {-1230, -1800, -2500};
    int slidesPositionIndex;
    public void init(){
        super.init();
        claw = new Claw(new ClawConfig(hardwareMap));
        slides = new Slides(new SlideConfig(hardwareMap));
        xDrive = new XDrive(new XDriveConfig(hardwareMap));
        intake = new Intake(new IntakeConfig(hardwareMap));
        droneLauncher = new DroneLauncher(new DroneLauncherConfig(hardwareMap));
        hangingSlides = new HangingSlides(new HangingSlidesConfig(hardwareMap));
        voltageSensor = hardwareMap.voltageSensor.iterator().next();
        //telemetry.addData("battery voltage", voltageSensor.getVoltage());
        //slides.telem(telemetry);
        //xDrive.telem(telemetry);
        //xDrive.debugTelemetry(telemetry);
        if(SampleMecanumDrive.posEstimate != null){
            xDrive.setPosEstimate(SampleMecanumDrive.posEstimate);
        }else{
            telemetry.addLine("SampleMecaniumDrive::posEstimate is null, using deafult heading");
        }
        telemetry.update();
    }
    public void start(){
        super.start();
        claw.initServos();
        slides.resetRotator();
        xDrive.start();
        droneLauncher.reset();
    }
    public void loop(){
        copyGamepads();

        //drive
        float forward = -gamepad1.left_stick_y;
        float right = gamepad1.left_stick_x;
        float rotate = -gamepad1.right_stick_x;
        xDrive.driveAccel(forward * driveSpeed, right * driveSpeed, rotate * driveSpeed);

        //intake
        if(gamepadEx1.rightBumper()){
            //Log.d("INTAKE", "Right Bumper Pressed");
            reverseIntake();
        }

        if(gamepadEx1.leftBumper()){
            //Log.d("INTAKE", "Left Bumper Pressed");
            updateIntake();
        }

        if(gamepadEx1.options()){
            droneLauncher.launch();
        }
        //telemetry.addData("Intake", intake.getState());

        if(gamepadEx1.share()){
            xDrive.setPosEstimate(new Pose2d());
        }

        //slides
        if(gamepad1.dpad_up){
            slides.setPower(-1);
        }else if(gamepad1.dpad_down){
            slides.setPower(1);
        }else{
            slides.setPower(0);
        }
        if(gamepadEx1.dpadRight()){
            slides.gotoPosition(slidesPosition[slidesPositionIndex]);
        }
        if(gamepadEx1.dpadLeft()){
            slides.reset();
        }

        if(gamepadEx1.triangle()) {
            slides.toggleRotator();
        }

        //claw
        if(gamepadEx1.cross()){
            claw.toggleTop();
        }

        //Score
        if(gamepadEx1.touchpad()){
            toggleScorePosition();
        }

        if(gamepadEx1.ps()){
            score(2);
        }

        if(gamepadEx1.square()){
            score(1);
        }
        if(gamepadEx1.circle()){
            slidesPositionIndex++;
            if(slidesPositionIndex > 2){
                slidesPositionIndex = 0;
            }
        }

        //Hanging slides
        if(gamepadEx1.leftTriggerb()){
            hangingSlides.up();
        }
        if(gamepadEx1.rightTriggerb()){
            hangingSlides.hang();
        }

        telemetry.addData("battery voltage", voltageSensor.getVoltage());
        telemetry.addData("slidesPositionIndex", slidesPositionIndex + 1);
        claw.telem(telemetry);
        slides.telem(telemetry);
        //xDrive.telem(telemetry);
        //xDrive.debugTelemetry(telemetry);
        telemetry.update();
    }

    protected void updateIntake() {
        if(intake.getState() == Intake.IntakeState.Intake) {
            intake.stop();
            claw.closeTop();
        }
        else {
            intake.intake(intakeSpeed);
            claw.openTop();
        }
        /*
        switch(intake.getState()) {
            case Intake:
                Log.d("INTAKE", "Stopping Intake");
                intake.stop();
                break;
            case Stop:
                Log.d("INTAKE", "Starting Intake");
                intake.intake(intakeSpeed);
                break;
            default:
                Log.d("INTAKE", intake.getState().toString());
        }

         */

    }
    protected void reverseIntake(){
        if(intake.getState() == Intake.IntakeState.Outtake) {
            intake.stop();
            claw.closeTop();
        }
        else{
            intake.outtake(intakeSpeed);
        }
    }
    void toggleScorePosition(){
        if(scoreState == ScoringState.Down){
            xDrive.drive(0, 0, 0);
            claw.closeTop();
            slides.gotoPosition(slidesPosition[slidesPositionIndex]);
            sleep(500);
            slides.scoreRotator();
            scoreState = ScoringState.Up;
        }else{
            slides.resetRotator();
            sleep(250);
            slides.reset();
            claw.openTop();
            scoreState = ScoringState.Down;
        }
    }
    protected void score(int count) {
        xDrive.drive(0, 0, 0);
        for (int i = 0; i < count; i++) {
            flicker();
            if (i != count - 1) {
                sleep(flickerDelay);
            }
        }
    }
    public void flicker() {
        //flick the flicker on the claw
        claw.push();
        sleep(flickerDelay); //350
        claw.resetFlicker();
    }
}
