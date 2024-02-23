package org.firstinspires.ftc.teamcode.opmode.teleop.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.modules.robot.DroneLauncher;
import org.firstinspires.ftc.teamcode.modules.robot.HangingSlides;
import org.firstinspires.ftc.teamcode.opmode.config.DroneLauncherConfig;
import org.firstinspires.ftc.teamcode.opmode.config.HangingSlidesConfig;
import org.firstinspires.ftc.teamcode.opmode.teleop.BaseTeleOp;
@TeleOp(group = "test")
public class HangingTest extends BaseTeleOp {
    HangingSlides slides;
    public void init(){
        super.init();
        slides = new HangingSlides(new HangingSlidesConfig(hardwareMap));
    }
    public void loop(){
        if(gamepadEx1.square()){
            slides.up();
        }
        if(gamepadEx1.cross()){
            slides.hang();
        }
        copyGamepads();
        slides.telem(telemetry);
        telemetry.update();
    }
}
