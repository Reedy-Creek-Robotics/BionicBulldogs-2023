package org.firstinspires.ftc.teamcode.modules.robot;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.modules.hardware.MotorGroup;
import org.firstinspires.ftc.teamcode.opmode.config.SlideConfig;
@Config
public class Slides {
    public static int lastPosition = 0;
    public static int pixelHeight = 20; //encoder ticks to go up one pixel
    public static int slidePosToMoveServo = -1000;
    public static double slideSpeed = 0.8f;
    public static double servoStartPos = 0.975f; //0.47f;
    public static double servoScorePos = 0.2f; //0.2f;
    DcMotor motor;
    Servo servo;

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
        if(power == 0 && motor.getMode() == DcMotor.RunMode.RUN_TO_POSITION){
            return;
        }
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setPower(power * slideSpeed);
    }
    public void updateClawServo(){
        if(motor.getCurrentPosition() < slidePosToMoveServo){
            servo.setPosition(servoScorePos);
        }else if(motor.getCurrentPosition() < -200) {
            servo.setPosition(0.49);
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
    public void scoreRotator(float position){
        servo.setPosition(position);
    }
    public void toggleRotator(){
        if(servo.getPosition() == servoScorePos){
            servo.setPosition(servoStartPos);
        }else{
            servo.setPosition(servoScorePos);
        }
    }
    public void gotoPosition(){
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setTargetPosition(-975); //-800
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.setPower(slideSpeed);
    }
    public void telem(Telemetry t){
        t.addData("(slides)power", motor.getPower());
        t.addData("(slides)last position", lastPosition);
        t.addData("(slides)current position", motor.getCurrentPosition());
        t.addData("(slides)target position", motor.getTargetPosition());
    }
}