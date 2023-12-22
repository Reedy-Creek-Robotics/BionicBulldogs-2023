package org.firstinspires.ftc.teamcode.opmode.teleop.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.onbotjava.handlers.objbuild.WaitForBuild;
import org.firstinspires.ftc.robotcore.external.hardware.camera.CameraName;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.modules.robot.ElementPosition;
import org.firstinspires.ftc.teamcode.modules.robot.Recognition;
import org.firstinspires.ftc.teamcode.modules.robot.RecognitionProcesser;
import org.firstinspires.ftc.teamcode.opmode.teleop.BaseTeleOp;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.VisionProcessor;

@TeleOp
public class RecognitionTest extends BaseTeleOp {

    RecognitionProcesser recognitionProcesser;
    VisionPortal visionPortal;

    @Override
    public void init() {
        super.init();
        // Create the AprilTag processor by using a builder.
        recognitionProcesser = new RecognitionProcesser();

        // Create the vision portal by using a builder.
        CameraName camera = hardwareMap.get(WebcamName.class, "Webcam 1");
        visionPortal = new VisionPortal.Builder()
                .setCamera(camera)
                .addProcessor(recognitionProcesser)
                .build();
    }

    @Override
    public void init_loop() {
        super.init_loop();
        telemetry.addData("initialized", recognitionProcesser.isInitialized());
        if(recognitionProcesser.isInitialized()){
            ElementPosition position = recognitionProcesser.getPosition();
            telemetry.addData("Result", position);
            telemetry.addData("region-one", recognitionProcesser.getNonZero1());
            telemetry.addData("region-two", recognitionProcesser.getNonZero2());
            telemetry.addData("region-three", recognitionProcesser.getNonZero3());
        }
        telemetry.update();
    }

    @Override
    public void loop() {


    }
}
