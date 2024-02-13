package org.firstinspires.ftc.teamcode.modules.auto.actions;

import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.firstinspires.ftc.teamcode.modules.robot.Robot;

public class Action_DriveToAprilTag extends Action_Base{
    int tag;
    Vector2d offset;
    public Action_DriveToAprilTag(int _tag){
        offset = new Vector2d();
        tag = _tag;
    }
    public Action_DriveToAprilTag(int _tag, Vector2d _offset){
        offset = _offset;
        tag = _tag;
    }
    public void run(){
        Robot.driveToAprilTag.roadRunnerDriveToTag(tag, Robot.drive, offset);
    }
}
