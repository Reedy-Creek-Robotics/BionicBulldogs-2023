package org.firstinspires.ftc.teamcode.modules.hardware;

import com.qualcomm.robotcore.hardware.Gamepad;

public class GamepadEx {
    Gamepad gamepad;
    Gamepad curGamepad;
    Gamepad prevGamepad;
    public GamepadEx(Gamepad _gamepad){
        gamepad = _gamepad;
        curGamepad.copy(gamepad);
        prevGamepad = new Gamepad();
    }

    /**
     * copy gamepad for rising edge detection
     */
    public void copy(){
        prevGamepad.copy(curGamepad);
        curGamepad.copy(gamepad);
    }

    // buttons
    public boolean cross(){
        return gamepad.cross && !prevGamepad.cross;
    }
    public boolean square(){
        return gamepad.square && !prevGamepad.square;
    }
    public boolean circle(){
        return gamepad.circle && !prevGamepad.circle;
    }
    public boolean triangle(){
        return gamepad.triangle && !prevGamepad.triangle;
    }

    //dpad
    public boolean dpadUp(){
        return gamepad.dpad_up && !prevGamepad.dpad_up;
    }
    public boolean dpadDown(){
        return gamepad.dpad_down && !prevGamepad.dpad_down;
    }
    public boolean dpadLeft(){
        return gamepad.dpad_left && !prevGamepad.dpad_left;
    }
    public boolean dpadRight(){
        return gamepad.dpad_right && !prevGamepad.dpad_right;
    }

    //bumpers
    public boolean leftBumper(){
        return gamepad.left_bumper && !prevGamepad.left_bumper;
    }
    public boolean rightBumper(){
        return gamepad.right_bumper && !prevGamepad.right_bumper;
    }

    //options
    public boolean options(){
        return gamepad.options && ! prevGamepad.options;
    }

    //sticks
    public float leftStickX(){
        return gamepad.left_stick_x;
    }
    public float leftStickY(){
        return gamepad.left_stick_y;
    }
    public float rightStickX(){
        return gamepad.right_stick_x;
    }
    public float rightStickY(){
        return gamepad.right_stick_y;
    }

    //triggers
    public float leftTrigger(){
        return gamepad.left_trigger;
    }
    public float rightTrigger(){
        return gamepad.right_trigger;
    }
}
