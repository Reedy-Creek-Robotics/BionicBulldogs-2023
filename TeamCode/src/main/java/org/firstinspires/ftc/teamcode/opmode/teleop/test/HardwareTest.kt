package org.firstinspires.ftc.teamcode.opmode.teleop.test

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit
import org.firstinspires.ftc.teamcode.modules.Ui.FloatPtr
import org.firstinspires.ftc.teamcode.modules.Ui.BoolPtr
import org.firstinspires.ftc.teamcode.modules.Ui.IntPtr
import org.firstinspires.ftc.teamcode.modules.Ui.UI

@TeleOp
class HardwareTest : OpMode {
    private enum class Mode{
        Motor, Servo, None
    }
    private var ui: UI = UI();
    private var mode: Mode = Mode.None;

    private var index: IntPtr = IntPtr();

    private var motor: DcMotorEx? = null;
    private var motorList: MutableList<DcMotorEx> = ArrayList();
    private val motorPower: FloatPtr = FloatPtr();
    private val motorTargetPosition: IntPtr = IntPtr();
    private val motorUpdateTargetPosition: BoolPtr = BoolPtr();


    private var servo: Servo? = null;
    private var servoList: MutableList<Servo> = ArrayList();
    private val servoPosition: FloatPtr = FloatPtr();

    constructor(){}
    override fun init(){
        ui.init(telemetry, gamepad1)
        hardwareMap.forEach() {
            if(it is DcMotorEx){
                motorList.add(it);
            }
            if(it is Servo){
                servoList.add(it);
            }
        }
    }
    override fun start(){}
    override fun loop(){
        when(mode) {
            Mode.None->{
                if (ui.button("motor")) {
                    mode = Mode.Motor;
                    index.value = 0;
                    motor = motorList[index.value];
                }
                if (ui.button("servo")) {
                    mode = Mode.Servo;
                    index.value = 0;
                    servo = servoList[index.value];
                }
            }
            Mode.Motor->{
                if(ui.button("back")) {
                    mode = Mode.None;
                }
                if(ui.intInput("index", index, 1)){
                    if(index.value >= motorList.size) {
                        index.value = motorList.size - 1;
                    }
                    if(index.value <= 0) {
                        index.value = 0;
                    }
                    motor = motorList[index.value];
                }
                ui.label("name" , motor?.connectionInfo);
                ui.label("port" , motor?.portNumber);
                ui.label("currentPosition", motor?.currentPosition);
                ui.label("velocity", motor?.velocity);
                ui.label("current", motor?.getCurrent(CurrentUnit.MILLIAMPS));
                if(ui.floatInput("power", motorPower, 0.1f)) {
                    motor?.power = motorPower.value.toDouble();
                }
                if(ui.button("stop")){
                    motor?.power = 0.0;
                    motorPower.value = 0.0f;
                }
                if(ui.checkbox("updateTargetPosition", motorUpdateTargetPosition)){
                  motor?.targetPosition = motorTargetPosition.value;
                }
                if(ui.intInput("targetPosition", motorTargetPosition, 50)){
                  if(motorUpdateTargetPosition.value){
                    motor?.targetPosition = motorTargetPosition.value;
                  }
                } 
            }
            Mode.Servo->{
                if(ui.button("back")) {
                    mode = Mode.None;
                }
                if(ui.intInput("index", index, 1)){
                    if(index.value >= servoList.size) {
                        index.value = servoList.size - 1;
                    }
                    if(index.value <= 0) {
                        index.value = 0;
                    }
                    servo = servoList[index.value];
                }
                ui.label("name" , servo?.connectionInfo);
                ui.label("port" , servo?.portNumber);
                if(ui.floatInput("position", servoPosition, 0.05f)) {
                    servo?.position = motorPower.value.toDouble();
                }
                if(ui.button("reset position")){
                    servo?.position = 0.0;
                    servoPosition.value = 0.0f;
                }
            }
        }
        ui.update();
    }
  }
