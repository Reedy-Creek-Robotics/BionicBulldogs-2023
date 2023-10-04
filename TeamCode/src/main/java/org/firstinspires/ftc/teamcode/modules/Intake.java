package org.firstinspires.ftc.teamcode.modules;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.opmode.config.DriveIntakeConfig;

public class Intake {
    DcMotor intake;
    public Intake(DriveIntakeConfig config){
        intake = config.getMotor();
        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void intake(double power) {
        intake.setDirection(DcMotorSimple.Direction.REVERSE);
        intake.setPower(power);
    }

    public void outtake(double power) {
        intake.setDirection(DcMotorSimple.Direction.FORWARD);
        intake.setPower(power);
    }

    public void stop() {
        intake.setPower(0);
    }
}
