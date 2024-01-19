package org.firstinspires.ftc.teamcode.opmode.auto.test;

import android.sax.Element;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.CameraName;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.R;
import org.firstinspires.ftc.teamcode.modules.drive.XDrive;
import org.firstinspires.ftc.teamcode.modules.robot.Claw;
import org.firstinspires.ftc.teamcode.modules.robot.ElementPosition;
import org.firstinspires.ftc.teamcode.modules.robot.Intake;
import org.firstinspires.ftc.teamcode.modules.robot.RecognitionProcesser;
import org.firstinspires.ftc.teamcode.modules.robot.RobotTeam;
import org.firstinspires.ftc.teamcode.modules.robot.Slides;
import org.firstinspires.ftc.teamcode.opmode.config.ClawConfig;
import org.firstinspires.ftc.teamcode.opmode.config.IntakeConfig;
import org.firstinspires.ftc.teamcode.opmode.config.SlideConfig;
import org.firstinspires.ftc.teamcode.opmode.config.XDriveConfig;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequenceBuilder;
import org.firstinspires.ftc.vision.VisionPortal;

public abstract class AutoBase extends LinearOpMode {
    Slides slides;
    Claw claw;
    SampleMecanumDrive drive;
    void delay(float time){
        ElapsedTime t = new ElapsedTime();
        t.reset();
        while(t.seconds() >= time){

        }
    }
    public void runOpMode(){
        drive = new SampleMecanumDrive(hardwareMap);
        drive.resetEncoders();
        Intake intake = new Intake(new IntakeConfig(hardwareMap));
        slides = new Slides(new SlideConfig(hardwareMap));
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        XDrive xDrive = new XDrive();
        xDrive.init(new XDriveConfig(hardwareMap));
        claw = new Claw(new ClawConfig(hardwareMap));

        RecognitionProcesser recognitionProcesser = new RecognitionProcesser();
        recognitionProcesser.setTeam(getTeam());
        CameraName camera = hardwareMap.get(WebcamName.class, "Webcam 1");
        VisionPortal visionPortal = new VisionPortal.Builder()
                .setCamera(camera)
                .addProcessor(recognitionProcesser)
                .build();

        while(opModeInInit()){
            telemetry.addData("initialized", recognitionProcesser.isInitialized());
            if(recognitionProcesser.isInitialized()){
                ElementPosition position = recognitionProcesser.getPosition();
                telemetry.addData("Result", position);
                telemetry.addData("region-one", recognitionProcesser.getNonZero1());
                telemetry.addData("region-two", recognitionProcesser.getNonZero2());
                telemetry.addData("region-three", recognitionProcesser.getNonZero3());
                telemetry.addData("region-one-RGB", String.format("%.2f, %.2f, %.2f",
                        RecognitionProcesser.RGBReigon1.val[0],
                                RecognitionProcesser.RGBReigon1.val[1],
                        RecognitionProcesser.RGBReigon1.val[2]
                ));
                telemetry.addData("region-two-RGB", String.format("%.2f, %.2f, %.2f",
                        RecognitionProcesser.RGBReigon2.val[0],
                        RecognitionProcesser.RGBReigon2.val[1],
                        RecognitionProcesser.RGBReigon2.val[2]
                ));
                telemetry.addData("region-three-RGB", String.format("%.2f, %.2f, %.2f",
                        RecognitionProcesser.RGBReigon3.val[0],
                        RecognitionProcesser.RGBReigon3.val[1],
                        RecognitionProcesser.RGBReigon3.val[2]
                ));
            }
            telemetry.update();
        }

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

        waitForStart();

        slides.resetRotator();
        claw.closeTop();

        TrajectorySequence scorePreloadPath;
        ElementPosition elementPosition = recognitionProcesser.getPosition();
        double preloadY = getStartPos().getY();
        float offset = (elementPosition == ElementPosition.Center ? 33 : 36);
        preloadY = preloadY + (preloadY > 0 ? -offset : offset);
        switch (elementPosition){
            default:
            case Center:
                scorePreloadPath = drive.trajectorySequenceBuilder(getStartPos())
                        .lineToLinearHeading(new Pose2d(getStartPos().getX(), preloadY, getStartPos().getHeading()))
                        .build();
                break;
            case Left:
                scorePreloadPath = drive.trajectorySequenceBuilder(getStartPos())
                        .lineToLinearHeading(new Pose2d(getStartPos().getX() + (getTeam() == RobotTeam.Blue ? 2 : 0), preloadY, getStartPos().getHeading() + Math.toRadians(90)))
                        .build();
                break;
            case Right:
                scorePreloadPath = drive.trajectorySequenceBuilder(getStartPos())
                        .lineToLinearHeading(new Pose2d(getStartPos().getX() + (getTeam() == RobotTeam.Red ? 2 : 0), preloadY, getStartPos().getHeading() - Math.toRadians(90)))
                        .build();
                break;
        }

        TrajectorySequence path = getTrajectory(scorePreloadPath.end(), drive, elementPosition);

        drive.setPoseEstimate(scorePreloadPath.start());

        drive.followTrajectorySequence(scorePreloadPath);

        intake.outtake(0.3);
        sleep(500);
        intake.stop();

        drive.followTrajectorySequence(path);

        scoreOnBackboard();
        sleep(1000);
        SampleMecanumDrive.posEstimate = drive.getPoseEstimate();
    }

    public abstract Pose2d getStartPos();
    public abstract RobotTeam getTeam();
    public abstract TrajectorySequence getTrajectory(Pose2d startPos, SampleMecanumDrive drive, ElementPosition elementPosition);
    public void flicker(Claw claw){
        //flick the flicker on the claw
        claw.push();
        sleep(350);
        claw.resetFlicker();
    }
    public void scoreOnBackboard(){
        slides.gotoPosition();
        sleep(1500);
        slides.scoreRotatorAuto();
        sleep(350);
        flicker(claw);
        sleep(200);
        flicker(claw);
        sleep(200);
        drive.followTrajectorySequence(
                drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                        .forward(5)
                        .build()
        );
        slides.resetRotator();
        sleep(500);
        slides.reset();
        claw.openTop();
    }

}
