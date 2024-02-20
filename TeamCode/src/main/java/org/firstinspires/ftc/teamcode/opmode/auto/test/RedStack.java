package org.firstinspires.ftc.teamcode.opmode.auto.test;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.modules.robot.ElementPosition;
import org.firstinspires.ftc.teamcode.modules.robot.RobotTeam;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequenceBuilder;
public class RedStack extends AutoBase{
    public RobotTeam getTeam(){
        return RobotTeam.Red;
    }
    public Pose2d getStartPos(){
        return new Pose2d(-35, -62.5, Math.toRadians(90));
    }
    public Vector2d getPreloadPos(){
        return new Vector2d(36, -36);
    }
    public TrajectorySequence getTrajectory(Pose2d start, SampleMecanumDrive drive, ElementPosition elementPosition){
        TrajectorySequenceBuilder builder = drive.trajectorySequenceBuilder(start);
        if(elementPosition == ElementPosition.Right) {
            builder.lineToLinearHeading(new Pose2d(-34.5, -57.5, Math.toRadians(90)));
            builder.turn(Math.toRadians(90));
        }else{
            builder.lineToLinearHeading(new Pose2d(-34.5, -57.5, Math.toRadians(180)));
        }
        builder.lineToLinearHeading(new Pose2d(15,-57.5, Math.toRadians(180)));
        builder.lineToConstantHeading(new Vector2d(30, -41));
        return builder.build();
    }
}
