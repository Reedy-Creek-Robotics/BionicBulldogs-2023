package org.firstinspires.ftc.teamcode.opmode.actionAuto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.modules.auto.actions.Action_Base;
import org.firstinspires.ftc.teamcode.modules.auto.actions.Action_DriveToAprilTag;
import org.firstinspires.ftc.teamcode.modules.auto.actions.Action_GrabFromStack;
import org.firstinspires.ftc.teamcode.modules.auto.actions.Action_Park;
import org.firstinspires.ftc.teamcode.modules.auto.actions.Action_ResetPos;
import org.firstinspires.ftc.teamcode.modules.auto.actions.Action_ScoreOnBackboard;
import org.firstinspires.ftc.teamcode.modules.auto.actions.Action_Trajectory;
import org.firstinspires.ftc.teamcode.modules.robot.ElementPosition;
import org.firstinspires.ftc.teamcode.modules.robot.RobotTeam;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequenceBuilder;

import java.util.ArrayList;
import java.util.List;

@Autonomous
public class BlueBoardCycle extends AutoBase{
    // starting position for blue board side
    public Pose2d getStartPos() {
        return new Pose2d(12, 62.5, Math.toRadians(-90));
    }

    @Override
    public TrajectorySequence getPreloadPath(ElementPosition elementPosition){
        switch (elementPosition){
            default:
            case Center:
                return drive.trajectorySequenceBuilder(getStartPos())
                        .lineToLinearHeading(new Pose2d(getStartPos().getX(), 36, getStartPos().getHeading()))
                        .build();
            case Left:
                return drive.trajectorySequenceBuilder(getStartPos())
                        .lineToLinearHeading(new Pose2d(36, 36, getStartPos().getHeading() + Math.toRadians(90)))
                        .build();
            case Right:
                return drive.trajectorySequenceBuilder(getStartPos())
                        .lineToLinearHeading(new Pose2d(getStartPos().getX(), 36, getStartPos().getHeading() - Math.toRadians(90)))
                        .build();
        }
    }

    public List<Action_Base> getActions(Pose2d start, ElementPosition elementPosition){
        List<Action_Base> list = new ArrayList<Action_Base>();

        TrajectorySequenceBuilder builder = drive.trajectorySequenceBuilder(start);
        if(elementPosition == ElementPosition.Left){ // moving back to near start position
            builder.lineToLinearHeading(new Pose2d(12, 60, Math.toRadians(-90)));
        }else{
            builder.lineToConstantHeading(new Vector2d(12, 60));
        }
        builder.lineToLinearHeading(new Pose2d(30, 41, Math.toRadians(180)));

        TrajectorySequenceBuilder toStack = drive.trajectorySequenceBuilder(new Pose2d(48, 36, Math.toRadians(180)))
                .lineToConstantHeading(new Vector2d(24, 12))
                .lineToConstantHeading(new Vector2d(-62, 18));

        TrajectorySequenceBuilder toBoard = drive.trajectorySequenceBuilder(new Pose2d(-60, 12, Math.toRadians(180)))
                .lineToConstantHeading(new Vector2d(24, 12))
                .lineToConstantHeading(new Vector2d(30, 41));

        Pose2d pos;
        switch (elementPosition){
            case Right:
                pos = new Pose2d(54, 42, Math.toRadians(180));
                break;
            case Center:
                pos = new Pose2d(50.5, 36, Math.toRadians(180));
                break;
            case Left:
                pos = new Pose2d(54, 30, Math.toRadians(180));
                break;
            default:
                pos = new Pose2d();
                break;
        }

        list.add(new Action_Trajectory(builder.build()));                   //to backboard
        list.add(new Action_DriveToAprilTag(elementPosition.getValue(), new Vector2d(-1, 2)));   //line up with backboard
        list.add(new Action_ResetPos(pos));
        list.add(new Action_ScoreOnBackboard());                            //score on backboard
        list.add(new Action_Trajectory(toStack.build()));                   //to stack
        list.add(new Action_GrabFromStack());                               //grab from stack
        //list.add(new Action_Trajectory(toBoard.build()));                   //to backboard
        //list.add(new Action_DriveToAprilTag(elementPosition.getValue()));   //line up with backboard
        //list.add(new Action_ScoreOnBackboard());                            //score on backboard
        //list.add(new Action_Park(getStartPos()));                           //park
        return list;
    }
}
