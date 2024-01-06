package org.firstinspires.ftc.teamcode.opmode.teleop.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.modules.drive.XDrive;
import org.firstinspires.ftc.teamcode.modules.hardware.GamepadEx;
import org.firstinspires.ftc.teamcode.opmode.config.XDriveConfig;

@TeleOp
public class DpadXDrive extends LinearOpMode {
    public void runOpMode() {
        GamepadEx gamepadEx = new GamepadEx(gamepad1);
        float speed = 0.5f;
        XDriveConfig xDriveConfig = new XDriveConfig(hardwareMap);
        XDrive drive = new XDrive();
        drive.init(xDriveConfig);
        waitForStart();
        while (opModeIsActive()) {
            float forward = 0;
            float right = 0;
            float rotate = 0;
            if(gamepad1.dpad_up){
                forward = speed;
            }
            if(gamepad1.dpad_down){
                forward = -speed;
            }
            if(gamepad1.dpad_left){
                right = -speed;
            }
            if(gamepad1.dpad_right){
                right = speed;
            }
            if(gamepad1.square){
                rotate = speed;
            }
            if(gamepad1.circle){
                rotate = -speed;
            }
            if(gamepadEx.triangle()){
                speed += 0.05f;
            }
            if(gamepadEx.cross()){
                speed -= 0.05f;
            }
            drive.drive(forward, right, rotate);
            drive.telem(telemetry);
            telemetry.addData("power", speed);
            telemetry.update();
            gamepadEx.copy();
        }
    }
}
