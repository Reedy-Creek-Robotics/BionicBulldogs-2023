package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ClawTestConfig {
    public Servo top;
    public Servo side;

    public ClawTestConfig(HardwareMap hmap) {
        this.top = hmap.servo.get("top");
        this.side = hmap.servo.get("side");
    }
}
