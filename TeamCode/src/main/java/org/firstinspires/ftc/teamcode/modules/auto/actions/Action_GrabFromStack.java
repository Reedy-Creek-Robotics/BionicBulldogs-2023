package org.firstinspires.ftc.teamcode.modules.auto.actions;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.R;
import org.firstinspires.ftc.teamcode.modules.robot.Robot;
import org.firstinspires.ftc.teamcode.roadrunner.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;

public class Action_GrabFromStack extends Action_Base{
    public void run(){
        Robot.intake.intake(0.8);
        //int pixelCount = 0;
        //boolean prevHasPixel = false;
        Robot.drive.followTrajectorySequence(Robot.drive.trajectorySequenceBuilder(Robot.drive.getPoseEstimate())
                .forward(6,
                        Robot.drive.getVelocityConstraint(10, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                        Robot.drive.getAccelerationConstraint(DriveConstants.MAX_ACCEL))
                        .waitSeconds(0.25)
                .build());
        Robot.intake.outtake(0.8);
    }
}
