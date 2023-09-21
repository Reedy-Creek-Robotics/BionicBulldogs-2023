package org.firstinspires.ftc.teamcode.config;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class RobotConfig {
    static String front = "front";
    static String back = "back";
    static String left  = "left";
    static String right = "right";
    public static DcMotor getFront(HardwareMap hw){
        return hw.dcMotor.get(front);
    }
    public static DcMotor getBack(HardwareMap hw){
        return hw.dcMotor.get(back);
    }
    public static DcMotor getLeft(HardwareMap hw){
        return hw.dcMotor.get(left);
    }
    public static DcMotor getRight(HardwareMap hw){
        return hw.dcMotor.get(right);
    }
}
