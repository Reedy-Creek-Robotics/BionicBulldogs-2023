package org.firstinspires.ftc.teamcode.opmode.teleop.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.modules.drive.XDrive;
import org.firstinspires.ftc.teamcode.modules.robot.Intake;
import org.firstinspires.ftc.teamcode.opmode.config.IntakeConfig;
import org.firstinspires.ftc.teamcode.opmode.config.XDriveConfig;
import org.firstinspires.ftc.teamcode.opmode.teleop.BaseTeleOp;

@TeleOp
public class IntakeTest extends BaseTeleOp {
    Intake intake;
    float intakePwr = 0.8f;
    public void init() {
        super.init();

        IntakeConfig intakeConfig = new IntakeConfig(hardwareMap);

        intake = new Intake(intakeConfig);
    }
    public void loop(){
        copyGamepads();

        if (gamepadEx1.dpadUp()){
            if (intakePwr < 1){
                intakePwr = intakePwr +0.05f;
            }
        }
        if (gamepadEx1.dpadDown()){
            if (intakePwr > 0) {
                intakePwr = intakePwr - 0.05f;
            }
        }
        if (gamepadEx1.cross()) {
            intake.intake(intakePwr);
        }
        if (gamepadEx1.circle()) {
            intake.outtake(intakePwr);
        }
        if (gamepadEx1.square()) {
            intake.stop();
        }
        telemetry.addData("Intakes Power", intakePwr);
        telemetry.update();
    }
}
