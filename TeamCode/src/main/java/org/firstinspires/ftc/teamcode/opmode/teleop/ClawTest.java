package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.opmode.teleop.BaseTeleOp;
import org.firstinspires.ftc.teamcode.opmode.teleop.ClawTestConfig;
import org.firstinspires.ftc.teamcode.modules.robot.Claw;

@TeleOp
public class ClawTest extends BaseTeleOp {
    Claw claw;

    @Override
    public void init() {
        ClawTestConfig config = new ClawTestConfig(hardwareMap);
        this.claw = new Claw(config.top, config.side);
    }

    @Override
    public void loop() {
        // OPEN TOP CLAW - Triangle
        if (gamepad1.triangle) {
            claw.openTop();
        }

        // CLOSE TOP CLAW - Cross
        if (gamepad1.cross) {
            claw.closeTop();
        }

        // OPEN SIDE CLAW - Square
        if (gamepad1.square) {
            claw.openSide();
        }

        // CLOSE SIDE CLAW - Circle
        if (gamepad1.circle) {
            claw.closeSide();
        }

        // OPEN and CLOSE SIDE CLAW - Dpad Up
        if (gamepad1.dpad_up) {
            claw.openSide();
            sleep(200);
            claw.closeSide();
        }
    }
}
