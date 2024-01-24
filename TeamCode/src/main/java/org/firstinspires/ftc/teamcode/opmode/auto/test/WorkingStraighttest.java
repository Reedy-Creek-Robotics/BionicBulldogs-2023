package org.firstinspires.ftc.teamcode.opmode.auto.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.opmode.config.XDriveConfig;

@Autonomous
@Disabled
public class WorkingStraighttest extends LinearOpMode {
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
    DcMotor[] motors;

    @Override
    public void runOpMode() throws InterruptedException {
        // INIT
        XDriveConfig config = new XDriveConfig(hardwareMap);
        frontLeft = config.getFrontLeft();
        frontRight = config.getFrontRight();
        backLeft = config.getBackLeft();
        backRight = config.getBackRight();
        motors = new DcMotor[]{frontLeft, frontRight, backLeft, backRight};
        initMotors();

        waitForStart();

        // START
        run(48);

        boolean done = false;
        while(!done) {
            telemetry.addData("fl", frontLeft.getCurrentPosition());
            telemetry.addData("fr", frontRight.getCurrentPosition());
            telemetry.addData("bl", backLeft.getCurrentPosition());
            telemetry.addData("br", backRight.getCurrentPosition());
            telemetry.update();
            done = !frontLeft.isBusy() && !frontRight.isBusy() && !backLeft.isBusy() && !backRight.isBusy();
        }

        // set power (to off )
        for(DcMotor m : motors) {
            m.setPower(0);
        }
    }

    // ticks-per-inch = (ticks / rev) / (inches / rev)
    // 384.5 ticks / rev
    // 1 rev = 120 mm * 3.1415 = 4.7244 in * 3.1415 = 14.8417 inches / rev
    // 384.5 ticks/rev / 14.8417 inches/rev = 25.9067 ticks / inch
    double TICKS_PER_INCH = 25.9067;

    protected void run(int distance) {
        // set position and change mode
        int targetPosition = distance * (int)TICKS_PER_INCH;
        for(DcMotor m : motors) {
            m.setTargetPosition(targetPosition);
            m.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }

        // set power (runs it)
        for(DcMotor m : motors) {
            m.setPower(0.5);
        }
    }

    protected void initMotors() {
        frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);

        for(DcMotor m : motors) {
            m.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            m.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            m.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
    }
}


