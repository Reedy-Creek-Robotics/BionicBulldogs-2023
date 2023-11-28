package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.DriveShim;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
import com.noahbres.meepmeep.roadrunner.trajectorysequence.TrajectorySequence;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(1000);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(-90), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(12, 63, Math.toRadians(-90)))
                                .lineToLinearHeading(new Pose2d(12, 30, Math.toRadians(180)))
                                .strafeLeft(17)
                                .back(20)
                                .splineToConstantHeading(new Vector2d(50, 29), Math.toRadians(0))
                                .strafeTo(new Vector2d(24, 11))
                                .forward(80)
                                .back(80)
                                .splineToConstantHeading(new Vector2d(50, 29), Math.toRadians(0))
                                .strafeTo(new Vector2d(24, 11))
                                .forward(80)
                                .back(80)
                                .splineToConstantHeading(new Vector2d(50, 29), Math.toRadians(0))
                                .build()




                );
        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }

    public static TrajectorySequence blueSideBoardRandomizeMiddle(DriveShim drive) {
        TrajectorySequence sequence = drive.trajectorySequenceBuilder(new Pose2d(12, 63, Math.toRadians(-90)))
                .forward(30)
                .lineToLinearHeading(new Pose2d(44, 35.2, Math.toRadians(180)))
                .forward(100)
                .back(100)
                .forward(100)
                .back(100)
                .build();
        return sequence;
    }

    public static TrajectorySequence blueSideBoardRandomizeLeft(DriveShim drive) {
        TrajectorySequence sequence = drive.trajectorySequenceBuilder(new Pose2d(12, 63, Math.toRadians(-90)))
                .lineToLinearHeading(new Pose2d(12, 30, Math.toRadians(-180)))
                .strafeLeft(17)
                .back(20)
                .splineToConstantHeading(new Vector2d(50, 29), Math.toRadians(0))
                .strafeTo(new Vector2d(24, 11))
                .forward(80)
                .back(80)
                .splineToConstantHeading(new Vector2d(50, 29), Math.toRadians(0))
                .strafeTo(new Vector2d(24, 11))
                .forward(80)
                .back(80)
                .splineToConstantHeading(new Vector2d(50, 29), Math.toRadians(0))
                .build();

        return sequence;
    }

    public static TrajectorySequence blueSideBoardRandomizeRight(DriveShim drive) {
        TrajectorySequence sequence = drive.trajectorySequenceBuilder(new Pose2d(12, 63, Math.toRadians(-90)))
                .lineToLinearHeading(new Pose2d(12, 30, Math.toRadians(180)))
                .strafeLeft(17)
                .back(20)
                .splineToConstantHeading(new Vector2d(50, 29), Math.toRadians(0))
                .strafeTo(new Vector2d(24, 11))
                .forward(80)
                .back(80)
                .splineToConstantHeading(new Vector2d(50, 29), Math.toRadians(0))
                .strafeTo(new Vector2d(24, 11))
                .forward(80)
                .back(80)
                .splineToConstantHeading(new Vector2d(50, 29), Math.toRadians(0))
                .build();

        return sequence;
    }

    public static TrajectorySequence redSideBoardRandomizeMiddle(DriveShim drive) {
        TrajectorySequence sequence = drive.trajectorySequenceBuilder(new Pose2d(12, -63, Math.toRadians(90)))
                .forward(30)
                .lineToLinearHeading(new Pose2d(44, -35.2, Math.toRadians(180)))
                .forward(100)
                .back(100)
                .forward(100)
                .back(100)
                .build();
        return sequence;
    }

    public static TrajectorySequence redSideBoardRandomizeLeft(DriveShim drive) {
        TrajectorySequence sequence = drive.trajectorySequenceBuilder(new Pose2d(12, -63, Math.toRadians(90)))
                .lineToLinearHeading(new Pose2d(12, -30, Math.toRadians(180)))
                .strafeRight(17)
                .back(20)
                .splineToConstantHeading(new Vector2d(50, -29), Math.toRadians(0))
                .strafeTo(new Vector2d(24, -11))
                .forward(80)
                .back(80)
                .splineToConstantHeading(new Vector2d(50, -29), Math.toRadians(0))
                .strafeTo(new Vector2d(24, -11))
                .forward(80)
                .back(80)
                .splineToConstantHeading(new Vector2d(50, -29), Math.toRadians(0))
                .build();

        return sequence;
    }

    public static TrajectorySequence redSideBoardRandomizeRight(DriveShim drive) {
        TrajectorySequence sequence = drive.trajectorySequenceBuilder(new Pose2d(12, -63, Math.toRadians(90)))
                .lineToLinearHeading(new Pose2d(12, -30, Math.toRadians(-180)))
                .strafeRight(17)
                .back(20)
                .splineToConstantHeading(new Vector2d(50, -29), Math.toRadians(0))
                .strafeTo(new Vector2d(24, -11))
                .forward(80)
                .back(80)
                .splineToConstantHeading(new Vector2d(50, -29), Math.toRadians(0))
                .strafeTo(new Vector2d(24, -11))
                .forward(80)
                .back(80)
                .splineToConstantHeading(new Vector2d(50, -29), Math.toRadians(0))
                .build();

        return sequence;
    }
}