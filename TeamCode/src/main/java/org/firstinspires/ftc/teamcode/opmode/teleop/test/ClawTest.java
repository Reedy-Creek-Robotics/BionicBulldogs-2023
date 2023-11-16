package org.firstinspires.ftc.teamcode.opmode.teleop.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.opmode.config.ClawConfig;
import org.firstinspires.ftc.teamcode.opmode.teleop.BaseTeleOp;
import org.firstinspires.ftc.teamcode.modules.robot.Claw;

@TeleOp
public class ClawTest extends BaseTeleOp {
    Claw claw;

    public void init() {
        super.init();
        ClawConfig config = new ClawConfig(hardwareMap);
        claw = new Claw(config);
    }
    public void start(){
        claw.initServos();
    }

    public void loop() {
        copyGamepads();
        // CLOSE TOP CLAW - Triangle
        if (gamepadEx1.triangle()) {
            claw.closeTop();
        }

        // OPEN TOP CLAW - Cross
        if (gamepadEx1.cross()) {
            claw.openTop();
        }

        // SCORE - square
        if (gamepadEx1.square()) {
            claw.score();
        }

        claw.scoreUpdate();
    }
}
