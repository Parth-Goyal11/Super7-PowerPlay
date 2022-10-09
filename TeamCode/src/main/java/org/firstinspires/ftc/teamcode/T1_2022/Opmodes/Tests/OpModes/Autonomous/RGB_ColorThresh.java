package org.firstinspires.ftc.teamcode.T1_2022.Opmodes.Tests.OpModes.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.T1_2022.Base;
import org.firstinspires.ftc.teamcode.T1_2022.Modules.Camera.Camera;

@Autonomous(name = "RGB_ColorThresh", group = "Tests")
public class RGB_ColorThresh extends Base {

    @Override
    public void runOpMode() throws InterruptedException {
        Camera camera = new Camera(hardwareMap);
        sleep(1000);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        String color = camera.getSignalColor();

        while (opModeIsActive()) {
            // Display Values
            telemetry.addData("Signal Color:", color);
            telemetry.update();
        }
    }
}