package org.firstinspires.ftc.teamcode.opmode.auto.test;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;

import java.util.List;

@Autonomous
@Disabled
public class Forward extends LinearOpMode {
    public void runOpMode(){
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Trajectory trajectory = drive.trajectoryBuilder(new Pose2d())
                .forward(72).build();

        ElapsedTime elapsedTime = new ElapsedTime();

        waitForStart();

        elapsedTime.reset();
        drive.followTrajectoryAsync(trajectory);
        while (opModeIsActive()){
            drive.update();
            List<Double> position = drive.getWheelPositions();
            for(int i = 0; i < 4; i++){
                telemetry.addData(i + "", position.get(i));
            }
            telemetry.update();
        }
    }
}
