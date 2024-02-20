package org.firstinspires.ftc.teamcode.opmode.actionAuto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.modules.auto.actions.Action_Base;
import org.firstinspires.ftc.teamcode.modules.auto.actions.Action_DriveToAprilTag;
import org.firstinspires.ftc.teamcode.modules.auto.actions.Action_Func;
import org.firstinspires.ftc.teamcode.modules.auto.actions.Action_GrabFromStack;
import org.firstinspires.ftc.teamcode.modules.auto.actions.Action_Park;
import org.firstinspires.ftc.teamcode.modules.auto.actions.Action_ResetPos;
import org.firstinspires.ftc.teamcode.modules.auto.actions.Action_ScoreOnBackboard;
import org.firstinspires.ftc.teamcode.modules.auto.actions.Action_Trajectory;
import org.firstinspires.ftc.teamcode.modules.robot.ElementPosition;
import org.firstinspires.ftc.teamcode.modules.robot.Robot;
import org.firstinspires.ftc.teamcode.modules.robot.RobotTeam;
import org.firstinspires.ftc.teamcode.roadrunner.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequenceBuilder;
import org.opencv.core.Mat;

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
        if(elementPosition == ElementPosition.Center){
            builder.turn(-90);
        }else{
            builder.lineToConstantHeading(new Vector2d(30, 41));
        }

        TrajectorySequenceBuilder toStack = drive.trajectorySequenceBuilder(new Pose2d(48, 36, Math.toRadians(180)))
                .lineToConstantHeading(new Vector2d(24, 12),
                        drive.getVelocityConstraint(60, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                        drive.getAccelerationConstraint(DriveConstants.MAX_ACCEL)
                )
                .addDisplacementMarker(()->Robot.intake.stackGrabberMid())
                .lineToConstantHeading(new Vector2d(-40, 12),
                        drive.getVelocityConstraint(60, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                        drive.getAccelerationConstraint(DriveConstants.MAX_ACCEL)
                )
                .lineToConstantHeading(new Vector2d(-58.5, 9));

        TrajectorySequenceBuilder toBoard = drive.trajectorySequenceBuilder(new Pose2d(-60, 12, Math.toRadians(180)))
                .lineToConstantHeading(new Vector2d(24, 12),
                        drive.getVelocityConstraint(60, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                        drive.getAccelerationConstraint(DriveConstants.MAX_ACCEL)
                )
                .addDisplacementMarker(()->{
                        Robot.intake.stop();
                        Robot.claw.closeTop();
                })
                .lineToConstantHeading(new Vector2d(36, 36),
                        drive.getVelocityConstraint(60, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                        drive.getAccelerationConstraint(DriveConstants.MAX_ACCEL)
                );

        if(elementPosition != ElementPosition.Left) {
            list.add(new Action_Trajectory(builder.build()));
        }//to backboard
        int offset = (elementPosition.getValue() - 1) * -6;
        list.add(new Action_Func(()-> {
            slides.gotoPosition(-850);
        }));
        list.add(new Action_DriveToAprilTag(1, new Vector2d(-1, offset)));   //line up with backboard
        list.add(new Action_ScoreOnBackboard(-850, true, false));                            //score on backboard
        list.add(new Action_Trajectory(toStack.build()));                   //to stack
        list.add(new Action_GrabFromStack());                               //grab from stack
        list.add(new Action_Trajectory(toBoard.build()));
        list.add(new Action_Func(()-> {
            slides.gotoPosition(-1000);
        }));//to backboard
        list.add(new Action_DriveToAprilTag(2, new Vector2d(-1.5, 0)));
        list.add(new Action_ScoreOnBackboard(-1000, false, false));             //score on backboard
        //list.add(new Action_Park(getStartPos()));                           //park
        return list;
    }
}
