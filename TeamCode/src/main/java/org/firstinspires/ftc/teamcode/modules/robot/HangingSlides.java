package org.firstinspires.ftc.teamcode.modules.robot;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.opmode.config.HangingSlidesConfig;

public class HangingSlides {
    static final int upPosition = 10;
    static final int hangPosition = 5;
    static final float motorPower = 0.8f;
    DcMotor motor;
    public HangingSlides(HangingSlidesConfig cfg){
        motor = cfg.getMotor();
    }
    public void up(){
        motor.setTargetPosition(upPosition);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.setPower(motorPower);
    }
    public void hang(){
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setTargetPosition(hangPosition);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.setPower(motorPower);
    }
}
