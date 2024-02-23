package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.modules.drive.XDrive;
import org.firstinspires.ftc.teamcode.modules.robot.Claw;
import org.firstinspires.ftc.teamcode.modules.robot.DroneLauncher;
import org.firstinspires.ftc.teamcode.modules.robot.HangingSlides;
import org.firstinspires.ftc.teamcode.modules.robot.Intake;
import org.firstinspires.ftc.teamcode.modules.robot.Slides;
import org.firstinspires.ftc.teamcode.opmode.config.ClawConfig;
import org.firstinspires.ftc.teamcode.opmode.config.DroneLauncherConfig;
import org.firstinspires.ftc.teamcode.opmode.config.HangingSlidesConfig;
import org.firstinspires.ftc.teamcode.opmode.config.IntakeConfig;
import org.firstinspires.ftc.teamcode.opmode.config.SlideConfig;
import org.firstinspires.ftc.teamcode.opmode.config.XDriveConfig;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;

@TeleOp(name = "\1MainTelopFR")
@Config
public class MainTelopFR extends BaseTeleOp{
    Claw claw;
    Slides slides;
    XDrive xDrive;
    Intake intake;
    HangingSlides hangingSlides;
    DroneLauncher droneLauncher;
    VoltageSensor voltageSensor;
    ColorSensor colorSensor;
    static public double targetRed = 45;
    static public double targetGreen = 95;
    static public double targetBlue = 210;
    int pixelCount = 0;
    ElapsedTime clawET;
    boolean closeClaw = false;
    float driveSpeed = 1;
    static public double intakeSpeed = 0.8f;

    public enum ScoringState{
        Up,
        Down,
        Score
    }
    boolean pixelCountIncremented = false;
    ScoringState scoreState = ScoringState.Down;

    int[] slidesPosition = {-1200, -1515, -1800, -2150, -2500};
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
        colorSensor = hardwareMap.colorSensor.iterator().next();
        //telemetry.addData("battery voltage", voltageSensor.getVoltage());
        //slides.telem(telemetry);
        //xDrive.telem(telemetry);
        //xDrive.debugTelemetry(telemetry);
        if(SampleMecanumDrive.posEstimate != null){
            //xDrive.setPosEstimate(SampleMecanumDrive.posEstimate);
        }else{
            telemetry.addLine("SampleMecaniumDrive::posEstimate is null, using deafult heading");
        }
        telemetry.update();
        clawET = new ElapsedTime();
    }
    public void start(){
        super.start();
        claw.initServos();
        slides.resetRotator();
        xDrive.start();
        droneLauncher.reset();
        //intake.initServos();
    }
    public void loop(){
        slides.updateSlidePower();
        copyGamepads();
        if(colorSensor.red() > targetRed && colorSensor.green() > targetGreen && colorSensor.blue() > targetBlue){
            gamepad1.setLedColor(1, 1, 1, 100);
            gamepad1.rumble(1, 1, 100);
            if(!pixelCountIncremented){
                pixelCountIncremented = true;
                pixelCount++;
            }
        }else if(pixelCountIncremented){
            pixelCountIncremented = false;
            if(pixelCount >= 2){
                closeClaw = true;
                clawET.reset();
                intake.stop();
            }
        }
        telemetry.addData("ColorRed  ",colorSensor.red());
        telemetry.addData("ColorGreen",colorSensor.green());
        telemetry.addData("ColorBlue ",colorSensor.blue());
        telemetry.addData("pixelCount", pixelCount);

            //drive
        float forward = -gamepad1.left_stick_y;
        float right = gamepad1.left_stick_x;
        float rotate = -gamepad1.right_stick_x;
        xDrive.driveAccelFR(forward * driveSpeed, right * driveSpeed, rotate * driveSpeed);

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
            slides.resetEncoder();
        }

        if(gamepadEx1.triangle()) {
            slidesPositionIndex = 0;
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
            claw.score(2);
        }

        if(gamepadEx1.square()){
            claw.score(1);
        }
        if(gamepadEx1.circle()){
            slidesPositionIndex++;
            if(slidesPositionIndex >= slidesPosition.length){
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

        if(gamepadEx2.cross()) {
            intake.grabStack();
        }
        if(gamepadEx2.circle()){
            intake.resetStackGrabber();
        }

        if(closeClaw){
            if(clawET.seconds() >= 0.2){
                claw.closeTop();
                slides.resetRotator();
                closeClaw = false;
            }
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
            pixelCount = 0;
        }
    }
}
