package org.firstinspires.ftc.teamcode.opmode.teleop.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.modules.Ui.FloatEx;
import org.firstinspires.ftc.teamcode.modules.Ui.IntEx;
import org.firstinspires.ftc.teamcode.modules.Ui.UI;
import org.firstinspires.ftc.teamcode.modules.robot.Recognition;
import org.firstinspires.ftc.teamcode.opmode.teleop.BaseTeleOp;

@TeleOp
public class RecognitionRegionTuner extends BaseTeleOp {
    Recognition recognition;
    UI ui;

    //java doesnt have int* (int pointer) so i have classes for them

    IntEx box1x;
    IntEx box1y;
    IntEx box2x;
    IntEx box2y;
    IntEx box3x;
    IntEx box3y;

    public void init(){
        super.init();
        recognition = new Recognition(this);
        ui = new UI(telemetry, gamepad1);
        box1x = new IntEx();
        box1x.value = (int)Recognition.SkystoneDeterminationPipeline.REGION1_TOPLEFT_ANCHOR_POINT.x;
        box1y = new IntEx();
        box1y.value = (int)Recognition.SkystoneDeterminationPipeline.REGION1_TOPLEFT_ANCHOR_POINT.y;
        box2x = new IntEx();
        box2x.value = (int)Recognition.SkystoneDeterminationPipeline.REGION2_TOPLEFT_ANCHOR_POINT.x;
        box2y = new IntEx();
        box2y.value = (int)Recognition.SkystoneDeterminationPipeline.REGION2_TOPLEFT_ANCHOR_POINT.y;
        box3x = new IntEx();
        box3x.value = (int)Recognition.SkystoneDeterminationPipeline.REGION3_TOPLEFT_ANCHOR_POINT.x;
        box3y = new IntEx();
        box3y.value = (int)Recognition.SkystoneDeterminationPipeline.REGION3_TOPLEFT_ANCHOR_POINT.y;
    }

    public void loop(){
        if(ui.intInput("box 1 x", box1x, 20)){
            Recognition.SkystoneDeterminationPipeline.REGION1_TOPLEFT_ANCHOR_POINT.x = box1x.value;
        }
        if(ui.intInput("box 1 y", box1y, 20)){
            Recognition.SkystoneDeterminationPipeline.REGION1_TOPLEFT_ANCHOR_POINT.y = box1y.value;
        }
        if(ui.intInput("box 2 x", box2x, 20)){
            Recognition.SkystoneDeterminationPipeline.REGION2_TOPLEFT_ANCHOR_POINT.x = box2x.value;
        }
        if(ui.intInput("box 2 y", box2y, 20)){
            Recognition.SkystoneDeterminationPipeline.REGION2_TOPLEFT_ANCHOR_POINT.y = box2y.value;
        }
        if(ui.intInput("box 3 x", box3x, 20)){
            Recognition.SkystoneDeterminationPipeline.REGION3_TOPLEFT_ANCHOR_POINT.x = box3x.value;
        }
        if(ui.intInput("box 3 y", box3y, 20)){
            Recognition.SkystoneDeterminationPipeline.REGION3_TOPLEFT_ANCHOR_POINT.y = box3y.value;
        }
        ui.update();
        telemetry.update();
        copyGamepads();
    }
}
