package org.firstinspires.ftc.teamcode.opmode.teleop.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.opmode.config.ClawConfig;
import org.firstinspires.ftc.teamcode.opmode.teleop.BaseTeleOp;
import org.firstinspires.ftc.teamcode.modules.robot.Claw;

import java.util.concurrent.Delayed;

@TeleOp
public class ClawTest extends BaseTeleOp {
    Claw claw;
    long delayMs;
    static long INCREMENT = 25;

    public void init() {
        super.init();
        ClawConfig config = new ClawConfig(hardwareMap);
        claw = new Claw(config);
    }
    public void start(){
        super.start();
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

        if(gamepadEx1.dpadUp()) {
            delayMs += INCREMENT;
        }

        if( gamepadEx1.dpadDown()) {
            delayMs -= INCREMENT;
            if (delayMs <=0 ) {
                delayMs = 0;
            }
        }
        telemetry.addData("Push Delay (ms)", delayMs);
        telemetry.update();

        if( gamepadEx1.circle()) {
            claw.push();
            sleep(delayMs);
            claw.resetFlicker();
        }

        if( gamepadEx1.rightBumper()) {
            claw.push();
            sleep(delayMs);
            claw.resetFlicker();
            sleep(delayMs);
            claw.push();
            sleep(delayMs);
            claw.resetFlicker();
        }
    }
}
