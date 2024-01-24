package org.firstinspires.ftc.teamcode.opmode.teleop.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.opmode.config.XDriveConfig;
import org.firstinspires.ftc.teamcode.modules.drive.XDrive;
import org.firstinspires.ftc.teamcode.opmode.teleop.BaseTeleOp;

@TeleOp
@Disabled
public class XDriveTest extends BaseTeleOp {
    XDrive drive;
    public void init() {
        XDriveConfig xDriveConfig = new XDriveConfig(hardwareMap);
        drive = new XDrive();
        drive.init(xDriveConfig);
    }
    public void loop() {
        float forward = -gamepad1.left_stick_y;
        float right = gamepad1.left_stick_x;
        float rotate = -gamepad1.right_stick_x;
        drive.drive(forward, right, rotate);
        drive.telem(telemetry);
        drive.debugTelemetry(telemetry);
        telemetry.update();
    }
}
