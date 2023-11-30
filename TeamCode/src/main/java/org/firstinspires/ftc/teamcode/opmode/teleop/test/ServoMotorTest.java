package org.firstinspires.ftc.teamcode.opmode.teleop.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp
public class ServoMotorTest extends LinearOpMode {
    public void runOpMode(){
        //DcMotor motor = hardwareMap.dcMotor.get("motor");
        Servo servo = hardwareMap.servo.get("rotator");

        //motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //motor.setDirection(DcMotorSimple.Direction.FORWARD);

        servo.setDirection(Servo.Direction.FORWARD);

        waitForStart();
        while(opModeIsActive()){
            if(gamepad1.dpad_up){
                //motor.setPower(1);
            }
            if(gamepad1.dpad_down){
                //motor.setPower(0);
            }
            if(gamepad1.triangle){
                servo.setPosition(0);
            }
            if(gamepad1.cross){
                servo.setPosition(0.5);
            }
        }
    }
}
