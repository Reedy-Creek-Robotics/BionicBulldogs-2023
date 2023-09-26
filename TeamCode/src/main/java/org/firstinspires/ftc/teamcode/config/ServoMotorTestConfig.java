package org.firstinspires.ftc.teamcode.config;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ServoMotorTestConfig {
    HardwareMap hw;

    public ServoMotorTestConfig(HardwareMap hmap) {
        hw = hmap;
    }

    public DcMotor getMotor() {
        return hw.dcMotor.get("motor");
    }

    public Servo getServo() {
        return hw.servo.get("servo");
    }
}
