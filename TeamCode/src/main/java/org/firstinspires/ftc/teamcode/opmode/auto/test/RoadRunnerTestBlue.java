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
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequenceBuilder;

@Autonomous
public class RoadRunnerTestBlue extends LinearOpMode {
    public void runOpMode(){
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        Pose2d startPose = new Pose2d(-35, 60, Math.toRadians(-90));

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        drive.resetEncoders();
        drive.setPoseEstimate(startPose);

        TrajectorySequence sequence = drive.trajectorySequenceBuilder(startPose)
                .splineToLinearHeading(new Pose2d(-16, 5),  Math.toRadians(0))
                .splineToLinearHeading(new Pose2d(40,25, Math.toRadians(180)), Math.toRadians(180))
                .build();

        waitForStart();

        drive.followTrajectorySequence(sequence);
    }
}
