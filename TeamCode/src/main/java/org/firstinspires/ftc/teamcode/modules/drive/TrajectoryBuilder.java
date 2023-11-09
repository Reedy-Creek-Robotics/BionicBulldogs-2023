package org.firstinspires.ftc.teamcode.modules.drive;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequenceBuilder;

public class TrajectoryBuilder {
    TrajectorySequenceBuilder trajectory;

    float x = 0;
    float y = 0;
    float heading = 0;

    public TrajectoryBuilder(SampleMecanumDrive drive, float _x, float _y, float _heading){
        trajectory = drive.trajectorySequenceBuilder(new Pose2d(_x, _y, _heading));
        x = _x;
        y = _y;
        heading = _heading;
    }
    public TrajectoryBuilder(SampleMecanumDrive drive, Pose2d pos){
        trajectory = drive.trajectorySequenceBuilder(pos);
        x = (float)pos.getX();
        y = (float)pos.getY();
        heading = (float)pos.getHeading();
    }

    /**
     * builds trajectory
     * @return the built trajectory
     */
    public TrajectorySequence build(){
        return trajectory.build();
    }

    /**
     * moves the robot forward
     * @param dist distance in inches
     */
    public void forward(float dist){
        move(0, dist);
    }
    /**
     * moves the robot backward
     * @param dist distance in inches
     */
    public void backward(float dist){
        move(0, -dist);
    }
    /**
     * moves the robot right
     * @param dist distance in inches
     */
    public void right(float dist){
        move(dist, 0);
    }
    /**
     * moves the robot left
     * @param dist distance in inches
     */
    public void left(float dist){
        move(-dist, 0);
    }

    /**
     * turns the robot right
     * @param angle angle in degrees
     */
    public void turnRight(float angle){
        heading += Math.toRadians(angle);
        trajectory.turn(Math.toRadians(angle));
    }
    /**
     * turns the robot build
     * @param angle angle in degrees
     */
    public void turnLeft(float angle){
        heading -= Math.toRadians(angle);
        trajectory.turn(Math.toRadians(-angle));
    }

    /**
     * moves the robot to a position
     * @param _x the x position in inches
     * @param _y the y position in inches
     */
    public void gotoPosition(float _x, float _y){
        x = _x;
        y = _y;
        trajectory.splineTo(new Vector2d(x, y), heading);
    }

    void move(float newx, float newy){
        x += (float)(newx * Math.cos(heading) - newy * Math.sin(heading));
        y += (float)(newx * Math.sin(heading) + newy * Math.cos(heading));
        trajectory.splineTo(new Vector2d(x, y), heading);
    }
}
