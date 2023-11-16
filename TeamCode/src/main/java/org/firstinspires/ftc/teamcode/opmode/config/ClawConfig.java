package org.firstinspires.ftc.teamcode.opmode.config;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ClawConfig {
    HardwareMap hw;
    public ClawConfig(HardwareMap hmap){
        hw = hmap;
    }
    public Servo getClaw(){
        return hw.servo.get("claw");
    }
    public Servo getFlicker(){
        return hw.servo.get("flicker");
    }
}