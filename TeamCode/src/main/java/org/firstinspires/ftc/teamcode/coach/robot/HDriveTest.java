package org.firstinspires.ftc.teamcode.coach.robot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class HDriveTest extends OpMode {
    HDrive hDrive;

    @Override
    public void init() {
        HDriveConfig config = new HDriveConfig(hardwareMap);
        this.hDrive = new HDrive(config.getDriveFront(), config.getDriveBack(), config.getDriveLeft(), config.getDriveRight());
        this.hDrive.initialize();
    }

    @Override
    public void loop() {
        float forwardBackward = -gamepad1.left_stick_y;
        float leftRight = gamepad1.left_stick_x;
        float rotate = -gamepad1.right_stick_x;
        this.hDrive.move(forwardBackward, leftRight, rotate);
    }
}
