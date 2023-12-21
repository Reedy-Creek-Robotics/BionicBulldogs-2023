package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.checkerframework.checker.units.qual.C;
import org.firstinspires.ftc.teamcode.modules.robot.*;
import org.firstinspires.ftc.teamcode.modules.drive.XDrive;
import org.firstinspires.ftc.teamcode.opmode.config.*;

@TeleOp
public class MainTelop extends BaseTeleOp{
    Claw claw;
    Slides slides;
    XDrive xDrive;
    Intake intake;


    float driveSpeed = 1;
    float intakeSpeed = 1;
    boolean intakeOn = false;

    public void init(){
        super.init();
        claw = new Claw(new ClawConfig(hardwareMap));
        slides = new Slides(new SlideConfig(hardwareMap));
        xDrive = new XDrive(new XDriveConfig(hardwareMap));
        intake = new Intake(new IntakeConfig(hardwareMap));
    }
    public void start(){
        super.start();
        claw.initServos();
    }
    public void loop(){
        //drive
        float forward = -gamepad1.left_stick_y;
        float right = gamepad1.left_stick_x;
        float rotate = -gamepad1.right_stick_x;
        xDrive.driveCH(forward * driveSpeed, right * driveSpeed, rotate * driveSpeed);

        //intake
        if(gamepadEx1.rightBumper()){
            intake.intake(intakeSpeed);
        }

        if(gamepadEx1.leftBumper()){
            intakeOn = !intakeOn;
        }
        updateIntake();
        telemetry.addData("Intake (expected, actual)", intakeOn + "," + intake.getState());

        /*
        if(gamepadEx1.leftBumper()){
            intake.intake(-intakeSpeed);
        }
        if(gamepadEx1.square()){
            intake.stop();
        }
        */


        //slides
        /*
        if(gamepadEx1.dpadUp()){
            slides.up1Pixel();
        }
        if(gamepadEx1.dpadDown()){
            slides.reset();
            claw.openTop();
        }
        if(gamepadEx1.dpadRight()){
            slides.down1Pixel();
        }
        if(gamepadEx1.dpadLeft()){
            slides.goToLastPosition();
        }*/
        if(gamepad1.dpad_up){
            slides.setPower(-1);
        }else if(gamepad1.dpad_down){
            slides.setPower(1);
        }else{
            slides.setPower(0);
        }
        if(gamepadEx1.dpadLeft()){
            slides.gotoPosition();
        }
        if(gamepadEx1.circle()) {
            slides.scoreRotator();
        }
        if(gamepadEx1.options()) {
            slides.resetRotator();
        }


        //claw
        if(gamepadEx1.triangle()){
            claw.closeTop();
        }
        if(gamepadEx1.cross()){
            claw.openTop();
        }
        if(gamepadEx1.touchpad()){
            claw.score();
        }

        claw.scoreUpdate();
        //slides.updateClawServo();
        slides.telem(telemetry);
        telemetry.update();
        copyGamepads();
    }

    protected void updateIntake() {
        if(intakeOn) {
            intake.intake(-intakeSpeed);
        }
        else {
            intake.stop();
        }

    }

}
