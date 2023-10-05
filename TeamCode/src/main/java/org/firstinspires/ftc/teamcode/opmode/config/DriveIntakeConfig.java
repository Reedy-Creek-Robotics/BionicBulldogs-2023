package org.firstinspires.ftc.teamcode.opmode.config;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
public class DriveIntakeConfig extends XDriveConfig {
    public DriveIntakeConfig(HardwareMap hmap){
        super(hmap);
    }
    public DcMotor getMotor() {
        return hw.dcMotor.get("intakeMotor");
    }
}