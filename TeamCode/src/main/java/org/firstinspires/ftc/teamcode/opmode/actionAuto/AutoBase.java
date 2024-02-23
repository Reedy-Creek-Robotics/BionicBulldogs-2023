package org.firstinspires.ftc.teamcode.opmode.actionAuto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.profile.AccelerationConstraint;
import com.acmerobotics.roadrunner.profile.VelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryAccelerationConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryVelocityConstraint;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.CameraName;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.modules.auto.actions.Action_Base;
import org.firstinspires.ftc.teamcode.modules.drive.XDrive;
import org.firstinspires.ftc.teamcode.modules.robot.Claw;
import org.firstinspires.ftc.teamcode.modules.robot.DriveToAprilTag;
import org.firstinspires.ftc.teamcode.modules.robot.ElementPosition;
import org.firstinspires.ftc.teamcode.modules.robot.Intake;
import org.firstinspires.ftc.teamcode.modules.robot.RecognitionProcesser;
import org.firstinspires.ftc.teamcode.modules.robot.Robot;
import org.firstinspires.ftc.teamcode.modules.robot.RobotTeam;
import org.firstinspires.ftc.teamcode.modules.robot.Slides;
import org.firstinspires.ftc.teamcode.opmode.config.ClawConfig;
import org.firstinspires.ftc.teamcode.opmode.config.IntakeConfig;
import org.firstinspires.ftc.teamcode.opmode.config.SlideConfig;
import org.firstinspires.ftc.teamcode.opmode.config.XDriveConfig;
import org.firstinspires.ftc.teamcode.roadrunner.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.vision.VisionPortal;

import java.nio.ReadOnlyBufferException;
import java.util.List;

public abstract class AutoBase extends LinearOpMode {
    Slides slides;
    Claw claw;
    SampleMecanumDrive drive;
    DriveToAprilTag driveToAprilTag;
    public void runOpMode(){
        drive = new SampleMecanumDrive(hardwareMap);
        drive.resetEncoders();
        Intake intake = new Intake(new IntakeConfig(hardwareMap));
        slides = new Slides(new SlideConfig(hardwareMap));
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        XDrive xDrive = new XDrive();
        xDrive.init(new XDriveConfig(hardwareMap));
        claw = new Claw(new ClawConfig(hardwareMap));

        Robot.drive = drive;
        Robot.claw = claw;
        Robot.slides = slides;
        Robot.intake = intake;

        RobotTeam team;

        if(getStartPos().getY() > 0){
            team = RobotTeam.Blue;
        }else{
            team = RobotTeam.Red;
        }

        RecognitionProcesser recognitionProcesser = new RecognitionProcesser();
        recognitionProcesser.setTeam(team);
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

        if(!opModeIsActive()){
            return;
        }
        waitForStart();
        visionPortal.close();
        driveToAprilTag = new DriveToAprilTag(xDrive, this, "Webcam 2", false);
        Robot.driveToAprilTag = driveToAprilTag;
        ElementPosition elementPosition = recognitionProcesser.getPosition();
        slides.resetRotator();
        claw.closeTop();
        claw.flicker();

        TrajectorySequence purplePreloadPath = getPreloadPath(elementPosition);
        List<Action_Base> actions = getActions(purplePreloadPath.end(), elementPosition);

        drive.setPoseEstimate(purplePreloadPath.start());

        //Drop-off purple pixel
        drive.followTrajectorySequence(purplePreloadPath);

        intake.outtake(0.3);
        sleep(500);
        intake.stop();

        for(Action_Base action : actions){
            action.run();
        }

        SampleMecanumDrive.posEstimate = drive.getPoseEstimate();
        telemetry.addData("end pos estimate", SampleMecanumDrive.posEstimate);
        telemetry.update();
        sleep(1000);
    }

    public abstract Pose2d getStartPos();

    public TrajectorySequence getPreloadPath(ElementPosition elementPosition){
        RobotTeam team;

        if(getStartPos().getY() > 0){
            team = RobotTeam.Blue;
        }else{
            team = RobotTeam.Red;
        }

        TrajectorySequence purplePreloadPath;

        //Y position for dropping off purple preload
        double preloadY = getStartPos().getY();
        float offset = (elementPosition == ElementPosition.Center ? 28.5f : 31.5f);
        preloadY = preloadY + (preloadY > 0 ? -offset : offset);

        TrajectoryVelocityConstraint vel = SampleMecanumDrive.getVelocityConstraint(55, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH);
        TrajectoryAccelerationConstraint accel = SampleMecanumDrive.getAccelerationConstraint(20);

        //Define trajectory for dropping purple pixel
        switch (elementPosition){
            default:
            case Center:
                purplePreloadPath = drive.trajectorySequenceBuilder(getStartPos())
                        .lineToLinearHeading(new Pose2d(getStartPos().getX(), preloadY, getStartPos().getHeading()), vel, accel)
                        .build();
                break;
            case Left:
                purplePreloadPath = drive.trajectorySequenceBuilder(getStartPos())
                        .lineToLinearHeading(new Pose2d(getStartPos().getX() - (team == RobotTeam.Blue ? -0.5 : 0), preloadY, getStartPos().getHeading() + Math.toRadians(90)), vel, accel)
                        .build();
                break;
            case Right:
                purplePreloadPath = drive.trajectorySequenceBuilder(getStartPos())
                        .lineToLinearHeading(new Pose2d(getStartPos().getX() - (team == RobotTeam.Blue ? 0.5 : -0.5), preloadY, getStartPos().getHeading() - Math.toRadians(90)), vel, accel)
                        .build();
                break;
        }
        return purplePreloadPath;
    }

    public abstract List<Action_Base> getActions(Pose2d startPos, ElementPosition elementPosition);

}