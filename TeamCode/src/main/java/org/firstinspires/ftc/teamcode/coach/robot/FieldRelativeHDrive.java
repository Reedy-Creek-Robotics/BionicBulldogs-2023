package org.firstinspires.ftc.teamcode.coach.robot;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;

public class FieldRelativeHDrive extends HDrive {
    IMU imu;

    public FieldRelativeHDrive(DcMotorEx front, DcMotorEx back, DcMotorEx left, DcMotorEx right, IMU imu) {
        super(front, back, left, right);
    }

    @Override
    public void initialize() {
        super.initialize();
        initIMU();
    }

    protected void initIMU() {
        // TODO
    }

    public void move(double forwardBackPower, double leftRightPower, double rotatePower, double degrees) {
        // TODO: heading stuff
        // TODO: add telemetry
        // for holonomic: front and back wheels move us left and right, left and right move us forward and back
        front.setPower(leftRightPower + rotatePower);
        back.setPower(leftRightPower - rotatePower);
        left.setPower(forwardBackPower - rotatePower);
        right.setPower(forwardBackPower + rotatePower);
    }
}
