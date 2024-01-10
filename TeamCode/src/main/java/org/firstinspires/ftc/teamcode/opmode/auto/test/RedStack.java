package org.firstinspires.ftc.teamcode.opmode.auto.test;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.firstinspires.ftc.teamcode.modules.robot.ElementPosition;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequenceBuilder;

public class RedStack extends AutoBase{
    public Pose2d getStartPos(){
        return new Pose2d(-36, -66, Math.toRadians(90));
    }
    public Vector2d getPreloadPos(){
        return new Vector2d(36, -36);
    }
    public TrajectorySequence getTrajectory(Pose2d start, SampleMecanumDrive drive, ElementPosition elementPosition){
        TrajectorySequenceBuilder builder = drive.trajectorySequenceBuilder(start)
                .lineToConstantHeading(new Vector2d(-36, -60))
                .lineToConstantHeading(new Vector2d(30, -60));
        switch(elementPosition){
            case Left:
                builder.splineToLinearHeading(new Pose2d(54, -30, Math.toRadians(180)), Math.toRadians(180));
                break;
            case Center:
                builder.splineToLinearHeading(new Pose2d(54, -36, Math.toRadians(180)), Math.toRadians(180));
                break;
            case Right:
                builder.splineToLinearHeading(new Pose2d(54, -42, Math.toRadians(180)), Math.toRadians(180));
                break;
        }
        builder.addDisplacementMarker(()->{
            scoreOnBackboard();
        });
        return builder.build();
    }
}
