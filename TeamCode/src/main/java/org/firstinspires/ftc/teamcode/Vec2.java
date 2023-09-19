package org.firstinspires.ftc.teamcode;

import org.opencv.core.Mat;

public class Vec2 {
    public float x;
    public float y;
    public Vec2(float _x, float _y){
        x = _x;
        y = _y;
    }
    public Vec2(float _x){
        x = _x;
        y = _x;
    }
    public Vec2(){
        x = 0;
        y = 0;
    }
    public Vec2 add(Vec2 other){
        return new Vec2(x + other.x, y + other.y);
    }
    public Vec2 sub(Vec2 other){
        return new Vec2(x - other.x, y - other.y);
    }
    public Vec2 mul(Vec2 other){
        return new Vec2(x * other.x, y * other.y);
    }
    public Vec2 div(Vec2 other){
        return new Vec2(x / other.x, y / other.y);
    }
    public float length(){
        return (float)Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
    public Vec2 neg(){
        return new Vec2(-x, -y);
    }
}
