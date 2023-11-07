package org.firstinspires.ftc.teamcode.opmode.config;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class DroneLauncherConfig {
    HardwareMap hmap;
    public DroneLauncherConfig(HardwareMap h){
        hmap = h;
    }
    public Servo getServo(){
        return hmap.servo.get("droneServo");
    }
}
