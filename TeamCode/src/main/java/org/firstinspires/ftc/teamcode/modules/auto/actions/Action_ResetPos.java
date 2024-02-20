package org.firstinspires.ftc.teamcode.modules.auto.actions;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.firstinspires.ftc.teamcode.modules.robot.Robot;

public class Action_ResetPos extends Action_Base{
    Pose2d pos;
    public Action_ResetPos(Pose2d _pos){
        pos = _pos;
    }
    public void run(){
        Robot.drive.setPoseEstimate(pos);
    }
}
