package org.firstinspires.ftc.teamcode.opmode.auto.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.modules.drive.XDrive;
import org.firstinspires.ftc.teamcode.modules.hardware.MotorGroup;
import org.firstinspires.ftc.teamcode.opmode.config.XDriveConfig;

@Autonomous
@Disabled
public class BasicStraightTest extends LinearOpMode {
    float ticksPerIn = 384.5f / ((float)Math.PI * 4.75f);//25.7
    public void runOpMode(){
        XDrive xDrive = new XDrive();
        xDrive.init(new XDriveConfig(hardwareMap));

        waitForStart();

        xDrive.motors.runToPositionBlock((int)(60 * ticksPerIn), 0.5f);
        xDrive.telem(telemetry);

        while (opModeIsActive());
    }
}
