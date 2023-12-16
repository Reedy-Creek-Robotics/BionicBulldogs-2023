package org.firstinspires.ftc.teamcode.opmode.teleop.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.onbotjava.handlers.objbuild.WaitForBuild;
import org.firstinspires.ftc.teamcode.modules.robot.Recognition;
@TeleOp
public class RecognitionTest extends LinearOpMode {
    public void runOpMode(){
        Recognition recognition;

        recognition = new Recognition(this);

        waitForStart();
    }
}
