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
public class BlueBoard extends AutoBase{
    public RobotTeam getTeam(){
        return RobotTeam.Blue;
    }
    public Pose2d getStartPos() {
        return new Pose2d(12, 66, Math.toRadians(-90));
    }
    public TrajectorySequence getTrajectory(Pose2d start, SampleMecanumDrive drive, ElementPosition elementPosition){
        TrajectorySequenceBuilder builder = drive.trajectorySequenceBuilder(start)
                .lineToConstantHeading(new Vector2d(12, 60));
        switch(elementPosition){
            case Right:
                builder.lineToLinearHeading(new Pose2d(52, 34, Math.toRadians(180)));
                break;
            case Center:
                builder.lineToLinearHeading(new Pose2d(50, 42, Math.toRadians(180)));
                break;
            case Left:
                builder.lineToLinearHeading(new Pose2d(52.5, 48, Math.toRadians(180)));
                break;
        }
        return builder.build();
    }
}
