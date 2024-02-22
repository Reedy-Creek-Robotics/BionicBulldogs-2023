package org.firstinspires.ftc.teamcode.opmode.actionAuto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.modules.auto.actions.Action_Base;
import org.firstinspires.ftc.teamcode.modules.auto.actions.Action_DriveToAprilTag;
import org.firstinspires.ftc.teamcode.modules.auto.actions.Action_Park;
import org.firstinspires.ftc.teamcode.modules.auto.actions.Action_ScoreOnBackboard;
import org.firstinspires.ftc.teamcode.modules.auto.actions.Action_Trajectory;
import org.firstinspires.ftc.teamcode.modules.auto.actions.ParkLocation;
import org.firstinspires.ftc.teamcode.modules.robot.ElementPosition;
import org.firstinspires.ftc.teamcode.modules.robot.Robot;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequenceBuilder;

import java.util.ArrayList;
import java.util.List;

@Autonomous(group = "CenterPark")
public class RedStackCenterPark extends AutoBase {
    public Pose2d getStartPos(){
        return new Pose2d(-35, -62.5, Math.toRadians(90));
    }
    public List<Action_Base> getActions(Pose2d start, ElementPosition elementPosition){
        List<Action_Base> list = new ArrayList<Action_Base>();

        TrajectorySequenceBuilder builder = Robot.drive.trajectorySequenceBuilder(start);
        if(elementPosition == ElementPosition.Right) {
            builder.lineToLinearHeading(new Pose2d(-34.5, -57.5, Math.toRadians(90)));
            builder.turn(Math.toRadians(90));
        }else{
            builder.lineToLinearHeading(new Pose2d(-34.5, -57.5, Math.toRadians(180)));
        }
        builder.lineToLinearHeading(new Pose2d(15,-57.5, Math.toRadians(180)));
        builder.lineToConstantHeading(new Vector2d(30, -41));

        int offset = (elementPosition.getValue() - 3) * -6 - 42;
        list.add(new Action_Trajectory(builder.build()));                           //to backboard
        list.add(new Action_DriveToAprilTag(6, new Vector2d(-1, offset)));  //line up with backboard
        list.add(new Action_ScoreOnBackboard());                                    //score on backboard
        list.add(new Action_Park(ParkLocation.Center));                                   //park
        return list;
    }
}
