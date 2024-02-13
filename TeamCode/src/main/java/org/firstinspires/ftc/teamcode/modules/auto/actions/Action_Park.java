package org.firstinspires.ftc.teamcode.modules.auto.actions;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.firstinspires.ftc.teamcode.modules.robot.Robot;
import org.firstinspires.ftc.teamcode.modules.robot.RobotTeam;

public class Action_Park extends Action_Base{
    Pose2d start;
    public Action_Park(Pose2d _start){
        start = _start;
    }
    public void run(){
        RobotTeam team = (start.getY() > 0 ? RobotTeam.Blue : RobotTeam.Red);
        Robot.drive.followTrajectorySequence(
                Robot.drive.trajectorySequenceBuilder(Robot.drive.getPoseEstimate())
                        .lineToLinearHeading(new Pose2d(
                                Robot.drive.getPoseEstimate().getX(),
                                (team == RobotTeam.Red ? -1 : 1) * (start.getX() > 0 ? 60 : 12) * (team == RobotTeam.Red && start.getX() < 0 ? 0.5 : 1),
                                start.getHeading()
                        ))
                        .back(3)
                        .build()
        );
    }
}
