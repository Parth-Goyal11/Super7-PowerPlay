package org.firstinspires.ftc.teamcode.T1_2022.Modules;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Utils.Motor;

import java.util.Objects;

public class Grabber {
    public final double CLAW_OPEN=1, CLAW_CLOSE=0,
    V4B_FRONT=1, V4B_BACK=0, V4B_FRONT_THRESHOLD=0.1;
    public final int HIGH=0, MIDDLE=0, LOW=0, REST=0;
    boolean armRested=true, v4bISFRONT=false;
    public String armStatusPrev = "rest";

    Motor leftSlide, rightSlide;
    Servo claw, v4bLeft, v4bRight;
    public Grabber(Motor l, Motor r, Servo ls, Servo rs , Servo g){
        leftSlide = l; rightSlide = r;
        v4bLeft = ls; v4bRight = rs;
        claw = g;

        leftSlide.resetEncoder(true);
        rightSlide.resetEncoder(true);
        leftSlide.toPosition();
        rightSlide.toPosition();
    }

    public void raiseTop(){
        setV4B_FRONT();
        raiseToPosition(HIGH, 0.5);
        armRested = false;
    }

    public void raiseMiddle(){
        setV4B_FRONT();
        raiseToPosition(MIDDLE, 0.5);
        armRested = false;
    }

    public void raiseLow(){
        setV4B_FRONT();
        raiseToPosition(LOW, 0.5);
        armRested = false;
    }

    public void restArm(){
        raiseToPosition(REST, 0.5);
        armRested = true;
    }

    public void updateArmPos(String armStatus){
//        if(!Objects.equals(armStatusPrev, armStatus)) {
//            if (Objects.equals(armStatus, "high")) {
//                raiseTop();
//            } else if (Objects.equals(armStatus, "low")) {
//                raiseLow();
//            } else if (Objects.equals(armStatus, "middle")) {
//                raiseMiddle();
//            } else if (Objects.equals(armStatus, "rest")) {
//                setV4B_BACK();
//                if (v4bRight.getPosition() <= V4B_FRONT_THRESHOLD) {
//                    restArm();
//                }
//            }
//            armStatusPrev = armStatus;
//        }

            if (Objects.equals(armStatus, "high")) {
                raiseTop();
            } else if (Objects.equals(armStatus, "low")) {
                raiseLow();
            } else if (Objects.equals(armStatus, "middle")) {
                raiseMiddle();
            } else if (Objects.equals(armStatus, "rest")) {
                setV4B_BACK();
                if (v4bRight.getPosition() <= V4B_FRONT_THRESHOLD) {
                    restArm();
                }
            }
    }



    public void raiseToPosition(int pos, double power){
        leftSlide.setTarget(pos);
        leftSlide.retMotorEx().setTargetPositionTolerance(3);
        leftSlide.toPosition();
        leftSlide.setPower(power);

        rightSlide.setTarget(pos);
        rightSlide.retMotorEx().setTargetPositionTolerance(3);
        rightSlide.toPosition();
        rightSlide.setPower(power);
    }

    public void raiseToPosition(int pos){
        raiseToPosition(pos, 0.2);
    }

    public void grabCone(){claw.setPosition(CLAW_OPEN);}
    public void releaseCone(){claw.setPosition(CLAW_CLOSE);}

    public void setV4B_FRONT() {
        v4bLeft.setPosition(V4B_FRONT);
        v4bRight.setPosition(V4B_FRONT);
        v4bISFRONT = true;
    }

    public void setV4B_BACK() {
        v4bLeft.setPosition(V4B_BACK);
        v4bRight.setPosition(V4B_BACK);
        v4bISFRONT = false;
    }

    public int getSlidePos(){
        return Math.max(leftSlide.encoderReading(), rightSlide.encoderReading());
    }
}
