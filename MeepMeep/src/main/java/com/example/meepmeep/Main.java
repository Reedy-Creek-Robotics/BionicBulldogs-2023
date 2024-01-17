package com.example.meepmeep;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class Main {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                .setDimensions(12, 12)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 12.8)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36, -66, Math.toRadians(90)))
                                .splineTo(new Vector2d(-36, -36), Math.toRadians(90))
                                .turn(Math.toRadians(-0))
                                .splineTo(new Vector2d(-60, -36), Math.toRadians(180))
                                .turn(Math.toRadians(-0))
                                .splineTo(new Vector2d(-60, -24), Math.toRadians(90))
                                .splineTo(new Vector2d(-48, -12), Math.toRadians(-0))
                                .splineTo(new Vector2d(24, -12), Math.toRadians(-0))
                                .splineTo(new Vector2d(48, -36), Math.toRadians(-0))
                                .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}