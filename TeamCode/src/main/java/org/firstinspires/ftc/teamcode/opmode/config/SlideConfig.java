package org.firstinspires.ftc.teamcode.opmode.config;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class SlideConfig {
    HardwareMap hmap;
    public SlideConfig(HardwareMap h){
        hmap = h;
    }
    public DcMotor getLeft(){
        return hmap.dcMotor.get("slideLeft");
    }
    public DcMotor getRight(){
        return hmap.dcMotor.get("slideRight");
    }
}
