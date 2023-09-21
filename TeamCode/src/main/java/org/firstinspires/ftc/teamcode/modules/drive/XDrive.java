package org.firstinspires.ftc.teamcode.modules.drive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.config.RobotConfig;
import org.firstinspires.ftc.teamcode.modules.hardware.MotorGroup;

public class XDrive implements HDrive {
    MotorGroup motors;
    DcMotor front;
    DcMotor back;
    DcMotor left;
    DcMotor right;

    public void init(HardwareMap hmap){
        front = RobotConfig.getFront(hmap);
        back = RobotConfig.getBack(hmap);
        left = RobotConfig.getLeft(hmap);
        right = RobotConfig.getRight(hmap);

        motors.addMotor(front);
        motors.addMotor(back);
        motors.addMotor(left);
        motors.addMotor(right);

        motors.setRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motors.setZeroPower(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public void update(float forward, float strafe, float rotate){
        front.setPower(strafe + rotate);
        back.setPower(strafe - rotate);
        left.setPower(forward - rotate);
        right.setPower(forward + rotate);
    }
    public void updateFR(float forward, float strafe, float rotate, float heading){
        forward = (float) -(forward * Math.cos(heading) - strafe * Math.sin(heading));
        strafe = (float) -(forward * Math.sin(heading) + strafe * Math.cos(heading));
        front.setPower(strafe + rotate);
        back.setPower(strafe - rotate);
        left.setPower(forward - rotate);
        right.setPower(forward + rotate);
    }
}
