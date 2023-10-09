package org.firstinspires.ftc.teamcode.modules;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.opmode.config.ClawConfig;

public class Claw implements ClawSample {
    float topClose = 1;
    float topOpen = 0.5f;
    float sideClose = 0;
    float sideOpen = 0.1f;

    Servo top;
    Servo side;

    public Claw(ClawConfig config) {
        top = config.getTop();
        side = config.getSide();
        top.setDirection(Servo.Direction.FORWARD);
        side.setDirection(Servo.Direction.FORWARD);
    }

    @Override
    public void openTop() {
        top.setPosition(topOpen);
    }

    @Override
    public void openSide() {
        side.setPosition(sideOpen);
    }

    @Override
    public void closeTop() {
        top.setPosition(topClose);
    }

    @Override
    public void closeSide() {
        side.setPosition(sideClose);
    }

    public void openSideForTime() {

    }
}
