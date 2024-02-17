package org.firstinspires.ftc.teamcode.opmode.teleop.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.opmode.config.XDriveConfig;
import org.firstinspires.ftc.teamcode.modules.drive.XDrive;
@TeleOp

@Config
public class FRXDriveTest extends LinearOpMode {
    public static double SPEED = 1;
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        XDriveConfig xDriveConfig = new XDriveConfig(hardwareMap);
        XDrive drive = new XDrive();
        drive.init(xDriveConfig);
        waitForStart();
        while (opModeIsActive()) {
            float forward = gamepad1.left_stick_y;
            float right = -gamepad1.left_stick_x;
            float rotate = gamepad1.right_stick_x;
            drive.drive(forward * (float)SPEED, right * (float)SPEED, rotate * (float)SPEED);
            drive.debugTelemetry(telemetry);
            telemetry.update();
        }
    }
}
