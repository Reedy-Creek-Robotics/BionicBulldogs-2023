package org.firstinspires.ftc.teamcode.modules.auto.actions;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.modules.robot.Claw;
import org.firstinspires.ftc.teamcode.modules.robot.Robot;

public class Action_ScoreOnBackboard extends Action_Base{
    ElapsedTime elapsedTime;
    int slideHeight;
    boolean secondUp;
    boolean slidesUp;
    public Action_ScoreOnBackboard(int _slideHeight, boolean _secondUp, boolean _slidesUp){
        slideHeight = _slideHeight;
        secondUp = _secondUp;
        slidesUp = _slidesUp;
    }
    public Action_ScoreOnBackboard(){
        slideHeight = -1000;
        secondUp = true;
        slidesUp = true;
    }
    public void run(){
        elapsedTime = new ElapsedTime();
        if(slidesUp) {
            delay(250);
            Robot.slides.gotoPositionBlock(slideHeight);
            delay(500);
        }
        Robot.slides.scoreRotator();
        delay(450);
        flicker();
        delay(200);
        flicker();
        delay(200);
        if(secondUp) {
            Robot.slides.gotoPositionBlock(slideHeight - 200);
            Robot.drive.followTrajectorySequence(
                    Robot.drive.trajectorySequenceBuilder(Robot.drive.getPoseEstimate())
                            .forward(5)
                            .build()
            );
        }
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
