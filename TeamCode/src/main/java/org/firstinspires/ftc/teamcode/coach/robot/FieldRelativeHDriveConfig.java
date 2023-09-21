package org.firstinspires.ftc.teamcode.coach.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

public class FieldRelativeHDriveConfig extends HDriveConfig {
    public IMU imu;

    public FieldRelativeHDriveConfig(HardwareMap hardwareMap) {
        super(hardwareMap);
        this.imu = hardwareMap.get(IMU.class, "imu");
    }
}
