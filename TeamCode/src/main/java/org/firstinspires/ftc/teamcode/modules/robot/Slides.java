package org.firstinspires.ftc.teamcode.modules.robot;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.modules.hardware.MotorGroup;
import org.firstinspires.ftc.teamcode.opmode.config.SlideConfig;

public class Slides {
    DcMotor motor;
    int lastPosition = 0;
    int pixelHeight = 20; //encoder ticks to go up one pixel
    float slideSpeed = 0.5f;
    public Slides(SlideConfig cfg){
        motor = cfg.getLeft();
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
    public void telem(Telemetry t){
        t.addLine("Slides");
        t.addData("last position", lastPosition);
        t.addData("current position", motor.getCurrentPosition());
        t.addData("target position", motor.getTargetPosition());
    }
}
