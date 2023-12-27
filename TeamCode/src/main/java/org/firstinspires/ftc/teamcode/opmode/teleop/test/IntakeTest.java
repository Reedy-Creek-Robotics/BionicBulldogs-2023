package org.firstinspires.ftc.teamcode.opmode.teleop.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.modules.robot.Intake;
import org.firstinspires.ftc.teamcode.opmode.config.IntakeConfig;
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

        if(gamepadEx1.dpadUp()){
            intakePwr += 0.1;
        }
        if(gamepadEx1.dpadDown()){
            intakePwr -= 0.1;
        }

        //intake start
        if (gamepadEx1.leftBumper()) {
            if(intake.getState() == Intake.IntakeState.Stop) {
                intake.intake(intakePwr);
            }else{
                intake.stop();
            }
        }

        //intake outtake
        else if (gamepadEx1.square()) {
            if(intake.getState() == Intake.IntakeState.Stop) {
                intake.outtake(intakePwr);
            }else{
                intake.stop();
            }
        }

        telemetry.addData("intake power", intakePwr);
        telemetry.update();
    }
}
