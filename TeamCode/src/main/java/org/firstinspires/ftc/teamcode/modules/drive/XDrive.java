package org.firstinspires.ftc.teamcode.modules.drive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.opmode.config.XDriveConfig;
import org.firstinspires.ftc.teamcode.modules.hardware.ImuEx;
import org.firstinspires.ftc.teamcode.modules.hardware.MotorGroup;

public class XDrive {
    MotorGroup motors;
    DcMotor front;
    DcMotor back;
    DcMotor left;
    DcMotor right;
    float f;
    float r;
    ImuEx imu;

    public void init(XDriveConfig config){
        front = config.getFront();
        back = config.getBack();
        left = config.getLeft();
        right = config.getRight();

        front.setDirection(DcMotorSimple.Direction.FORWARD);
        back.setDirection(DcMotorSimple.Direction.REVERSE);
        left.setDirection(DcMotorSimple.Direction.REVERSE);
        right.setDirection(DcMotorSimple.Direction.FORWARD);

        motors = new MotorGroup();
        motors.addMotor(front);
        motors.addMotor(back);
        motors.addMotor(left);
        motors.addMotor(right);

        motors.setRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motors.setZeroPower(DcMotor.ZeroPowerBehavior.BRAKE);

        imu = new ImuEx(config.getImu());
    }

    public void update(float forward, float strafe, float rotate){
        front.setPower(strafe + rotate);
        back.setPower(strafe - rotate);
        left.setPower(forward - rotate);
        right.setPower(forward + rotate);
    }
    public void updateFR(float forward, float strafe, float rotate){
        updateFR(forward, strafe, rotate, imu.getHeading(AngleUnit.RADIANS));
    }

    public void updateFR(float forward, float strafe, float rotate, float heading){
        f = (float)(forward * Math.cos(heading) - strafe * Math.sin(heading));
        r = (float)(forward * Math.sin(heading) + strafe * Math.cos(heading));
        front.setPower(r + rotate);
        back.setPower(r - rotate);
        left.setPower(f - rotate);
        right.setPower(f + rotate);
        f = forward;
        r = strafe;
    }
    public void telem(Telemetry t){
        t.addData("rad", imu.getHeading(AngleUnit.RADIANS));
        t.addData("deg", imu.getHeading(AngleUnit.DEGREES));
        t.addData("forward", f);
        t.addData("strafe", r);
        t.update();
    }
}
