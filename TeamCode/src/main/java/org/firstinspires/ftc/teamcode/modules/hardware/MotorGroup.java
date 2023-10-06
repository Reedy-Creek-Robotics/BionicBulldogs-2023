package org.firstinspires.ftc.teamcode.modules.hardware;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import java.util.ArrayList;

public class MotorGroup {

    ArrayList<DcMotor> motors;
    DcMotor.RunMode runMode;
    boolean runToPos = false;
    int pos;

    public MotorGroup() {
        motors = new ArrayList<DcMotor>();
    }

//    public void addMotor(String name, boolean reversed) {
//        DcMotor motor = op.hardwareMap.dcMotor.get(name);
//        motor.setDirection(
//                reversed ?
//                        DcMotorSimple.Direction.REVERSE :
//                        DcMotorSimple.Direction.FORWARD
//        );
//        motors.add(motor);
//    }

    public void addMotor(DcMotor motor) {
        motors.add(motor);
    }

    public DcMotor getMotor(int i) {
        return motors.get(i);
    }

    public int getSize() {
        return motors.size();
    }

    public void setRunMode(DcMotor.RunMode runMode) {
        runMode = runMode;
        for (DcMotor motor : motors) {
            motor.setMode(runMode);
        }
    }

    public void setZeroPower(DcMotor.ZeroPowerBehavior zeroPowerBehavior) {
        for (DcMotor motor : motors) {
            motor.setZeroPowerBehavior(zeroPowerBehavior);
        }
    }

    public void setPower(float power) {
        for (DcMotor motor : motors) {
            motor.setPower(power);
        }
    }

    public void resetEncoders() {
        for (DcMotor motor : motors) {
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setMode(runMode);
        }
    }

    public void runToPosition(int _pos, float power) {
        pos = _pos;
        runToPos = true;
        setRunMode(DcMotor.RunMode.RUN_TO_POSITION);
        for (DcMotor motor : motors) {
            motor.setTargetPosition(pos);
        }
        setPower(power);
    }

    public void runToPosUpdate() {
        if (!runToPos) {
            return;
        }
        boolean moving = false;
        for (DcMotor motor : motors) {
            moving |= motor.isBusy();
        }
        if (!moving) {
            setPower(0);
            runToPos = false;
        }
    }

}