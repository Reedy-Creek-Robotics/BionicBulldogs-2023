package org.firstinspires.ftc.teamcode.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.modules.Intake;
import org.firstinspires.ftc.teamcode.modules.drive.HDrive;
import org.firstinspires.ftc.teamcode.modules.drive.XDrive;
import org.firstinspires.ftc.teamcode.opmode.config.DriveIntakeConfig;
import org.firstinspires.ftc.teamcode.opmode.config.XDriveConfig;

@TeleOp
public class DriveIntakeTest extends LinearOpMode {
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        DriveIntakeConfig driveIntakeConfig = new DriveIntakeConfig(hardwareMap);
        HDrive drive = new XDrive();
        drive.init(driveIntakeConfig);

        Intake intake = new Intake(driveIntakeConfig);
        waitForStart();
        float intakePwr = 0.8f;

        Gamepad currrentGamepad1 = new Gamepad();

        Gamepad previousGamepad1 = new Gamepad();

        while (opModeIsActive()) {
            float forward = -gamepad1.left_stick_y;
            float right = gamepad1.left_stick_x;
            float rotate = -gamepad1.right_stick_x;
            drive.updateFR(forward, right, rotate);

            previousGamepad1.copy(currrentGamepad1);

            currrentGamepad1.copy(gamepad1);

            if (currrentGamepad1.dpad_up && !previousGamepad1.dpad_up){
                if (intakePwr < 1){
                    intakePwr = intakePwr +0.05f;
                }
            }
            if (currrentGamepad1.dpad_down && !previousGamepad1.dpad_down){
                if (intakePwr > 0) {
                    intakePwr = intakePwr - 0.05f;

                }
            }
            if (gamepad1.cross) {
                intake.intake(intakePwr);
            }
            if (gamepad1.circle) {
                intake.outtake(intakePwr);
            }
            if (gamepad1.square) {
                intake.stop();
            }
            telemetry.addData("Intakes Power", intakePwr);
            telemetry.update();
        }
    }
}
