package org.firstinspires.ftc.teamcode.opmode.auto.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.modules.hardware.MotorGroup;

@Autonomous
public class BasicStraightTest extends LinearOpMode {
    float ticksPerIn = 537.7f / ((float)Math.PI * 4.75f);
    public void runOpMode(){
//        XDrive xDrive = new XDrive();
//        xDrive.init(new XDriveConfig(hardwareMap));
//
//        waitForStart();
//
//        xDrive.motors.runToPositionBlock((int)(60 * ticksPerIn), 0.5f);
//        xDrive.telem(telemetry);
        MotorGroup motorGroup = new MotorGroup();

        DcMotor left = hardwareMap.dcMotor.get("frontLeft");
        DcMotor right = hardwareMap.dcMotor.get("backRight");

        left.setDirection(DcMotorSimple.Direction.REVERSE);
        right.setDirection(DcMotorSimple.Direction.FORWARD);

        motorGroup.addMotor(left);
        motorGroup.addMotor(right);

        motorGroup.setRunMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        waitForStart();

        motorGroup.runToPositionBlock((int)(60 * ticksPerIn), 0.5f);

        telemetry.addData("left", left.getCurrentPosition());
        telemetry.addData("right", right.getCurrentPosition());
        telemetry.update();

        while (opModeIsActive());
    }
}
