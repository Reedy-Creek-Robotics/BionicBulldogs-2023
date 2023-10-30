package org.firstinspires.ftc.teamcode.opmode.teleop.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.stream.CameraStreamSource;
@TeleOp
public class CameraTest extends LinearOpMode {
    public void runOpMode(){
        WebcamName camera = hardwareMap.get(WebcamName.class, "Webcam 1");
        //FtcDashboard.getInstance().startCameraStream(camera, 0);
        while (opModeIsActive()){

        }
    }
}
