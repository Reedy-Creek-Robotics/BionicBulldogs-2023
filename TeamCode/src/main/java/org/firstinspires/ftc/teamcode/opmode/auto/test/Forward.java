package org.firstinspires.ftc.teamcode.opmode.auto.test;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
@Autonomous
public class Forward extends LinearOpMode {
    public void runOpMode(){
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Trajectory trajectory = drive.trajectoryBuilder(new Pose2d())
                .forward(72).build();

        ElapsedTime elapsedTime = new ElapsedTime();

        waitForStart();

        elapsedTime.reset();
        drive.followTrajectory(trajectory);
        telemetry.addData("time", elapsedTime.milliseconds());
        telemetry.update();
        while (opModeIsActive());
    }
}
