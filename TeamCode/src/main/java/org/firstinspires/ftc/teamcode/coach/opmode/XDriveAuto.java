package org.firstinspires.ftc.teamcode.coach.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.modules.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.modules.hardware.ImuEx;
@Disabled
@Autonomous(name = "XDriveAuto")
public class XDriveAuto extends OpMode {
    Telemetry dashTelemetry;
    ElapsedTime totalElapsedTime;

    MecanumDrive drive;
    ImuEx imu;


    int loopCount = 0;

    @Override
    public void init() {
        XDriveAutoConfig config = new XDriveAutoConfig(hardwareMap);
        dashTelemetry = new MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().getTelemetry());

        drive = new MecanumDrive(
            config.frontLeft,
            config.frontRight,
            config.backLeft,
            config.backRight,
            dashTelemetry
        );

        imu = config.imu;

        totalElapsedTime = new ElapsedTime();
    }

    @Override
    public void loop() {
        ElapsedTime elapsedTime = new ElapsedTime();
        while( elapsedTime.milliseconds() < 1500 ) {
            driveForward();
        }

        elapsedTime = new ElapsedTime();
        while( elapsedTime.milliseconds() < 1500 ) {
            driveBackward();
        }

        if( loopCount < 2 ) {
            loopCount++;
        }
        else {
            terminateOpModeNow();
        }
    }

    protected void driveForward() {
        drive.drive(0.4, 0.0, 0.0);
    }

    protected void driveBackward() {
        drive.drive(-0.4, 0.0, 0.0);
    }
}
