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
public class BlueStack extends LinearOpMode {
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
        Claw claw = new Claw(new ClawConfig(hardwareMap));

        RecognitionProcesser recognitionProcesser = new RecognitionProcesser();
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

        TrajectorySequence scorePreloadPath;
        switch (recognitionProcesser.getPosition()){
            default:
            case Center:
                scorePreloadPath = drive.trajectorySequenceBuilder(new Pose2d(-36, 66, Math.toRadians(-90)))
                        .lineToLinearHeading(new Pose2d(-36, 36, Math.toRadians(-90)))
                        .build();
                break;
            case Left:
                scorePreloadPath = drive.trajectorySequenceBuilder(new Pose2d(-36, 66, Math.toRadians(-90)))
                        .lineToLinearHeading(new Pose2d(-36, 30, Math.toRadians(0)))
                        .build();
                break;
            case Right:
                scorePreloadPath = drive.trajectorySequenceBuilder(new Pose2d(-36, 66, Math.toRadians(-90)))
                        .lineToLinearHeading(new Pose2d(-36, 30, Math.toRadians(-180)))
                        .build();
                break;
        }

        TrajectorySequence path = drive.trajectorySequenceBuilder(scorePreloadPath.end())
                .lineToConstantHeading(new Vector2d(-36, 62))
                .lineToConstantHeading(new Vector2d(30,62))

                .splineToLinearHeading(new Pose2d(50, 36, Math.toRadians(-180)), Math.toRadians(0))
                //.splineTo(new Vector2d(12, 60), Math.toRadians(-0))
                .addDisplacementMarker(()->{
                    slides.gotoPosition();
                    sleep(1500);
                    slides.scoreRotator();
                    sleep(350);
                    flicker(claw);
                    sleep(200);
                    flicker(claw);
                    sleep(200);
                    slides.resetRotator();
                    sleep(250);
                    slides.reset();
                    claw.openTop();

                })
                .lineToConstantHeading(new Vector2d(45,24))

                //.lineToConstantHeading(new Vector2d(48, 36))

                .build();

        drive.setPoseEstimate(scorePreloadPath.start());

        drive.followTrajectorySequence(scorePreloadPath);

        intake.outtake(0.5);
        sleep(500);
        intake.stop();

        drive.followTrajectorySequence(path);
    }
    public void flicker(Claw claw){
        //flick the flicker on the claw
        claw.push();
        sleep(350);
        claw.resetFlicker();
    }

}
