package org.firstinspires.ftc.teamcode.coach.robot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class FieldRelativeHDriveTest extends OpMode {
    FieldRelativeHDrive hDrive;

    @Override
    public void init() {
        FieldRelativeHDriveConfig config = new FieldRelativeHDriveConfig(hardwareMap);
        this.hDrive = new FieldRelativeHDrive(
            config.front,
            config.back,
            config.left,
            config.right,
            config.imu
        );
        this.hDrive.initialize();
    }

    @Override
    public void loop() {
        // TODO: need heading
        float forwardBackward = -gamepad1.left_stick_y;
        float leftRight = gamepad1.left_stick_x;
        float rotate = -gamepad1.right_stick_x;
        this.hDrive.move(forwardBackward, leftRight, rotate);
    }
}
