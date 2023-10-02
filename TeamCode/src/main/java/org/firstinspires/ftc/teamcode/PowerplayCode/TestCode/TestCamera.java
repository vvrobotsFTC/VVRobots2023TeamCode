package org.firstinspires.ftc.teamcode.PowerplayCode.TestCode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.PowerplayCode.Autonomy.ColorDetectCamera;
import org.firstinspires.ftc.teamcode.PowerplayCode.RobotComponents;
import org.opencv.core.Point;


@Disabled
public class TestCamera extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        RobotComponents.Camera = new ColorDetectCamera(new Point(0.32 , 0.1), new Point(0.5, 0.23),hardwareMap);
        waitForStart();
        while (opModeIsActive()){


        }
    }

}