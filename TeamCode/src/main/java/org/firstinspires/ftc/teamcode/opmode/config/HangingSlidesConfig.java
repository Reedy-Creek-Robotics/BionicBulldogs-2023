package org.firstinspires.ftc.teamcode.opmode.config;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class HangingSlidesConfig {
    HardwareMap hmap;
    public HangingSlidesConfig(HardwareMap h){
        hmap = h;
    }
    public DcMotor getMotor(){
        return hmap.dcMotor.get("hangingMotor");
    }
}
