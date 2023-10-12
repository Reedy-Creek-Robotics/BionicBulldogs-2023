package org.firstinspires.ftc.teamcode.opmode.config;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class IntakeConfig {
    public HardwareMap hw;
    public IntakeConfig(HardwareMap hmap){
        hw = hmap;
    }
    public DcMotor getMotor() {
        return hw.dcMotor.get("intakeMotor");
    }
}