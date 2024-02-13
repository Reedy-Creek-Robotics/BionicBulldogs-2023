package org.firstinspires.ftc.teamcode.modules.robot;

public enum ElementPosition {
    Left(1),
    Center(2),
    Right(3),
    UNKNOWN(-1);
    private final int value;
    private ElementPosition(int _value) {
        value = _value;
    }
    public int getValue(){
        return value;
    }
}
