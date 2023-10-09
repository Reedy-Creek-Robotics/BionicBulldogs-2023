package org.firstinspires.ftc.teamcode.modules.robot;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Claw extends BaseComponent {
    static final float TOP_CLOSE = 1;
    static final float TOP_OPEN = 0.5f;
    static final float SIDE_CLOSE = 0;
    static final float SIDE_OPEN = 0.1f;

    Servo top;
    Servo side;

    public Claw(Servo top, Servo side) {
        this.top = top;
        this.side = side;

        top.setDirection(Servo.Direction.FORWARD);
        side.setDirection(Servo.Direction.FORWARD);
    }

    public void openTop() {
        top.setPosition(TOP_OPEN);
    }

    public void openSide() {
        side.setPosition(SIDE_OPEN);
    }

    public void closeTop() {
        top.setPosition(TOP_CLOSE);
    }

    public void closeSide() {
        side.setPosition(SIDE_CLOSE);
    }

    @Override
    public void addTelemetry(Telemetry telemetry) {}
}
