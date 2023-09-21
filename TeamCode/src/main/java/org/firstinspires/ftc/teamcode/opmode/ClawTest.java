package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.config.ClawConfig;
import org.firstinspires.ftc.teamcode.modules.Claw;
@TeleOp
public class ClawTest extends LinearOpMode {
    public void runOpMode(){
        ClawConfig config = new ClawConfig(hardwareMap);
        Claw claw = new Claw(config);
        waitForStart();
        while (opModeIsActive()){
            if(gamepad1.triangle){
                claw.openTop();
            }
            if(gamepad1.cross){
                claw.closeTop();
            }
            if(gamepad1.square){
                claw.openSide();
            }
            if(gamepad1.circle){
                claw.closeSide();
            }
            if(gamepad1.dpad_up){
                claw.openSide();
                sleep(200);
                claw.closeSide();
            }
        }
    }
}
