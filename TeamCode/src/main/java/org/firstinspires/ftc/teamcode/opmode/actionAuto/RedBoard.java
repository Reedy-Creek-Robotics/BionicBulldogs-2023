package org.firstinspires.ftc.teamcode.opmode.actionAuto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.modules.auto.actions.Action_Base;
import org.firstinspires.ftc.teamcode.modules.auto.actions.Action_DriveToAprilTag;
import org.firstinspires.ftc.teamcode.modules.auto.actions.Action_Park;
import org.firstinspires.ftc.teamcode.modules.auto.actions.Action_ScoreOnBackboard;
import org.firstinspires.ftc.teamcode.modules.auto.actions.Action_Trajectory;
import org.firstinspires.ftc.teamcode.modules.robot.ElementPosition;
import org.firstinspires.ftc.teamcode.modules.robot.Robot;
import org.firstinspires.ftc.teamcode.modules.robot.RobotTeam;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequenceBuilder;

import java.util.ArrayList;
import java.util.List;

@Autonomous
public class RedBoard extends AutoBase {
    public Pose2d getStartPos(){
        return new Pose2d(12, -62.5, Math.toRadians(90));
    }
    public List<Action_Base> getActions(Pose2d start, ElementPosition elementPosition){
        List<Action_Base> list = new ArrayList<Action_Base>();

        TrajectorySequenceBuilder builder = Robot.drive.trajectorySequenceBuilder(start);
        if(elementPosition == ElementPosition.Right){
            builder.lineToLinearHeading(new Pose2d(12, -60, Math.toRadians(90)));
        }else {
            builder.lineToConstantHeading(new Vector2d(12, -60));
        }
        builder.lineToLinearHeading(new Pose2d(30, -41, Math.toRadians(180)));

        list.add(new Action_Trajectory(builder.build()));                           //to backbord
        list.add(new Action_DriveToAprilTag(elementPosition.getValue() + 3));  //line up with backboard
        list.add(new Action_ScoreOnBackboard());                                    //score on backboard
        list.add(new Action_Park(getStartPos()));                                   //park
        return list;
    }
}