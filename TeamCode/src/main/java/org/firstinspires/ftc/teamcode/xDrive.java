package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.ImuOrientationOnRobot;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class xDrive extends LinearOpMode {

    public Vec2 getVel(Vec2 target, Vec2 start, float velStep){
        if(start.sub(target).length() < velStep){return target;}
        Vec2 a = target.sub(start);
        Vec2 b = a.div(new Vec2(a.neg().length()));
        Vec2 c = b.mul(new Vec2(velStep));
        Vec2 d = c.add(start);
        return d;
    }
    public void runOpMode(){

        //IMU imu = hardwareMap.get(IMU.class, "imu");

        //imu.initialize(new IMU.Parameters(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.)));

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        DcMotor frontLeft = hardwareMap.dcMotor.get("frontLeft");
        DcMotor frontRight = hardwareMap.dcMotor.get("frontRight");
        DcMotor backLeft = hardwareMap.dcMotor.get("backLeft");
        DcMotor backRight = hardwareMap.dcMotor.get("backRight");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);

        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        float powerFactor = 0.8f;
        Vec2 prevVel = new Vec2(0,0);
        waitForStart();
        float vStep = 0.1f;
        float flEnc = 0;
        float frEnc = 0;
        float blEnc = 0;
        float brEnc = 0;
        ElapsedTime e = new ElapsedTime();
        while(opModeIsActive()){
            float forward = -gamepad1.left_stick_y;
            float strafe = gamepad1.left_stick_x;
            float rotate = -gamepad1.right_stick_x;

            if(gamepad1.dpad_up){
                forward = 1;
            }
            if(gamepad1.dpad_down){
                forward = -1;
            }
            if(gamepad1.dpad_left){
                strafe = -1;
            }
            if(gamepad1.dpad_right){
                strafe = 1;
            }
            if(gamepad1.b){
                rotate = 1;
            }if(gamepad1.x){
                rotate = 1;
            }
            //Vec2 vel = getVel(new Vec2(forward, strafe), prevVel, vStep);
            Vec2 vel = new Vec2(forward, strafe);

            frontLeft.setPower((vel.x * powerFactor) + (vel.y * powerFactor) + rotate);
            frontRight.setPower((vel.x * powerFactor) - (vel.y * powerFactor) - rotate);
            backLeft.setPower((vel.x * powerFactor) - (vel.y * powerFactor) + rotate);
            backRight.setPower((vel.x * powerFactor) + (vel.y * powerFactor) - rotate);

            if(gamepad1.y && e.seconds() > 0.25){
                if(vStep < 3) {
                    vStep += 0.01f;
                    e.reset();
                }
            }
            if(gamepad1.a && e.seconds() > 0.25){
                if(vStep > 0) {
                    vStep -= 0.01f;
                    e.reset();
                }
            }
            float currentFlEnc = frontLeft.getCurrentPosition();
            float currentFrEnc = frontRight.getCurrentPosition();
            float currentBlEnc = backLeft.getCurrentPosition();
            float currentBrEnc = backRight.getCurrentPosition();

            //telemetry.addData("vStep: ", vStep);
            telemetry.addData("stick: ", forward + ", " + strafe);
            telemetry.addData("flEnc: ", frontLeft.getCurrentPosition());
            telemetry.addData("frEnc: ", frontRight.getCurrentPosition());
            telemetry.addData("blEnc: ", backLeft.getCurrentPosition());
            telemetry.addData("brEnc: ", backRight.getCurrentPosition());
            telemetry.addData("flCep: ", currentFlEnc - flEnc);
            telemetry.addData("frCep: ", currentFrEnc - frEnc);
            telemetry.addData("blCep: ", currentBlEnc - blEnc);
            telemetry.addData("brCep: ", currentBrEnc - brEnc);
            telemetry.addData("flPwr: ", (vel.x * powerFactor) + (vel.y * powerFactor) + rotate);
            telemetry.addData("frPwr", (vel.x * powerFactor) - (vel.y * powerFactor) - rotate);
            telemetry.addData("blPwr", (vel.x * powerFactor) - (vel.y * powerFactor) + rotate);
            telemetry.addData("brPwr", (vel.x * powerFactor) + (vel.y * powerFactor) - rotate);
            //telemetry.addData("vel: ", vel.x + ", " + vel.y);
            //telemetry.addData("prevVel: ", prevVel.x + ", " + prevVel.y);
            flEnc = currentFlEnc;
            frEnc = currentFrEnc;
            blEnc = currentBlEnc;
            brEnc = currentBrEnc;
            telemetry.update();
            prevVel = vel;
        }
    }
}
