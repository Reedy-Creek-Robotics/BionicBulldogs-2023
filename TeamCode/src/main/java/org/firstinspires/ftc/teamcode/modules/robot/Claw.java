package org.firstinspires.ftc.teamcode.modules.robot;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.opmode.config.ClawConfig;

public class Claw extends BaseComponent {
    static final float TOP_CLOSE = 1;
    static final float TOP_OPEN = 0.5f;
    static final float SIDE_CLOSE = 0;
    static final float SIDE_OPEN = 0.1f;

    Servo top;
    Servo side;

    public Claw(ClawConfig config) {
        this.top = config.getTop();
        this.side = config.getSide();

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

    public void addTelemetry(Telemetry telemetry) {}
}
