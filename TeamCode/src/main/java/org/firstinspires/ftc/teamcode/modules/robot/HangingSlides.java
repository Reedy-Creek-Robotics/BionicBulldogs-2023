package org.firstinspires.ftc.teamcode.modules.robot;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.opmode.config.HangingSlidesConfig;

public class HangingSlides {
    static final int upPosition = 4300;
    static final int hangPosition = 1000;
    static final float motorPower = 1.0f;
    DcMotor motor;
    public HangingSlides(HangingSlidesConfig cfg){
        motor = cfg.getMotor();
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
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
    public void togglePosition(){
        if(motor.getTargetPosition() == hangPosition){
            up();
        }else{
            hang();
        }
    }
    public void telem(Telemetry telem){
        telem.addData("hanging slide pos", motor.getCurrentPosition());
    }
}
