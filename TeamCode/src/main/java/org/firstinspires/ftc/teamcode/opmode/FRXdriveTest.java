package org.firstinspires.ftc.teamcode.opmode;

import com.acmerobotics.dashboard.DashboardCore;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.config.XDriveConfig;
import org.firstinspires.ftc.teamcode.modules.drive.HDrive;
import org.firstinspires.ftc.teamcode.modules.drive.XDrive;
import org.firstinspires.ftc.teamcode.modules.hardware.Imu;
@TeleOp
public class FRXdriveTest extends LinearOpMode {
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        XDriveConfig xDriveConfig = new XDriveConfig(hardwareMap);
        HDrive drive = new XDrive();
        drive.init(xDriveConfig);
        waitForStart();
        while (opModeIsActive()) {
            float forward = -gamepad1.left_stick_y;
            float right = gamepad1.left_stick_x;
            float rotate = -gamepad1.right_stick_x;
            drive.updateFR(forward, right, rotate);
        }
    }
}
