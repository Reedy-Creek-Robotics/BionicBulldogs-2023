package org.firstinspires.ftc.teamcode.modules;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.ArrayList;

public class ServoGroup {
    public ServoGroup(){
        servos = new ArrayList<Servo>();
    }
    public void addServo(String name, boolean reversed, HardwareMap map){
        Servo servo = map.servo.get(name);
        servo.setDirection(
                reversed ?
                        Servo.Direction.REVERSE :
                        Servo.Direction.FORWARD
        );
        servos.add(servo);
    }
    public void addServo(Servo servo){
        servos.add(servo);
    }
    public Servo getServo(int i){
        return servos.get(i);
    }
    public int getSize(){
        return servos.size();
    }
    public void setPower(float power){
        for(Servo servo: servos){
            servo.setPosition(power);
        }
    }
    ArrayList<Servo> servos;
}
