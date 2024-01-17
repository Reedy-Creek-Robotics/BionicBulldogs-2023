package org.firstinspires.ftc.teamcode.opmode.auto.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.CameraName;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.modules.drive.XDrive;
import org.firstinspires.ftc.teamcode.modules.robot.Claw;
import org.firstinspires.ftc.teamcode.modules.robot.ElementPosition;
import org.firstinspires.ftc.teamcode.modules.robot.Intake;
import org.firstinspires.ftc.teamcode.modules.robot.RecognitionProcesser;
import org.firstinspires.ftc.teamcode.modules.robot.Slides;
import org.firstinspires.ftc.teamcode.opmode.config.ClawConfig;
import org.firstinspires.ftc.teamcode.opmode.config.IntakeConfig;
import org.firstinspires.ftc.teamcode.opmode.config.SlideConfig;
import org.firstinspires.ftc.teamcode.opmode.config.XDriveConfig;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequenceBuilder;
import org.firstinspires.ftc.vision.VisionPortal;

@Autonomous
public class BlueStack extends AutoBase {
    public Pose2d getStartPos() {
        return new Pose2d(-36, 66, Math.toRadians(-90));
    }
    public TrajectorySequence getTrajectory(Pose2d startPos, SampleMecanumDrive drive, ElementPosition elementPosition){
        TrajectorySequenceBuilder builder = drive.trajectorySequenceBuilder(startPos)
                .lineToLinearHeading(new Pose2d(-36, 62, Math.toRadians(-90)))
                .lineToConstantHeading(new Vector2d(30,62));
        switch(elementPosition){
            case Right:
                builder.lineToLinearHeading(new Pose2d(54, 36, Math.toRadians(185)));
                break;
            case Center:
                builder.lineToLinearHeading(new Pose2d(54, 42, Math.toRadians(185)));
                break;
            case Left:
                builder.lineToLinearHeading(new Pose2d(54, 46, Math.toRadians(185)));
                break;
        }
        return builder.build();
    }
}
