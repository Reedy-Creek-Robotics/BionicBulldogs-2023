package org.firstinspires.ftc.teamcode.coach.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.modules.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.modules.hardware.ImuEx;
@Disabled
@TeleOp(name = "Self HDriveTeleop")
public class XDriveTeleOp extends OpMode {
    Telemetry dashTelemetry;

    ImuEx imu;
    MecanumDrive drive;

    Gamepad currentGamepad1 = new Gamepad();
    Gamepad previousGamepad1 = new Gamepad();

    boolean fieldCentricEnabled = false;
    double heading = 0.0;

    @Override
    public void init() {
        XDriveTeleOpConfig config = new XDriveTeleOpConfig(hardwareMap);
        dashTelemetry = new MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().getTelemetry());

        drive = new MecanumDrive(
            config.frontLeft,
            config.frontRight,
            config.backLeft,
            config.backRight,
            dashTelemetry
        );

        imu = config.imu;
    }

    @Override
    public void loop() {
        updateGamepad();
        updateFieldCentric();
        updateHeading();
        updateDrive();
        updateTelemetry();
    }

    protected void updateDrive() {
        double forward = Math.pow(-gamepad1.left_stick_y, 3);
        double strafe = Math.pow(gamepad1.left_stick_x, 3);
        double turn = Math.pow(gamepad1.right_stick_x, 3);
        drive.drive(forward, strafe, turn, heading);
    }

    protected void updateHeading() {
        if( !fieldCentricEnabled ) {
            heading = 0.0;
        }
        else {
            heading = imu.getHeading(AngleUnit.RADIANS);
        }
    }

    protected void updateGamepad() {
        previousGamepad1.copy(currentGamepad1);
        currentGamepad1.copy(gamepad1);
    }

    protected void updateFieldCentric() {
        if( currentGamepad1.right_bumper && !previousGamepad1.right_bumper ) {
            fieldCentricEnabled = !fieldCentricEnabled;
        }
    }

    protected void updateTelemetry() {
        dashTelemetry.addData("left_y_raw: ", gamepad1.left_stick_y);
        dashTelemetry.addData("left_x_raw: ", gamepad1.left_stick_x);
        dashTelemetry.addData("right_x_raw: ", gamepad1.right_stick_x);
        dashTelemetry.addData("fieldCentricEnabled: ", fieldCentricEnabled);
        dashTelemetry.addData("heading: ", heading);

        dashTelemetry.update();
    }
}
