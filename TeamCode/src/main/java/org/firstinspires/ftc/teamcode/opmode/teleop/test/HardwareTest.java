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
            int i = 0;
            for(DcMotor dcMotor : hardwareMap.dcMotor){
                telemetry.addData("dcMotor" + i, dcMotor.getCurrentPosition());
                i++;
            }

            for(Servo servo : hardwareMap.servo){
                telemetry.addData("servo" + i, servo.getPosition());
                i++;
            }

            for(CRServo crservo : hardwareMap.crservo){
                telemetry.addData("crservo" + i, i);
                i++;
            }
            IMU imu = hardwareMap.get(IMU.class, "imu");
        }
    }
