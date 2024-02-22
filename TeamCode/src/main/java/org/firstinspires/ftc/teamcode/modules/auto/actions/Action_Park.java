package org.firstinspires.ftc.teamcode.modules.auto.actions;

import android.graphics.CornerPathEffect;
import android.util.Log;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.firstinspires.ftc.teamcode.modules.robot.Robot;
import org.firstinspires.ftc.teamcode.modules.robot.RobotTeam;

public class Action_Park extends Action_Base{
    ParkLocation location;
    public Action_Park(Pose2d start){
        if(start.getX() > 0){
            location = ParkLocation.Center;
        }else{
            location = ParkLocation.Corner;
        }
    }

    public Action_Park(ParkLocation _location){
        location = _location;
    }
    public void run(){
        RobotTeam team = (Robot.drive.getPoseEstimate().getY() > 0 ? RobotTeam.Blue : RobotTeam.Red);
        double endHeading = Math.toRadians(team == RobotTeam.Red ? 90 : -90);
        double parkY = (team == RobotTeam.Red ? -1 : 1) * (location == ParkLocation.Corner ? 60 : 12);
        Log.d("Park", location.toString() + ", " + parkY + ", " + Robot.drive.getPoseEstimate());
        Robot.drive.followTrajectorySequence(
                Robot.drive.trajectorySequenceBuilder(Robot.drive.getPoseEstimate())
                        .lineToLinearHeading(new Pose2d(Robot.drive.getPoseEstimate().getX(), parkY, endHeading))
                        .lineToConstantHeading(new Vector2d(60, parkY))
                        .build()
        );
    }
}
