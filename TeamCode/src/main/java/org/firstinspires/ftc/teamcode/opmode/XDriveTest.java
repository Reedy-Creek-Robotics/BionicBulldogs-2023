package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.config.XDriveConfig;
import org.firstinspires.ftc.teamcode.modules.drive.HDrive;
import org.firstinspires.ftc.teamcode.modules.drive.XDrive;

public class XDriveTest extends LinearOpMode {
    public void runOpMode(){
        XDriveConfig xDriveConfig = new XDriveConfig(hardwareMap);
        HDrive drive = new XDrive();
        drive.init(xDriveConfig);
        waitForStart();
        while(opModeIsActive()){
            float forward = -gamepad1.left_stick_y;
            float right = gamepad1.left_stick_x;
            float rotate = -gamepad1.right_stick_x;
            drive.update(forward, right, rotate);
        }
    }
}
