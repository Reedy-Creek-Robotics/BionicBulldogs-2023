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

    /**
     * Changes wich item is selected
     * 
     * Call every tick before drawing any ui
     */
    public void update(){
        if(gamepad.dpadUp()){
            selectedItem--;
        }
        if(gamepad.dpadDown()) {
            selectedItem++;
        }
		  if(selectedItem >= itemCount){
			   selectedItem = itemCount - 1;
		  }
		  if(selectedItem < 0){
			   selectedItem = 0;
		  }
        itemCount = 0;
    }

    /**
     * Resets selected item to the first item
     */
    public void resetSelected(){
        selectedItem = 1;
    }

    /**
     * Adds a ui button
     * 
     * @param label label
     * @return was this button pressed
     */
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

    /**
     * Adds a float input
     * 
     * @param label label
     * @param value the value to be changed
     * @param step the value to increment or decrement by
     * @return was this value changed
     */
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

    /**
     * Adds an int input
     * 
     * @param label label
     * @param value the value to be changed
     * @param step the value to increment or decrement by
     * @return was this value changed
     */
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

    /**
     * Adds a checkbox
     * 
     * @param label label
     * @param value the value to be changed
     * @return was this value changed
     */
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
    
    /**
     * Adds a label
     * Labels cant be selected
     * 
     * @param label label
     */
    public void label(String label){
        telemetry.addLine(label);
    }

    /**
     * Adds a label
     * Labels cant be selected
     * 
     * @param label label
     * @param arg the object to add to the end of the label
     */
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
