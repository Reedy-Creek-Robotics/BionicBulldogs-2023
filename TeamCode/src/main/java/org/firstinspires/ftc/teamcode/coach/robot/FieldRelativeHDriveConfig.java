package org.firstinspires.ftc.teamcode.coach.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

public class FieldRelativeHDriveConfig extends HDriveConfig {
    IMU imu;

    public FieldRelativeHDriveConfig(HardwareMap hardwareMap) {
        super(hardwareMap);
    }

    public IMU imu() {
        return this.hardwareMap.get(IMU.class, "imu");
    }
}
