package org.firstinspires.ftc.teamcode.opmode.auto.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.modules.drive.XDrive;
import org.firstinspires.ftc.teamcode.modules.robot.DriveToAprilTag;
import org.firstinspires.ftc.teamcode.opmode.config.XDriveConfig;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;

@Autonomous(group = "test")
public class DriveToAprilTagTest extends LinearOpMode {
    public void runOpMode(){
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        XDrive xDrive = new XDrive(new XDriveConfig(hardwareMap));
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        DriveToAprilTag aprilTag = new DriveToAprilTag(xDrive, this, "Webcam 2", false);
        while(opModeInInit()){
            aprilTag.initTelem();
            telemetry.update();
        }
        if(!opModeIsActive()){
            return;
        }
        waitForStart();
        if(!opModeIsActive()){
            return;
        }
        /*while (aprilTag.driveToTag(1) && opModeIsActive()) {
            aprilTag.telemetry();
            telemetry.update();
        }*/
        aprilTag.roadRunnerDriveToTag(1, drive, new Vector2d(0, 0));
    }
}
