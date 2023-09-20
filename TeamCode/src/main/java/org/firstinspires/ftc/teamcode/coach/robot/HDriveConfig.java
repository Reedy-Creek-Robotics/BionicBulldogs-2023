package org.firstinspires.ftc.teamcode.coach.robot;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * There should be 1 config class per config on control hub
 * This is for a simple test
 */
public class HDriveConfig {
    public final static String MOTOR_DRIVE_FRONT = "front";
    public final static String MOTOR_DRIVE_BACK = "back";
    public final static String MOTOR_DRIVE_LEFT = "left";
    public final static String MOTOR_DRIVE_RIGHT = "right";

    private HardwareMap hardwareMap;

    public HDriveConfig(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
    }

    public DcMotorEx getDriveFront() {
        return (DcMotorEx) this.hardwareMap.dcMotor.get(MOTOR_DRIVE_FRONT);
    }

    public DcMotorEx getDriveBack() {
        return (DcMotorEx) this.hardwareMap.dcMotor.get(MOTOR_DRIVE_BACK);
    }

    public DcMotorEx getDriveLeft() {
        return (DcMotorEx) this.hardwareMap.dcMotor.get(MOTOR_DRIVE_LEFT);
    }

    public DcMotorEx getDriveRight() {
        return (DcMotorEx) this.hardwareMap.dcMotor.get(MOTOR_DRIVE_RIGHT);
    }
}
