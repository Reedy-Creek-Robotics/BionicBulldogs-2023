package org.firstinspires.ftc.teamcode.modules.Ui;


import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/*
up - dpad up;
down - dpad down;
select/inc - dpad right;
dec - dpad left;
 */

public class UI {
    Telemetry telemetry;
    Gamepad gamepad;
    int itemCount = 0;
    int selectedItem = 1;

    boolean prevUp = false;
    boolean prevDown = false;
    boolean prevRight = false;
    boolean prevLeft = false;

    public UI(Telemetry telem, Gamepad _gamepad){
        gamepad = _gamepad;
        telemetry = telem;
    }

    public void update(){
        if(gamepad.dpad_up && !prevUp){
            selectedItem--;
            prevUp = true;
        }else if(!gamepad.dpad_up){
            prevUp = false;
        }
        if(gamepad.dpad_down && !prevDown){
            selectedItem++;
            prevDown = true;
        }else if(!gamepad.dpad_down){
            prevDown = false;
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
            if(gamepad.dpad_right && !prevRight) {
                prevRight = true;
                return true;
            }else if(!gamepad.dpad_right){
                prevRight = false;
            }
        }
        return false;
    }

    public boolean floatInput(String label, FloatEx value, float step){
        itemCount++;
        addLine(label + ": " + value.value);
        if(selectedItem == itemCount) {
            if (gamepad.dpad_right && !prevRight) {
                prevRight = true;
                value.value += step;
                return true;
            } else if (!gamepad.dpad_right) {
                prevRight = false;
            }
            if (gamepad.dpad_left && !prevLeft) {
                prevLeft = true;
                value.value -= step;
                return true;
            } else if (!gamepad.dpad_left) {
                prevLeft = false;
            }
        }
        return false;
    }

    public boolean intInput(String label, IntEx value, int step){
        itemCount++;
        addLine(label + ": " + value.value);
        if(selectedItem == itemCount) {
            if (gamepad.dpad_right && !prevRight) {
                prevRight = true;
                value.value += step;
                return true;
            } else if (!gamepad.dpad_right) {
                prevRight = false;
            }
            if (gamepad.dpad_left && !prevLeft) {
                prevLeft = true;
                value.value -= step;
                return true;
            } else if (!gamepad.dpad_left) {
                prevLeft = false;
            }
        }
        return false;
    }

    public boolean checkbox(String label, BoolEx value){
        itemCount++;
        addLine(label + ": " + (value.value ? "[V]" : "[X]"));
        if(selectedItem == itemCount) {
            if (gamepad.dpad_right && !prevRight) {
                prevRight = true;
                value.value = !value.value;
                return true;
            } else if (!gamepad.dpad_right) {
                prevRight = false;
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
