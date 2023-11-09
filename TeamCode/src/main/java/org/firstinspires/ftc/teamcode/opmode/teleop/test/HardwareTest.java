package org.firstinspires.ftc.teamcode.opmode.teleop.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.modules.UI.BoolEx;
import org.firstinspires.ftc.teamcode.modules.UI.FloatEx;
import org.firstinspires.ftc.teamcode.modules.UI.IntEx;
import org.firstinspires.ftc.teamcode.modules.UI.UI;
import org.firstinspires.ftc.teamcode.opmode.teleop.BaseTeleOp;

@TeleOp
public class HardwareTest extends BaseTeleOp {
    UI ui;
    int mode = 0;
    DcMotor motor;
    Servo servo;
    IntEx deviceNumber;
    FloatEx power;
    BoolEx showAll;
    public void init() {
        super.init();
        showAll = new BoolEx();
        ui = new UI(telemetry, gamepadEx1);
        power = new FloatEx();
        deviceNumber = new IntEx();
    }
    public void loop() {
        ui.update();
        if (mode != 0) {
            if (ui.button("back")) {
                mode = 0;
                ui.resetSelected();
            }
            ui.checkbox("show all", showAll);
            switch (mode) {
                case 1:
                    if (showAll.value) {
                        drawAllDcMotors();
                    } else {
                        drawDcMotor();
                    }
                    break;
                case 2:
                    if(showAll.value) {
                        drawAllServos();
                    }else {
                        drawServo();
                    }
                    break;
            }
        } else {
            if (ui.button("DcMotor")) {
                mode = 1;
                motor = hardwareMap.dcMotor.iterator().next();
                ui.resetSelected();
            }
            if (ui.button("Servo")) {
                mode = 2;
                servo = hardwareMap.servo.iterator().next();
                ui.resetSelected();
            }
        }
        telemetry.update();
    }
    void drawDcMotor(){
        if(ui.intInput("motor number", deviceNumber, 1)){
            int i = 0;
            for (DcMotor motor1 : hardwareMap.dcMotor) {
                if(i == deviceNumber.value){
                    motor = motor1;
                    break;
                }
                i++;
            }
        }
        ui.label("port: " + motor.getPortNumber());
        ui.label("connection info", motor.getConnectionInfo());
        ui.label("current position", motor.getCurrentPosition());
        if(ui.floatInput("power", power, 0.05f)){
            motor.setPower(power.value);
        }
        if(ui.button("stop")){
            motor.setPower(0);
            power.value = 0.0f;
        }
    }
    void drawServo(){
        if(ui.intInput("servo number", deviceNumber, 1)){
            int i = 0;
            for (Servo servo1: hardwareMap.servo) {
                if(i == deviceNumber.value){
                    servo = servo1;
                    break;
                }
                i++;
            }
        }
        ui.label("port: " + servo.getPortNumber());
        ui.label("connection info", servo.getConnectionInfo());
        ui.label("current position", servo.getPosition());
        if(ui.floatInput("targetPosition", power, 0.05f)){
            servo.setPosition(power.value);
        }
        if(ui.button("zero")){
            servo.setPosition(0);
            power.value = 0.0f;
        }
    }
    void drawAllDcMotors(){
        for(DcMotor motor1 : hardwareMap.dcMotor){
            ui.label("port: " + motor1.getPortNumber());
            ui.label("connection info", motor1.getConnectionInfo());
            ui.label("current power", motor1.getPower());
            ui.label("current position", motor1.getCurrentPosition());
        }
    }
    void drawAllServos(){
        for(Servo servo1 : hardwareMap.servo){
            ui.label("port: " + servo1.getPortNumber());
            ui.label("connection info", servo1.getConnectionInfo());
            ui.label("current position", servo1.getPosition());
        }
    }
}
