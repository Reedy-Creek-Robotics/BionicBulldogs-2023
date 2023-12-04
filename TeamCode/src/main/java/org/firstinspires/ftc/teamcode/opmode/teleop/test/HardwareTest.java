package org.firstinspires.ftc.teamcode.opmode.teleop.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.opmode.config.XDriveConfig;

import java.util.Iterator;

@TeleOp
public class HardwareTest extends OpMode {

    @Override
    public void init() {
    }

    @Override
    public void loop() {
            for(DcMotor dcMotor : hardwareMap.dcMotor){
                StringBuilder motor = new StringBuilder();
                motor.append("\n")
                        .append("\tposition").append(dcMotor.getCurrentPosition())
                        .append("\tpower").append(dcMotor.getPower())
                        .append("\tdirection").append(dcMotor.getDirection())
                        .append("\tname").append(dcMotor.getDeviceName());
                telemetry.addData("dcMotor" + dcMotor.getPortNumber(), motor.toString());
            }

            for(Servo servo : hardwareMap.servo){
                StringBuilder servoString = new StringBuilder();
                servoString.append("\n")
                        .append("\tdirection").append(servo.getDirection())
                        .append("\tport Number").append(servo.getPortNumber())
                        .append("\tname").append(servo.getDeviceName());
                telemetry.addData("servo" + servo.getPosition(), servoString.toString());
            }

            for(CRServo crservo : hardwareMap.crservo){
                StringBuilder crservoString = new StringBuilder();
                crservoString.append("\n")
                        .append("\tpower").append(crservo.getPower())
                        .append("\tname").append(crservo.getDeviceName())
                        .append("\tdirection").append(crservo.getDirection());
                telemetry.addData("crservo" ,crservoString);
            }
            IMU imu = hardwareMap.get(IMU.class, "imu");
        }
    }
