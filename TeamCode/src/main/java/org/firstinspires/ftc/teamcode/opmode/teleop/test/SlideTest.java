package org.firstinspires.ftc.teamcode.opmode.teleop.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.modules.robot.Slides;
import org.firstinspires.ftc.teamcode.opmode.config.SlideConfig;
import org.firstinspires.ftc.teamcode.opmode.teleop.BaseTeleOp;
@TeleOp
@Disabled
public class SlideTest extends BaseTeleOp {
    Slides slides;
    public void init(){
        super.init();
        slides = new Slides(new SlideConfig(hardwareMap));
    }

    public void loop(){
        if(gamepadEx1.dpadUp()){
            slides.up1Pixel();
        }
        if(gamepadEx1.dpadDown()){
            slides.reset();
        }
        if(gamepadEx1.dpadRight()){
            slides.down1Pixel();
        }
        if(gamepadEx1.dpadLeft()){
            slides.goToLastPosition();
        }
        if(gamepadEx1.options()){
            slides.resetLastPosition();
        }
        slides.telem(telemetry);
        copyGamepads();
    }
}
