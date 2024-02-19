package org.firstinspires.ftc.teamcode.opmode.config;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeConfig {
    public HardwareMap hw;
    public IntakeConfig(HardwareMap hmap){
        hw = hmap;
    }
    public DcMotor getMotor() {
        return hw.dcMotor.get("intakeMotor");
    }
    public CRServo getServo() {
        return hw.crservo.get("intakeServo");
    }
    public Servo getStackGrabber(){return hw.servo.get("stackGrabber");}
}