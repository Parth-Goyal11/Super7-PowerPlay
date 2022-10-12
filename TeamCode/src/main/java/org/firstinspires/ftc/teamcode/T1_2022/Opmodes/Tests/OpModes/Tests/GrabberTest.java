package org.firstinspires.ftc.teamcode.T1_2022.Opmodes.Tests.OpModes.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.T1_2022.Modules.Grabber;
import org.firstinspires.ftc.teamcode.Utils.Motor;

@TeleOp(name = "Armtest", group = "OdomBot")
public class GrabberTest extends LinearOpMode {
  @Override
  public void runOpMode() throws InterruptedException {
    Servo s = hardwareMap.get(Servo.class, "claw"),
        lv = hardwareMap.get(Servo.class, "v4bl"),
        rv = hardwareMap.get(Servo.class, "v4br");
    TouchSensor t = hardwareMap.get(TouchSensor.class, "resetSensor");
    boolean clawLast = false, clawCurr = false, clawOpen = false;


    Motor ls = new Motor(hardwareMap, "leftSlide"), rs = new Motor(hardwareMap, "rightSlide");
    Grabber grabber = new Grabber(ls, rs, lv, rv, s, t);
    String grabberStat = "rest";
    while (opModeIsActive()) {

      if (gamepad2.a) {
        grabberStat = "high";
      }

      if (gamepad1.x) {
        grabberStat = "rest";
      }

      if (gamepad1.y) {
        grabberStat = "middle";
      }
      if(gamepad1.b){
        grabberStat = "low";
      }

      if(gamepad2.left_stick_y > 0.05){
        ls.setPower(gamepad2.left_stick_y * -0.2);
        rs.setPower(gamepad2.left_stick_y * -0.2);
      }

      if(gamepad2.dpad_up){
        lv.setPosition(lv.getPosition() + 0.01);
        rv.setPosition(rv.getPosition() + 0.01);
      }else if(gamepad2.dpad_down){
        lv.setPosition(lv.getPosition() - 0.01);
        rv.setPosition(rv.getPosition() - 0.01);
      }

      clawLast = clawCurr;
      clawCurr = gamepad2.right_bumper; //gamepad1.a
      if(clawCurr && !clawLast){
        clawOpen = !clawOpen;
        if(clawOpen){
          s.setPosition(grabber.CLAW_OPEN);
        }else{
          s.setPosition(grabber.CLAW_CLOSE);
        }
      }

      grabber.updateArmPos(grabberStat);
    }
  }
}
