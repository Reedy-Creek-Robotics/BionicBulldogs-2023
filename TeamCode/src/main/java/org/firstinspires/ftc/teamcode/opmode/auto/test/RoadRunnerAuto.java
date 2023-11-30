package org.firstinspires.ftc.teamcode.opmode.auto.test;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;
@Autonomous
public class RoadRunnerAuto extends AutoBase {
    public TrajectorySequence getTrajectory(SampleMecanumDrive drive){
        Pose2d startPos = new Pose2d(-36, 66, Math.toRadians(-90));
        drive.setPoseEstimate(startPos);

        TrajectorySequence sequence = drive.trajectorySequenceBuilder(new Pose2d(-36, 66, Math.toRadians(270)))
                .lineToLinearHeading(new Pose2d(-30, 36, Math.toRadians(180)))
                .lineToLinearHeading(new Pose2d(-60, 36, Math.toRadians(360)))
                .splineTo(new Vector2d(-24, 60), Math.toRadians(360))
                .splineTo(new Vector2d(12, 60), Math.toRadians(360))
                .splineTo(new Vector2d(48, 36), Math.toRadians(360))
                .turn(Math.toRadians(90))
                .splineTo(new Vector2d(12, 60), Math.toRadians(180))
                .splineTo(new Vector2d(-24, 60), Math.toRadians(180))
                .lineToLinearHeading(new Pose2d(-60, 36, Math.toRadians(360)))
                .splineTo(new Vector2d(-24, 60), Math.toRadians(360))
                .splineTo(new Vector2d(12, 60), Math.toRadians(360))
                .splineTo(new Vector2d(48, 36), Math.toRadians(360))
                .turn(Math.toRadians(90))
                .splineTo(new Vector2d(12, 60), Math.toRadians(180))
                .splineTo(new Vector2d(-24, 60), Math.toRadians(180))
                .lineToLinearHeading(new Pose2d(-60, 36, Math.toRadians(360)))
                .splineTo(new Vector2d(-24, 60), Math.toRadians(360))
                .splineTo(new Vector2d(12, 60), Math.toRadians(360))
                .splineTo(new Vector2d(48, 36), Math.toRadians(360))
                .build();
        return sequence;
    }
}
