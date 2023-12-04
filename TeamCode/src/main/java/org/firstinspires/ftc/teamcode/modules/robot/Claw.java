package org.firstinspires.ftc.teamcode.modules.robot;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.opmode.config.ClawConfig;

public class Claw extends BaseComponent {
    //top motor position for open
    static final float TOP_OPEN = 0;
    //top motor position for close
    static final float TOP_CLOSE = 0.18f;
    //flicker default position
    static final float SIDE_CLOSE = 0;
    //flicker position for pushing a pixel out
    static final float SIDE_OPEN = 0.25f;

    Servo top;
    Servo side;

    int scoringStep = 0;
    int pixelsToScore = 0;
    ElapsedTime elapsedTime;

    public Claw(ClawConfig config) {
        top = config.getClaw();
        side = config.getFlicker();

        elapsedTime = new ElapsedTime();
    }

    public void initServos(){
        top.setDirection(Servo.Direction.FORWARD);
        side.setDirection(Servo.Direction.FORWARD);

        top.setPosition(TOP_OPEN);
        side.setPosition(SIDE_CLOSE);
    }

    public void openTop() {
        top.setPosition(TOP_OPEN);
    }

    public void closeTop() {
        top.setPosition(TOP_CLOSE);
    }

    public void score(){
        score(1);
    }
    public void score(int pixelCount){
        pixelsToScore = pixelCount;
        elapsedTime.reset();
    }

    public void scoreUpdate(){
        if(pixelsToScore > 0){
            if(scoringStep == 0 && elapsedTime.seconds() >= 0.5f){
                side.setPosition(SIDE_OPEN);
                elapsedTime.reset();
                scoringStep = 1;
            }
            if(scoringStep == 1 && elapsedTime.seconds() >= 0.5f){
                scoringStep = 0;
                elapsedTime.reset();
                side.setPosition(SIDE_CLOSE);
                pixelsToScore--;
            }
        }
    }

    public void addTelemetry(Telemetry telemetry) {}
}
