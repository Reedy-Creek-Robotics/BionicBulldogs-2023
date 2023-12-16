package org.firstinspires.ftc.teamcode.opmode.auto.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.apache.commons.math3.geometry.euclidean.twod.Line;
import org.firstinspires.ftc.ftccommon.internal.manualcontrol.ManualControlOpMode;
import org.firstinspires.ftc.teamcode.modules.drive.XDrive;
import org.firstinspires.ftc.teamcode.modules.robot.DriveToAprilTag;
import org.firstinspires.ftc.teamcode.modules.robot.Intake;
import org.firstinspires.ftc.teamcode.modules.robot.Recognition;
import org.firstinspires.ftc.teamcode.modules.robot.Slides;
import org.firstinspires.ftc.teamcode.opmode.config.IntakeConfig;
import org.firstinspires.ftc.teamcode.opmode.config.SlideConfig;
import org.firstinspires.ftc.teamcode.opmode.config.XDriveConfig;
import org.firstinspires.ftc.teamcode.roadrunner.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;

@Autonomous
public class RoadRunnerTestBlue extends LinearOpMode {
    void delay(float time){
        ElapsedTime t = new ElapsedTime();
        t.reset();
        while(t.seconds() >= time){

        }
    }
    public void runOpMode(){
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        drive.resetEncoders();
        Intake intake = new Intake(new IntakeConfig(hardwareMap));
        Slides slides = new Slides(new SlideConfig(hardwareMap));
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        XDrive xDrive = new XDrive();
        xDrive.init(new XDriveConfig(hardwareMap));
/*
        Recognition recognition = new Recognition(this);
        while (!recognition.getInitialized()){
            telemetry.addData("recognition", "NOT INITIALIZED");
            telemetry.update();
        }
        telemetry.addData("recognition", "INITIALIZED");
        telemetry.update();

        Recognition.SkystoneDeterminationPipeline.ElementPosition position = recognition.getBarcode();
        telemetry.addData("position", position);
        telemetry.update();
*/
//        TrajectorySequence path = drive.trajectorySequenceBuilder(new Pose2d(-36, 66, Math.toRadians(-90)))
//                .splineTo(new Vector2d(-36, 30), Math.toRadians(-90))
//                .waitSeconds(1)
//                .lineToConstantHeading(new Vector2d(-36, 48))
//                .splineTo(new Vector2d(-24, 60), Math.toRadians(-0))
//                .splineTo(new Vector2d(12, 60), Math.toRadians(-0))
//                .lineToConstantHeading(new Vector2d(48, 36))
//                .build();

        TrajectorySequence path = drive.trajectorySequenceBuilder(new Pose2d(-36, 66, Math.toRadians(-90)))
                .splineTo(new Vector2d(-36, 32), Math.toRadians(-90))
                .addDisplacementMarker(() -> {
                    intake.outtake(-0.3);
                })
                //.waitSeconds(1)
                .lineToConstantHeading(new Vector2d(-36, 48))
                .addDisplacementMarker(() -> {
                    intake.stop();
                })
                .splineTo(new Vector2d(-24, 60), Math.toRadians(-0))
                .splineTo(new Vector2d(12, 60), Math.toRadians(-0))
                .addDisplacementMarker(()->{
                    slides.gotoPosition();
                })
                .lineToConstantHeading(new Vector2d(48, 36))
                .build();

         drive.setPoseEstimate(path.start());

        waitForStart();

        //drive.followTrajectorySequence(path);
    }
}
