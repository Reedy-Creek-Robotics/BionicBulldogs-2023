package org.firstinspires.ftc.teamcode.opmode.teleop.test;

import android.graphics.Color;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.LED;

@Config
@TeleOp(group = "test")
public class LineBreakTest extends OpMode {
    ColorSensor colorSensor;
    LED led;
    static public double targetRed = 0.1;
    static public double targetGreen = 0.1;
    static public double targetBlue = 89;
    static public double rumbleStrength = 1;
    public void init(){
        colorSensor = hardwareMap.colorSensor.iterator().next();
    }
    public void loop(){
        if(colorSensor.red() > targetRed && colorSensor.green() > targetGreen && colorSensor.blue() > targetBlue){
            gamepad1.setLedColor(1, 1, 1, 1);
            gamepad1.rumble(rumbleStrength, rumbleStrength, 1);
        }else{
            //hardwareMap.led.iterator().next().enable(false);
        }
    }
}
