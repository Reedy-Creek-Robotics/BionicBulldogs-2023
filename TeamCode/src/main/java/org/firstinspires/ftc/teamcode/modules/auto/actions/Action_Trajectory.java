package org.firstinspires.ftc.teamcode.modules.auto.actions;

import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.modules.robot.Robot;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;

public class Action_Trajectory extends Action_Base {
    TrajectorySequence sequence;
    public Action_Trajectory(TrajectorySequence trajectorySequence){
        sequence = trajectorySequence;
    }
    public void run(){
        Robot.drive.followTrajectorySequence(sequence);
    }
}
