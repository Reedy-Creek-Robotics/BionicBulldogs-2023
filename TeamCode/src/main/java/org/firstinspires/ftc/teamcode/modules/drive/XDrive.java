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
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
    float f;
    float r;
    ImuEx imu;

    public void init(XDriveConfig config){
        frontLeft = config.getFrontLeft();
        frontRight = config.getFrontRight();
        backLeft = config.getBackLeft();
        backRight = config.getBackRight();

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);

        motors = new MotorGroup();
        motors.addMotor(frontLeft);
        motors.addMotor(frontRight);
        motors.addMotor(backLeft);
        motors.addMotor(backRight);

        motors.resetEncoders();
        motors.setRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motors.setZeroPower(DcMotor.ZeroPowerBehavior.BRAKE);

        imu = new ImuEx(config.getImu());
    }

    /**
     * Drives robot with current heading
     * @param forward
     * @param strafe
     * @param rotate
     */
    public void driveCH(float forward, float strafe, float rotate){
        drive(forward, strafe, rotate, imu.getHeading(AngleUnit.RADIANS));
    }

    public void drive(float forward, float strafe, float rotate, float heading){
        f = (float)(forward * Math.cos(heading) - strafe * Math.sin(heading));
        r = (float)(forward * Math.sin(heading) + strafe * Math.cos(heading));
        drive(f, r, rotate);
    }

    public void drive(float forward, float strafe, float rotate){
        frontLeft.setPower(forward + strafe + rotate);
        backLeft.setPower(forward - strafe + rotate);
        frontRight.setPower(forward - strafe - rotate);
        backRight.setPower(forward + strafe - rotate);
        f = forward;
        r = strafe;
    }

    public void telem(Telemetry t){
        t.addData("rad", imu.getHeading(AngleUnit.RADIANS));
        t.addData("deg", imu.getHeading(AngleUnit.DEGREES));
        t.addData("forward", f);
        t.addData("strafe", r);
        t.addData("fl", frontLeft.getCurrentPosition());
        t.addData("fr", frontRight.getCurrentPosition());
        t.addData("bl", backLeft.getCurrentPosition());
        t.addData("br", backRight.getCurrentPosition());
    }
}
