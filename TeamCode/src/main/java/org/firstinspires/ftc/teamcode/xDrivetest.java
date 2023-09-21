package org.firstinspires.ftc.teamcode;

//import com.acmerobotics.dashboard.FtcDashboard;
//import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp
    public class xDrivetest extends LinearOpMode {

        public Vec2 getVel(Vec2 target, Vec2 start, float velStep){
            if(start.sub(target).length() < velStep){return target;}
            Vec2 a = target.sub(start);
            Vec2 b = a.div(new Vec2(a.neg().length()));
            Vec2 c = b.mul(new Vec2(velStep));
            Vec2 d = c.add(start);
            return d;
        }
        public void runOpMode(){

            //telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
            DcMotor front = hardwareMap.dcMotor.get("front");
            DcMotor back = hardwareMap.dcMotor.get("back");
            DcMotor left = hardwareMap.dcMotor.get("left");
            DcMotor right = hardwareMap.dcMotor.get("right");

            front.setDirection(DcMotorSimple.Direction.FORWARD);
            back.setDirection(DcMotorSimple.Direction.REVERSE);
            left.setDirection(DcMotorSimple.Direction.REVERSE);
            right.setDirection(DcMotorSimple.Direction.FORWARD);

            front.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            back.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            front.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            back.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            front.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            back.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            //Imu imu = new Imu(this);

            float powerFactor = 1.0f;
            Vec2 prevVel = new Vec2(0,0);
            waitForStart();
            float vStep = 0.1f;
            float flEnc = 0;
            float frEnc = 0;
            float blEnc = 0;
            float brEnc = 0;
            ElapsedTime e = new ElapsedTime();
            while(opModeIsActive()){
                //float angle = imu.getHeading(AngleUnit.RADIANS);
                float forward = -gamepad1.left_stick_y;
                float strafe = gamepad1.left_stick_x;
                float rotate = -gamepad1.right_stick_x * powerFactor;
                //forward = (float) -(forward * Math.cos(angle) - strafe * Math.sin(angle));
                //strafe = (float) -(forward * Math.sin(angle) + strafe * Math.cos(angle));

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

                front.setPower((vel.x * powerFactor) + rotate);
                back.setPower((vel.x * powerFactor) - rotate);
                left.setPower((vel.y * powerFactor) - rotate);
                right.setPower((vel.y * powerFactor) + rotate);

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
                float currentFlEnc = front.getCurrentPosition();
                float currentFrEnc = back.getCurrentPosition();
                float currentBlEnc = left.getCurrentPosition();
                float currentBrEnc = right.getCurrentPosition();

                //telemetry.addData("vStep: ", vStep);
                telemetry.addData("stick: ", forward + ", " + strafe);
                telemetry.addData("flEnc: ", front.getCurrentPosition());
                telemetry.addData("frEnc: ", back.getCurrentPosition());
                telemetry.addData("blEnc: ", left.getCurrentPosition());
                telemetry.addData("brEnc: ", right.getCurrentPosition());
                telemetry.addData("flCep: ", currentFlEnc - flEnc);
                telemetry.addData("frCep: ", currentFrEnc - frEnc);
                telemetry.addData("blCep: ", currentBlEnc - blEnc);
                telemetry.addData("brCep: ", currentBrEnc - brEnc);
                telemetry.addData("flPwr: ", (vel.x * powerFactor) + (vel.y * powerFactor) + rotate);
                telemetry.addData("frPwr", (vel.x * powerFactor) - (vel.y * powerFactor) - rotate);
                telemetry.addData("blPwr", (vel.x * powerFactor) - (vel.y * powerFactor) + rotate);
                telemetry.addData("brPwr", (vel.x * powerFactor) + (vel.y * powerFactor) - rotate);
                //telemetry.addData("heading", imu.getHeading(AngleUnit.DEGREES));
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

