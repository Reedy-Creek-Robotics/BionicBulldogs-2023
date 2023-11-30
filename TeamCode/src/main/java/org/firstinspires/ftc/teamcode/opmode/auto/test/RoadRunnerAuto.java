package org.firstinspires.ftc.teamcode.opmode.auto.test;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;
@Autonomous
public class RoadRunnerAuto extends AutoBase {
    public TrajectorySequence getTrajectory(SampleMecanumDrive drive){
        Pose2d startPos = new Pose2d(12, 63, Math.toRadians(-90));

        TrajectorySequence sequence = drive.trajectorySequenceBuilder(startPos)
                .forward(30)
                .lineToLinearHeading(new Pose2d(44, 35.2, Math.toRadians(180)))
                .forward(100)
                .back(100)
                .forward(100)
                .back(100)
                .build();
        return sequence;
    }
}
