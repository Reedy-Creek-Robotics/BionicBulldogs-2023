package org.firstinspires.ftc.teamcode.opmode.config;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class SlideConfig {
    HardwareMap hmap;
    public SlideConfig(HardwareMap h){
        hmap = h;
    }
    public DcMotor getMotor(){
        return hmap.dcMotor.get("slides");
    }
    public Servo getRotator(){
        return hmap.servo.get("rotator");
    }
}




