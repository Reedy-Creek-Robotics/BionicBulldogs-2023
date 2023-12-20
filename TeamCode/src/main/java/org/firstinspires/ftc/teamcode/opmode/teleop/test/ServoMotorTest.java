package org.firstinspires.ftc.teamcode.opmode.teleop.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.opmode.teleop.BaseTeleOp;


@TeleOp
public class ServoMotorTest extends BaseTeleOp {
    DcMotor motor;
    Servo servo;

    public void init(){
        motor = hardwareMap.dcMotor.get("motor");
        servo = hardwareMap.servo.get("servo");

        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setDirection(DcMotorSimple.Direction.FORWARD);

        servo.setDirection(Servo.Direction.FORWARD);
    }

    public void loop(){

        if(gamepadEx1.dpadUp()){
            motor.setPower(1);
        }
        if(gamepadEx1.dpadDown()){
            motor.setPower(0);
        }
        if(gamepadEx1.triangle()){
            servo.setPosition(1);
        }
        if(gamepadEx1.cross()){
            servo.setPosition(0.5);
        }
        if(gamepadEx1.circle()){
            servo.setPosition(0);
        }

        copyGamepads();
    }

}
