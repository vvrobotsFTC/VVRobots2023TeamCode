package org.firstinspires.ftc.teamcode.PowerplayCode.TestCode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DeviceManager;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.PowerplayCode.RobotComponents;
import org.firstinspires.ftc.teamcode.PowerplayCode.RobotParamaters;
import org.firstinspires.ftc.teamcode.PowerplayCode.config;

@Disabled
public class TestServo extends LinearOpMode {
    Boolean aWasPressed=false;
    Boolean bWasPressed=false;
    Servo servo;
    DcMotor motor_brat;
    double x=0;
    @Override
    public void runOpMode(){
        servo=hardwareMap.get(Servo.class, config.CLESTE);
        motor_brat=hardwareMap.get(DcMotor.class, config.MANA);
        motor_brat.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor_brat.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        waitForStart();
        while (opModeIsActive()){
            motor_brat.setPower(gamepad1.left_stick_y*0.2);
            if(gamepad1.a&&!aWasPressed) {
                x+=0.05;
                aWasPressed=true;
            }
            if(gamepad1.b&&!bWasPressed) {
                x-=0.05;
                bWasPressed=true;
            }
            if(!gamepad1.a)
                aWasPressed=false;
            if(!gamepad1.b)
                bWasPressed=false;
            servo.setPosition(x);
            telemetry.addData("x=",x);
            telemetry.addData("encoder brat=",motor_brat.getCurrentPosition());
            telemetry.update();
        }
    }
}
