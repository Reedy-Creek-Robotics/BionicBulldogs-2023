package org.firstinspires.ftc.teamcode.opmode.config;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
public class XDriveConfig {
    String frontLeft = "frontLeft";
    String frontRight = "frontRight";
    String backLeft  = "backLeft";
    String backRight = "backRight";

    HardwareMap hw;

    public XDriveConfig(HardwareMap hmap){hw = hmap;}

    public DcMotor getFrontLeft(){
        return hw.dcMotor.get(frontLeft);
    }
    public DcMotor getFrontRight(){
        return hw.dcMotor.get(frontRight);
    }
    public DcMotor getBackLeft(){
        return hw.dcMotor.get(backLeft);
    }
    public DcMotor getBackRight(){
        return hw.dcMotor.get(backRight);
    }
    public IMU getImu(){
        return hw.get(IMU.class, "imu");
    }
}
