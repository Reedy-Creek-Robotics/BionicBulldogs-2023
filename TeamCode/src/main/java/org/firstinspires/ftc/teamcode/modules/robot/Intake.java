package org.firstinspires.ftc.teamcode.modules.robot;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.opmode.config.IntakeConfig;

public class Intake extends BaseComponent {
    public enum IntakeState{
        Intake,
        Outtake,
        Stop
    }
    DcMotor motor;
    CRServo servo;
    IntakeState state;
    public Intake(IntakeConfig config){
        motor = config.getMotor();
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor.setDirection(DcMotorSimple.Direction.REVERSE);
        servo = config.getServo();
        state = IntakeState.Stop;
    }

    public void intake(double power) {
        motor.setPower(-power);
        servo.setPower(-power);
        state = IntakeState.Intake;
    }

    public void outtake(double power) {
        motor.setPower(power);
        servo.setPower(power);
        state = IntakeState.Outtake;
    }

    public void stop() {
        motor.setPower(0);
        servo.setPower(0);
        state = IntakeState.Stop;
    }
    public IntakeState getState(){
        return state;
    }

    @Override
    public void addTelemetry(Telemetry telemetry) {

    }
}
