package org.firstinspires.ftc.teamcode.opmode.actionAuto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.modules.auto.actions.Action_Base;
import org.firstinspires.ftc.teamcode.modules.auto.actions.Action_DriveToAprilTag;
import org.firstinspires.ftc.teamcode.modules.auto.actions.Action_GrabFromStack;
import org.firstinspires.ftc.teamcode.modules.auto.actions.Action_Park;
import org.firstinspires.ftc.teamcode.modules.auto.actions.Action_ScoreOnBackboard;
import org.firstinspires.ftc.teamcode.modules.auto.actions.Action_Trajectory;
import org.firstinspires.ftc.teamcode.modules.robot.ElementPosition;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequenceBuilder;

import java.util.ArrayList;
import java.util.List;

@Autonomous
public class BlueStackCycle extends AutoBase {
    public Pose2d getStartPos() {
        return new Pose2d(-35, 62.5, Math.toRadians(-90));
    }
    public List<Action_Base> getActions(Pose2d start, ElementPosition elementPosition){
        List<Action_Base> list = new ArrayList<Action_Base>();

        TrajectorySequenceBuilder builder = drive.trajectorySequenceBuilder(start);
        if(elementPosition == ElementPosition.Left) {
            builder.lineToLinearHeading(new Pose2d(-34.5, 57.5, Math.toRadians(-90)));
            builder.turn(Math.toRadians(-90));
        }else{
            builder.lineToLinearHeading(new Pose2d(-34.5, 57.5, Math.toRadians(180)));
        }
        builder.lineToLinearHeading(new Pose2d(12,57.5, Math.toRadians(180)));
        builder.lineToConstantHeading(new Vector2d(30, 41));

        TrajectorySequenceBuilder toStack = drive.trajectorySequenceBuilder(new Pose2d(54, 36, Math.toRadians(-0)))
                .lineToConstantHeading(new Vector2d(24, 12))
                .lineToConstantHeading(new Vector2d(-60, 12));

        TrajectorySequenceBuilder toBoard = drive.trajectorySequenceBuilder(new Pose2d(-60, 12, Math.toRadians(-0)))
                .lineToConstantHeading(new Vector2d(24, 12))
                .lineToConstantHeading(new Vector2d(30, 41));

        list.add(new Action_Trajectory(builder.build()));                   //to backboard
        list.add(new Action_DriveToAprilTag(elementPosition.getValue()));   //line up with backboard
        list.add(new Action_ScoreOnBackboard());                            //score on backboard
        list.add(new Action_Trajectory(toStack.build()));                   //to stack
        list.add(new Action_GrabFromStack());                               //grab from stack
        list.add(new Action_Trajectory(toBoard.build()));                   //to backboard
        list.add(new Action_DriveToAprilTag(elementPosition.getValue()));   //line up with backboard
        list.add(new Action_ScoreOnBackboard());                            //score on backboard
        list.add(new Action_Park(getStartPos()));                           //park
        return list;
    }
}
