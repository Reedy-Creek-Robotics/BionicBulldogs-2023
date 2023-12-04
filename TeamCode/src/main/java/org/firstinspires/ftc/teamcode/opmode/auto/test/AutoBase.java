package org.firstinspires.ftc.teamcode.opmode.auto.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;

public abstract class AutoBase extends LinearOpMode {
    public void runOpMode(){
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        TrajectorySequence trajectory = getTrajectory(drive);
        waitForStart();
        drive.followTrajectorySequence(trajectory);
    }
    public abstract TrajectorySequence getTrajectory(SampleMecanumDrive drive);
}
