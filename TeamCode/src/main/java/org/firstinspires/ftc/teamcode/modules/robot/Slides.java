package org.firstinspires.ftc.teamcode.modules.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.modules.hardware.MotorGroup;
import org.firstinspires.ftc.teamcode.opmode.config.SlideConfig;

public class Slides {
    DcMotor motor;
    Servo servo;
    int lastPosition = 0;
    int pixelHeight = 20; //encoder ticks to go up one pixel
    int slidePosToMoveServo = -1000;
    float slideSpeed = 0.8f;
    float servoStartPos = 0.5f;
    float servoScorePos = 0.2f;
    public Slides(SlideConfig cfg){
        servo = cfg.getRotator();
        motor = cfg.getMotor();
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    public void up1Pixel(){
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setTargetPosition(motor.getCurrentPosition() + pixelHeight);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.setPower(slideSpeed);
        lastPosition++;
    }
    public void down1Pixel(){
        if(lastPosition == 0) return;
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setTargetPosition(motor.getCurrentPosition() - pixelHeight);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.setPower(slideSpeed);
        lastPosition--;
    }
    public void goToLastPosition(){
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setTargetPosition(pixelHeight * lastPosition);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.setPower(slideSpeed);
    }
    public void reset(){
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setTargetPosition(0);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.setPower(slideSpeed);
    }
    public void resetLastPosition(){
        lastPosition = 0;
    }
    public void setPower(float power){
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setPower(power * slideSpeed);
    }
    public void updateClawServo(){
        if(motor.getCurrentPosition() > slidePosToMoveServo){
            servo.setPosition(servoScorePos);
        }else{
            servo.setPosition(servoStartPos);
        }
    }
    public void resetRotator(){
        servo.setPosition(servoStartPos);
    }
    public void scoreRotator(){
        servo.setPosition(servoScorePos);
    }
    public void telem(Telemetry t){
        t.addLine("Slides");
        t.addData("power", motor.getPower());
        t.addData("last position", lastPosition);
        t.addData("current position", motor.getCurrentPosition());
        t.addData("target position", motor.getTargetPosition());
    }
}
