package org.firstinspires.ftc.teamcode.opmode.teleop.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.opmode.config.ClawConfig;
import org.firstinspires.ftc.teamcode.opmode.teleop.BaseTeleOp;
import org.firstinspires.ftc.teamcode.modules.robot.Claw;

@TeleOp
public class ClawTest extends BaseTeleOp {
    Claw claw;

    public void init() {
        baseInit();
        ClawConfig config = new ClawConfig(hardwareMap);
        claw = new Claw(config);
    }

    public void loop() {
        // OPEN TOP CLAW - Triangle
        if (gamepadEx1.triangle()) {
            claw.openTop();
        }

        // CLOSE TOP CLAW - Cross
        if (gamepadEx1.cross()) {
            claw.closeTop();
        }

        // OPEN SIDE CLAW - Square
        if (gamepadEx1.square()) {
            claw.openSide();
        }

        // CLOSE SIDE CLAW - Circle
        if (gamepadEx1.circle()) {
            claw.closeSide();
        }

        // OPEN and CLOSE SIDE CLAW - Dpad Up
        if (gamepadEx1.dpadUp()) {
            claw.openSide();
            sleep(200);
            claw.closeSide();
        }
        copyGamepads();
    }
}
