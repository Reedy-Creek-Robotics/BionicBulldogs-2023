package org.firstinspires.ftc.teamcode.coach.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

public class HDrive {

    DcMotorEx front;
    DcMotorEx back;
    DcMotorEx left;
    DcMotorEx right;

    public HDrive(DcMotorEx front, DcMotorEx back, DcMotorEx left, DcMotorEx right) {
        this.front = front;
        this.back = back;
        this.left = left;
        this.right = right;
    }

    public void initialize() {
        initMotors();
    }

    public void move(double forwardBackPower, double leftRightPower, double rotatePower) {
        // TODO: add telemetry
        // for holonomic: front and back wheels move us left and right, left and right move us forward and back
        front.setPower(leftRightPower + rotatePower);
        back.setPower(leftRightPower - rotatePower);
        left.setPower(forwardBackPower - rotatePower);
        right.setPower(forwardBackPower + rotatePower);
    }

    protected void initMotors() {
        // set the encoders mode and the motor directions
        front.setDirection(DcMotorSimple.Direction.REVERSE);
        back.setDirection(DcMotorSimple.Direction.FORWARD);
        left.setDirection(DcMotorSimple.Direction.REVERSE);
        right.setDirection(DcMotorSimple.Direction.FORWARD);

        front.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        back.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        front.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        back.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
}
