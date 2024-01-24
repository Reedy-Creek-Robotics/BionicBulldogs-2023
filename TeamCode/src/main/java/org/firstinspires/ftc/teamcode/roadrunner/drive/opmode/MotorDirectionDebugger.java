package org.firstinspires.ftc.teamcode.roadrunner.drive.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;

import java.util.Map;
import java.util.TreeMap;

/**
 * This is a simple teleop routine for debugging your motor configuration.
 * Pressing each of the buttons will power its respective motor.
 *
 * Button Mappings:
 *
 * Xbox/PS4 Button - Motor
 *   X / ▢         - Front Left
 *   Y / Δ         - Front Right
 *   B / O         - Rear  Right
 *   A / X         - Rear  Left
 *                                    The buttons are mapped to match the wheels spatially if you
 *                                    were to rotate the gamepad 45deg°. x/square is the front left
 *                    ________        and each button corresponds to the wheel as you go clockwise
 *                   / ______ \
 *     ------------.-'   _  '-..+              Front of Bot
 *              /   _  ( Y )  _  \                  ^
 *             |  ( X )  _  ( B ) |     Front Left   \    Front Right
 *        ___  '.      ( A )     /|       Wheel       \      Wheel
 *      .'    '.    '-._____.-'  .'       (x/▢)        \     (Y/Δ)
 *     |       |                 |                      \
 *      '.___.' '.               |          Rear Left    \   Rear Right
 *               '.             /             Wheel       \    Wheel
 *                \.          .'              (A/X)        \   (B/O)
 *                  \________/
 *
 * Uncomment the @Disabled tag below to use this opmode.
 */
@Config
@Disabled
@TeleOp(group = "drive")
public class MotorDirectionDebugger extends LinearOpMode {
    public static double MOTOR_POWER = 0.7;

    @Override
    public void runOpMode() throws InterruptedException {
        Map<String, String> telemetryMap = new TreeMap<>();

        Telemetry telemetry = new MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().getTelemetry());

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        telemetry.addLine("Press play to begin the debugging opmode");
        telemetry.update();

        waitForStart();

        if (isStopRequested()) return;

        telemetry.clearAll();
        telemetry.setDisplayFormat(Telemetry.DisplayFormat.HTML);

        while (!isStopRequested()) {
            telemetry.addLine("Press each button to turn on its respective motor");
            telemetry.addLine();
            telemetry.addLine("Xbox/PS4 Button - Motor");
            telemetry.addLine("X / ▢- Front Left</font>");
            telemetry.addLine("Y / Δ- Front Right</font>");
            telemetry.addLine("B / O- Rear Right</font>");
            telemetry.addLine("A / X- Rear Left</font>");
            telemetry.addLine();

            if(gamepad1.x) {
                drive.setMotorPowers(MOTOR_POWER, 0, 0, 0);
                telemetryMap.put("motor", "Running Motor: Front Left");
                drive.telemetry(telemetryMap);
                telemetry.update();
            } else if(gamepad1.y) {
                drive.setMotorPowers(0, 0, 0, MOTOR_POWER);
                telemetryMap.put("motor", "Running Motor: Front Right");
                drive.telemetry(telemetryMap);
                telemetry.update();
            } else if(gamepad1.b) {
                drive.setMotorPowers(0, 0, MOTOR_POWER, 0);
                telemetryMap.put("motor", "Running Motor: Rear Right");
                drive.telemetry(telemetryMap);
                telemetry.update();
            } else if(gamepad1.a) {
                drive.setMotorPowers(0, MOTOR_POWER, 0, 0);
                telemetryMap.put("motor", "Running Motor: Rear Left");
                drive.telemetry(telemetryMap);
                telemetry.update();
            } else if(gamepad1.left_bumper){
                drive.setMotorPowers(MOTOR_POWER, MOTOR_POWER, MOTOR_POWER, MOTOR_POWER);
                drive.telemetry(telemetryMap);
                telemetry.update();
            } else {
                drive.setMotorPowers(0, 0, 0, 0);
            }
            telemetryMap.put("motor", "Running Motor: None");
            if(gamepad1.right_bumper){
                drive.resetEncoders();
            }
            for(String key : telemetryMap.keySet()){
                telemetry.addData(key, telemetryMap.get(key));
            }
            telemetry.update();
        }
    }
}
