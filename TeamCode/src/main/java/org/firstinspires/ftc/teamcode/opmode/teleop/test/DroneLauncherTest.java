package org.firstinspires.ftc.teamcode.opmode.teleop.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.modules.robot.DroneLauncher;
import org.firstinspires.ftc.teamcode.opmode.config.DroneLauncherConfig;
import org.firstinspires.ftc.teamcode.opmode.teleop.BaseTeleOp;
@Disabled
@TeleOp
public class DroneLauncherTest extends BaseTeleOp {
    DroneLauncher droneLauncher;
    public void init(){
        super.init();
        droneLauncher = new DroneLauncher(new DroneLauncherConfig(hardwareMap));
    }
    public void start(){
        droneLauncher.reset();
    }
    public void loop(){
        if(gamepadEx1.cross()){
            droneLauncher.launch();
        }
        copyGamepads();
    }
}
