package org.firstinspires.ftc.teamcode.modules.robot;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.opmode.config.SlideConfig;
@Config
public class Slides {
    public static int lastPosition = 0;
    public static int pixelHeight = 20; //encoder ticks to go up one pixel
    public static int slidePosToMoveServo = -1000;
    public static double slideSpeedUp = 1.0f;
    public static double slideSpeedDown = 0.8f;
    public static double servoStartPos = 0.98f; //0.47f;
    public static double servoScorePosAuto = 0.2f; //0.2f;
    public static double servoScorePosTelop = 0.45f;
    DcMotor motor;
    Servo servo;
    boolean prevServoPos = false;

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
        motor.setPower(slideSpeedUp);
        lastPosition++;
    }
    public void down1Pixel(){
        if(lastPosition == 0) return;
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setTargetPosition(motor.getCurrentPosition() - pixelHeight);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.setPower(slideSpeedUp);
        lastPosition--;
    }
    public void goToLastPosition(){
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setTargetPosition(pixelHeight * lastPosition);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.setPower(slideSpeedUp);
    }
    public void reset(){
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setTargetPosition(0);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.setPower(slideSpeedDown);
    }
    public void resetLastPosition(){
        lastPosition = 0;
    }
    public void setPower(float power){
        if(power == 0 && motor.getMode() == DcMotor.RunMode.RUN_TO_POSITION){
            return;
        }
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setPower(power * slideSpeedUp);
    }
    public void updateClawServo(){
        if(motor.getCurrentPosition() > -100){
            servo.setPosition(servoStartPos);
        }
    }
    public void resetEncoder(){
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public void resetRotator(){
        servo.setPosition(servoStartPos);
    }
    public void scoreRotator(){
        servo.setPosition(servoScorePosTelop);
    }
    public void scoreRotatorAuto(){servo.setPosition(servoScorePosAuto);}
    public void scoreRotator(float position){
        servo.setPosition(position);
    }
    public void toggleRotator(){
        if(Math.abs(servo.getPosition() - servoScorePosTelop) < 0.11f){
            servo.setPosition(servoStartPos);
        }else{
            servo.setPosition(servoScorePosTelop);
        }
    }
    public void gotoPosition(int pos){
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setTargetPosition(pos); //-800
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.setPower(slideSpeedUp);
    }
    public void gotoPositionBlock(int pos){
        gotoPosition(pos);
        while(motor.isBusy());
    }

    public void gotoPositionBlock(){
        gotoPositionBlock(-758);
    }
    public void telem(Telemetry t){
        t.addData("(slides)power", motor.getPower());
        t.addData("(slides)last position", lastPosition);
        t.addData("(slides)current position", motor.getCurrentPosition());
        t.addData("(slides)target position", motor.getTargetPosition());
    }
}