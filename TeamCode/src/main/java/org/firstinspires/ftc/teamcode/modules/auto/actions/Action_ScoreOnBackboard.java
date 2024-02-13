package org.firstinspires.ftc.teamcode.modules.auto.actions;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.modules.robot.Claw;
import org.firstinspires.ftc.teamcode.modules.robot.Robot;

public class Action_ScoreOnBackboard extends Action_Base{
    ElapsedTime elapsedTime;
    public void run(){
        elapsedTime = new ElapsedTime();
        delay(250);
        Robot.slides.gotoPositionBlock();
        delay(500);
        Robot.slides.scoreRotator();
        delay(450);
        flicker();
        delay(200);
        flicker();
        delay(200);
        Robot.slides.gotoPositionBlock(-900);
        Robot.drive.followTrajectorySequence(
                Robot.drive.trajectorySequenceBuilder(Robot.drive.getPoseEstimate())
                        .forward(5)
                        .build()
        );
        Robot.slides.resetRotator();
        delay(500);
        Robot.slides.reset();
        Robot.claw.openTop();
    }
    void flicker(){
        Robot.claw.push();
        delay(350);
        Robot.claw.resetFlicker();
    }
    void delay(float time){
        elapsedTime.reset();
        while(elapsedTime.milliseconds() < time);
    }
}
