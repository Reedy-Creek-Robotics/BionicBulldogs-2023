package org.firstinspires.ftc.teamcode.modules.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Intake extends BaseComponent {
    DcMotor motor;

    public Intake(DcMotor motor){
        this.motor = motor;
        this.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void intake(double power) {
        motor.setDirection(DcMotorSimple.Direction.REVERSE);
        motor.setPower(power);
    }

    public void outtake(double power) {
        motor.setDirection(DcMotorSimple.Direction.FORWARD);
        motor.setPower(power);
    }

    public void stop() {
        motor.setPower(0);
    }

    @Override
    public void addTelemetry(Telemetry telemetry) {

    }
}
