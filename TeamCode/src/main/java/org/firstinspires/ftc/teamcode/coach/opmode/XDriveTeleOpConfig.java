package org.firstinspires.ftc.teamcode.coach.opmode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.modules.hardware.ImuEx;

public class XDriveTeleOpConfig {
    ImuEx imu;
    DcMotorEx frontLeft;
    DcMotorEx frontRight;
    DcMotorEx backLeft;
    DcMotorEx backRight;

    public XDriveTeleOpConfig(HardwareMap hwMap) {
        frontLeft = (DcMotorEx) hwMap.dcMotor.get("left");
        frontRight = (DcMotorEx) hwMap.dcMotor.get("front");
        backLeft = (DcMotorEx) hwMap.dcMotor.get("back");
        backRight = (DcMotorEx) hwMap.dcMotor.get("right");

        imu = new ImuEx(hwMap.get(IMU.class, "imu"));
    }
}
