package org.firstinspires.ftc.teamcode.modules.robot;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.opmode.config.ClawConfig;

public class Claw {
    //top motor position for open
    static final float TOP_OPEN = 0.2f;
    //top motor position for close
    static final float TOP_CLOSE = 0.33f;
    //flicker default position
    static final float SIDE_CLOSE = 0.0f;
    //flicker position for pushing a pixel out
    static final float SIDE_OPEN = 0.35f;

    Servo top;
    Servo side;

    int scoringStep = 0;
    ElapsedTime elapsedTime;

    boolean scoring = false;

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

    public void toggleTop(){
        if(Math.abs(top.getPosition() - TOP_OPEN) < 0.11f){
            top.setPosition(TOP_CLOSE);
        }else{
            top.setPosition(TOP_OPEN);
        }
    }
    public void push(){
        side.setPosition(SIDE_OPEN);
    }

    public void resetFlicker(){
        side.setPosition(SIDE_CLOSE);
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

    public void telem(Telemetry telemetry){
        telemetry.addData("(claw)top position", top.getPosition());
        telemetry.addData("(claw)side position", side.getPosition());
    }
}
