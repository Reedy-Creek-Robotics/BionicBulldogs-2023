package org.firstinspires.ftc.teamcode.coach.robot;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class FieldRelativeHDrive extends HDrive {
    IMU imu;

    public FieldRelativeHDrive(DcMotorEx front, DcMotorEx back, DcMotorEx left, DcMotorEx right, IMU imu) {
        super(front, back, left, right);
        this.imu = imu;
    }

    @Override
    public void initialize() {
        super.initialize();
        initIMU();
    }

    protected void initIMU() {
        // TODO - edit this to be correct
        RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.UP;
        RevHubOrientationOnRobot.UsbFacingDirection  usbDirection  = RevHubOrientationOnRobot.UsbFacingDirection.FORWARD;
        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);
        imu.initialize(new IMU.Parameters(orientationOnRobot));
    }

    @Override
    public void move(double forwardBackPower, double leftRightPower, double rotatePower) {
        // TODO: add heading stuff to calculations
        double degrees = getHeading();

        // for holonomic: front and back wheels move us left and right, left and right move us forward and back
        front.setPower(leftRightPower + rotatePower);
        back.setPower(leftRightPower - rotatePower);
        left.setPower(forwardBackPower - rotatePower);
        right.setPower(forwardBackPower + rotatePower);

        updateTelemetry();
    }

    protected void updateTelemetry() {
        // TODO
    }

    /**
     * read the Robot heading directly from the IMU (in degrees)
     */
    public double getHeading() {
        YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
        return orientation.getYaw(AngleUnit.DEGREES);
    }
}
