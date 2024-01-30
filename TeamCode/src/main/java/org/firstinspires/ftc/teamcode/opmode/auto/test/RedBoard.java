package org.firstinspires.ftc.teamcode.opmode.auto.test;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.modules.robot.ElementPosition;
import org.firstinspires.ftc.teamcode.modules.robot.RobotTeam;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequenceBuilder;
@Autonomous
public class RedBoard extends AutoBase{
    public RobotTeam getTeam(){
        return RobotTeam.Red;
    }
    public Pose2d getStartPos(){
        return new Pose2d(12, -62.5, Math.toRadians(90));
    }
    public Vector2d getPreloadPos(){
        return new Vector2d(12, -36);
    }
    public TrajectorySequence getTrajectory(Pose2d start, SampleMecanumDrive drive, ElementPosition elementPosition){
        TrajectorySequenceBuilder builder = drive.trajectorySequenceBuilder(start);
        if(elementPosition == ElementPosition.Right){
            builder.lineToLinearHeading(new Pose2d(12, -60, Math.toRadians(90)));
        }else {
            builder.lineToConstantHeading(new Vector2d(12, -60));
        }
        switch(elementPosition){
            case Left:
                builder.lineToLinearHeading(new Pose2d(51.5, -29, Math.toRadians(180)));
                break;
            case Center:
                builder.lineToLinearHeading(new Pose2d(51.5, -35, Math.toRadians(180)));
                break;
            case Right:
                builder.lineToLinearHeading(new Pose2d(51.5, -41, Math.toRadians(180)));
                break;
        }
        return builder.build();
    }
}
