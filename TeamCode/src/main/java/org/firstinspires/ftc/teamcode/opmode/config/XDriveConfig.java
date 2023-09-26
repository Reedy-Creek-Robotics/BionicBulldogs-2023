package org.firstinspires.ftc.teamcode.opmode.config;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
public class XDriveConfig {
    String front = "front";
    String back = "back";
    String left  = "left";
    String right = "right";

    HardwareMap hw;

    public XDriveConfig(HardwareMap hmap){hw = hmap;}

    public DcMotor getFront(){
        return hw.dcMotor.get(front);
    }
    public DcMotor getBack(){
        return hw.dcMotor.get(back);
    }
    public DcMotor getLeft(){
        return hw.dcMotor.get(left);
    }
    public DcMotor getRight(){
        return hw.dcMotor.get(right);
    }
    public IMU getImu(){
        return hw.get(IMU.class, "imu");
    }
}
