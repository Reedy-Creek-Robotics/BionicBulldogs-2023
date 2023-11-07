package org.firstinspires.ftc.teamcode.modules.robot;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.opmode.config.ClawConfig;

public class Claw extends BaseComponent {
    static final float TOP_CLOSE = 0;
    static final float TOP_OPEN = 0.35f;
    static final float SIDE_CLOSE = 0;
    static final float SIDE_OPEN = 0.25f;

    Servo top;
    Servo side;

    int scoringStep = 0;
    ElapsedTime elapsedTime;

    boolean scoring = false;

    public Claw(ClawConfig config) {
        this.top = config.getTop();
        this.side = config.getSide();

        top.setDirection(Servo.Direction.FORWARD);
        side.setDirection(Servo.Direction.FORWARD);

        top.setPosition(TOP_CLOSE);
        side.setPosition(SIDE_CLOSE);

        elapsedTime = new ElapsedTime();
    }

    public void openTop() {
        top.setPosition(TOP_OPEN);
    }

    public void closeTop() {
        top.setPosition(TOP_CLOSE);
    }

    public void score(){
        scoring = true;
        side.setPosition(SIDE_OPEN);
        elapsedTime.reset();
    }

    public void scoreUpdate(){
        if(scoring){
            if(elapsedTime.seconds() >= 0.5f){
                scoring = false;
                side.setPosition(SIDE_CLOSE);
            }
        }
    }

    public void addTelemetry(Telemetry telemetry) {}
}
