package org.firstinspires.ftc.teamcode.modules.auto.actions;

import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.firstinspires.ftc.teamcode.modules.robot.Robot;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;

public class Action_DriveToAprilTag extends Action_Base{
    int tag;
    Vector2d offset;
    TrajectorySequence defult;
    Vector2d multipliers;
    public Action_DriveToAprilTag(int _tag, TrajectorySequence _defult){
        defult = _defult;
        offset = new Vector2d();
        tag = _tag;
    }
    public Action_DriveToAprilTag(int _tag, Vector2d _offset, TrajectorySequence _defult){
        offset = _offset;
        tag = _tag;
        defult = _defult;
    }
    public Action_DriveToAprilTag(int _tag){
        offset = new Vector2d();
        tag = _tag;
    }
    public Action_DriveToAprilTag(int _tag, Vector2d _offset){
        offset = _offset;
        tag = _tag;
        multipliers = new Vector2d(1);
    }
    public Action_DriveToAprilTag(int _tag, Vector2d _offset, Vector2d _multipliers){
        offset = _offset;
        tag = _tag;
        multipliers = _multipliers;
    }
    public void run(){
        if(!Robot.driveToAprilTag.roadRunnerDriveToTag(tag, Robot.drive, offset, multipliers)){
            if(defult != null) {
                Robot.drive.followTrajectorySequence(defult);
            }
        }
    }
}
