package org.firstinspires.ftc.teamcode.modules.auto.actions;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.modules.robot.Robot;

public class Action_GrabFromStack extends Action_Base{
    public void run(){
        ElapsedTime elapsedTime = new ElapsedTime();
        elapsedTime.reset();
        Robot.intake.intake(0.8);
        while(elapsedTime.seconds() < 1.5);
        Robot.drive.followTrajectorySequence(
                Robot.drive.trajectorySequenceBuilder(Robot.drive.getPoseEstimate())
                        .back(2)
                        .forward(2)
                        .build()
        );
        elapsedTime.reset();
        while(elapsedTime.seconds() < 1.5);
        Robot.intake.stop();
    }
}
