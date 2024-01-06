package org.firstinspires.ftc.teamcode.opmode.teleop.test;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.opmode.teleop.BaseTeleOp;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;

@TeleOp
public class LineTest extends BaseTeleOp {
    SampleMecanumDrive drive;
    public void init(){
        drive = new SampleMecanumDrive(hardwareMap);
        drive.setPoseEstimate(new Pose2d(0, 0, Math.toRadians(0)));
    }
    public void loop(){
        if(gamepadEx1.cross()){
            drive.followTrajectorySequence(
                    drive.trajectorySequenceBuilder(new Pose2d(0, 0, Math.toRadians(0)))
                            .lineTo(new Vector2d(0, 24))
                            .build()
            );
        }
        if(gamepadEx1.circle()){
            drive.followTrajectorySequence(
                    drive.trajectorySequenceBuilder(new Pose2d(0, 0, Math.toRadians(0)))
                            .lineToLinearHeading(new Pose2d(0, 24, Math.toRadians(90)))
                            .build()
            );
        }
        if(gamepadEx1.triangle()){
            drive.followTrajectorySequence(
                    drive.trajectorySequenceBuilder(new Pose2d(0, 0, Math.toRadians(0)))
                            .turn(Math.toRadians(90))
                            .lineToConstantHeading(new Vector2d(0, 24))
                            .build()
            );
        }
    }
}
