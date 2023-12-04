package org.firstinspires.ftc.teamcode.opmode.teleop.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.modules.robot.Claw;
import org.firstinspires.ftc.teamcode.modules.robot.Slides;
import org.firstinspires.ftc.teamcode.opmode.config.ClawConfig;
import org.firstinspires.ftc.teamcode.opmode.config.SlideConfig;
import org.firstinspires.ftc.teamcode.opmode.teleop.BaseTeleOp;
@TeleOp
public class ScoreTest extends BaseTeleOp {
    Slides slides;
    Claw claw;
    public void init(){
        claw = new Claw(new ClawConfig(hardwareMap));
        slides = new Slides(new SlideConfig(hardwareMap));
    }
    public void start(){
        super.start();
        claw.initServos();
        slides.resetRotator();
    }
    public void loop(){
        slides.setPower(gamepadEx1.leftStickY());
        if(gamepadEx1.square()){
            claw.score(2);
        }
        slides.updateClawServo();
        claw.scoreUpdate();
        copyGamepads();
    }
}
