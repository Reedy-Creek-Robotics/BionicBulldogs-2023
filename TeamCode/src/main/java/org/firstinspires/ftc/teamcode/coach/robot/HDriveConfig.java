package org.firstinspires.ftc.teamcode.coach.robot;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * There should be 1 config class per config on control hub
 * This is for a simple test
 */
public class HDriveConfig {
    public DcMotorEx front;
    public DcMotorEx back;
    public DcMotorEx left;
    public DcMotorEx right;

    public HDriveConfig(HardwareMap hardwareMap) {
        this.front = (DcMotorEx) hardwareMap.dcMotor.get("front");
        this.back = (DcMotorEx) hardwareMap.dcMotor.get("back");
        this.left = (DcMotorEx) hardwareMap.dcMotor.get("left");
        this.right = (DcMotorEx) hardwareMap.dcMotor.get("right");
    }
}
