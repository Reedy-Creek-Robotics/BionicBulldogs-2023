package org.firstinspires.ftc.teamcode.modules.robot;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.opmode.config.DroneLauncherConfig;

public class DroneLauncher {
    Servo servo;
    static final float launchPosition = 0;
    public DroneLauncher(DroneLauncherConfig cfg){
        servo = cfg.getServo();
    }
    public void reset(){
        servo.setPosition(0.2f);
    }
    public void launch(){
        servo.setPosition(launchPosition);
    }
}
