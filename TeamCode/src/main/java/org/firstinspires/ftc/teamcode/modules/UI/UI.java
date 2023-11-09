package org.firstinspires.ftc.teamcode.modules.UI;


import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.modules.hardware.GamepadEx;

/*
up - dpad up;
down - dpad down;
select/inc - dpad right;
dec - dpad left;
 */

public class UI {
    Telemetry telemetry;
    GamepadEx gamepad;
    int itemCount = 0;
    int selectedItem = 1;

    public UI(Telemetry telem, GamepadEx _gamepad){
        gamepad = _gamepad;
        telemetry = telem;
    }

    public void update(){
        if(gamepad.dpadUp()){
            selectedItem--;
        }
        if(gamepad.dpadDown()) {
            selectedItem++;
        }
        itemCount = 0;
    }

    public void resetSelected(){
        selectedItem = 1;
    }

    public boolean button(String label){
        itemCount++;
        addLine(label);
        if(selectedItem == itemCount){
            if(gamepad.dpadRight()) {
                return true;
            }
        }
        return false;
    }

    public boolean floatInput(String label, FloatEx value, float step){
        itemCount++;
        addLine(label + ": " + value.value);
        if(selectedItem == itemCount) {
            if (gamepad.dpadRight()) {
                value.value += step;
                return true;
            }
            if (gamepad.dpadRight()) {
                value.value -= step;
                return true;
            }
        }
        return false;
    }

    public boolean intInput(String label, IntEx value, int step){
        itemCount++;
        addLine(label + ": " + value.value);
        if(selectedItem == itemCount) {
            if (gamepad.dpadRight()) {
                value.value += step;
                return true;
            }
            if (gamepad.dpadLeft()) {
                value.value -= step;
                return true;
            }
        }
        return false;
    }

    public boolean checkbox(String label, BoolEx value){
        itemCount++;
        addLine(label + ": " + (value.value ? "[V]" : "[X]"));
        if(selectedItem == itemCount) {
            if (gamepad.dpadRight()) {
                value.value = !value.value;
                return true;
            }
        }
        return false;
    }

    public void label(String label){
        telemetry.addLine(label);
    }

    public void label(String label, Object arg){
        telemetry.addLine(label + ": " + arg.toString());
    }

    void addLine(String label){
        if(selectedItem == itemCount){
            telemetry.addLine("--" + label + "--");
        }else{
            telemetry.addLine(label);
        }
    }
}
