package org.firstinspires.ftc.teamcode.opmode.auto.test;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.path.LineSegment;
import com.acmerobotics.roadrunner.path.Path;
import com.acmerobotics.roadrunner.path.PathSegment;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;

@Autonomous
public class RoadRunnerTest extends LinearOpMode {
    public void runOpMode(){
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        drive.resetEncoders();
         Trajectory forward = drive.trajectoryBuilder(new Pose2d())
                .forward(26)
                .build();
        Trajectory strafe = drive.trajectoryBuilder(forward.end())
                .strafeLeft(24)
                .build();
        Trajectory contForward = drive.trajectoryBuilder(strafe.end())
                .forward(24)
                .build();
        Trajectory toBackboard = drive.trajectoryBuilder(contForward.end())
                .strafeRight(120)
                .build();


        waitForStart();
        drive.followTrajectory(forward);
        drive.followTrajectory(strafe);
        drive.followTrajectory(contForward);
        drive.followTrajectory(toBackboard);
    }
}
