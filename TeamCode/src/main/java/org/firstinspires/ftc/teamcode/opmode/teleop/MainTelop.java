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
    float intakeSpeed = 0.4f;

    public void init(){
        super.init();
        claw = new Claw(new ClawConfig(hardwareMap));
        slides = new Slides(new SlideConfig(hardwareMap));
        xDrive = new XDrive(new XDriveConfig(hardwareMap));
        intake = new Intake(new IntakeConfig(hardwareMap));
    }
    public void start(){
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
        if(gamepadEx1.square()){
            intake.stop();
        }

        //slides
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
        }

        //claw
        if(gamepadEx1.leftBumper()){
            claw.closeTop();
        }
        if(gamepadEx1.touchpad()){
            claw.score();
        }
        claw.scoreUpdate();

        copyGamepads();
    }
}
