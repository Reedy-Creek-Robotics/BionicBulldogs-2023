package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareDevice;

import org.firstinspires.ftc.teamcode.modules.hardware.GamepadEx;

public abstract class BaseTeleOp extends OpMode {
    public GamepadEx gamepadEx1;
    public GamepadEx gamepadEx2;

    /**
     * inits gamepads and telemetry for dashboard
     */
    public void init(){
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }
    public void start(){
        gamepadEx1 = new GamepadEx(gamepad1);
        gamepadEx2 = new GamepadEx(gamepad2);
    }
    /**
     * copies gamepads for rising edge detection
     */
    public final void copyGamepads(){
        gamepadEx1.copy();
        gamepadEx2.copy();
    }
    public final void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
