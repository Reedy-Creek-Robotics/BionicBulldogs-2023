package org.firstinspires.ftc.teamcode.modules.auto.actions;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.R;
import org.firstinspires.ftc.teamcode.modules.robot.RecognitionProcesser;
import org.firstinspires.ftc.teamcode.modules.robot.Robot;
import org.firstinspires.ftc.teamcode.roadrunner.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;

public class Action_GrabFromStack extends Action_Base{
    ElapsedTime elapsedTime;
    public void run(){
        Robot.intake.grabStack();
        elapsedTime = new ElapsedTime();
        delay(1.0f);
        Robot.drive.followTrajectorySequence(Robot.drive.trajectorySequenceBuilder(Robot.drive.getPoseEstimate())
                .lineToConstantHeading(new Vector2d(Robot.drive.getPoseEstimate().getX() + 5, Robot.drive.getPoseEstimate().getY()))
                .build()
        );
        Robot.intake.resetStackGrabber();
        Robot.intake.intake(0.8);
        Robot.drive.followTrajectorySequence(Robot.drive.trajectorySequenceBuilder(Robot.drive.getPoseEstimate())
                .lineToConstantHeading(new Vector2d(Robot.drive.getPoseEstimate().getX() - 2, Robot.drive.getPoseEstimate().getY() + 3),
                        Robot.drive.getVelocityConstraint(10, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                        Robot.drive.getAccelerationConstraint(DriveConstants.MAX_ACCEL)
                )
                .build());
        delay(0.5f);
        Robot.intake.outtake(0.8);
    }
    void delay(float time){
        elapsedTime.reset();
        while(elapsedTime.seconds() < time);
    }
}
