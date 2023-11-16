package org.firstinspires.ftc.teamcode.opmode.auto.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.apache.commons.math3.geometry.euclidean.twod.Line;
import org.firstinspires.ftc.ftccommon.internal.manualcontrol.ManualControlOpMode;
import org.firstinspires.ftc.teamcode.modules.drive.XDrive;
import org.firstinspires.ftc.teamcode.modules.robot.DriveToAprilTag;
import org.firstinspires.ftc.teamcode.opmode.config.XDriveConfig;
import org.firstinspires.ftc.teamcode.roadrunner.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;

@Autonomous
public class RoadRunnerTestBlue extends LinearOpMode {
    public void runOpMode(){
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        drive.resetEncoders();

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        XDrive xDrive = new XDrive();
        xDrive.init(new XDriveConfig(hardwareMap));

        DriveToAprilTag aprilTag = new DriveToAprilTag(xDrive, this);

         Trajectory forward = drive.trajectoryBuilder(new Pose2d())
                .forward(26)
                .build();
        Trajectory strafe = drive.trajectoryBuilder(forward.end())
                .strafeRight(24)
                .build();
        Trajectory contForward = drive.trajectoryBuilder(strafe.end())
                .forward(24)
                .build();
        TrajectorySequence turn = drive.trajectorySequenceBuilder(contForward.end())
                .turn(Math.toRadians(90))
                .build();
        Trajectory toBackboard = drive.trajectoryBuilder(turn.end())
                .forward(80)
                .build();
        Trajectory strafeToAprilTag = drive.trajectoryBuilder(toBackboard.end())
                .strafeLeft(24)
                .build();

        waitForStart();

        /*(drive.followTrajectory(forward);
        drive.followTrajectory(strafe);
        drive.followTrajectory(contForward);
        drive.followTrajectorySequence(turn);
        drive.followTrajectory(toBackboard);
        drive.followTrajectory(strafeToAprilTag);*/

        boolean movingToTag = true;
        while(movingToTag && opModeIsActive()) {
            movingToTag = aprilTag.driveToTag(3);
            aprilTag.telemetry();
            telemetry.update();
        }
    }
}
