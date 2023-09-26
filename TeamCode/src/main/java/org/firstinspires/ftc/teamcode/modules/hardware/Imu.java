package org.firstinspires.ftc.teamcode.modules.hardware;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

public class Imu {
    IMU imu;

    public Imu(HardwareMap hmap, RevHubOrientationOnRobot.UsbFacingDirection usb, RevHubOrientationOnRobot.LogoFacingDirection logo) {
        imu = hmap.get(IMU.class, "imu");
        imu.initialize(new IMU.Parameters(new RevHubOrientationOnRobot(logo, usb)));
        imu.resetYaw();
    }

    public Imu(HardwareMap hmap) {
        imu = hmap.get(IMU.class, "imu");
        imu.initialize(
                new IMU.Parameters(
                        new RevHubOrientationOnRobot(
                                RevHubOrientationOnRobot.LogoFacingDirection.FORWARD,
                                RevHubOrientationOnRobot.UsbFacingDirection.UP
                        )
                )
        );
        imu.resetYaw();
    }

    public void resetHeading() {
        imu.resetYaw();
    }

    public float getHeading(AngleUnit unit) {
        return imu.getRobotOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, unit).secondAngle;
    }
}
