package org.firstinspires.ftc.teamcode.PowerplayCode.TestCode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.PowerplayCode.RobotComponents;
import org.firstinspires.ftc.teamcode.PowerplayCode.config;

@TeleOp
public class TestLift extends LinearOpMode  {
    Boolean xWasPressed=false;
    DcMotor TestMotor;
    Boolean yWasPressed=false;
    Boolean aWasPressed=false;
    Boolean bWasPressed=false;
    Boolean upWasPressed=false;
    int modifier=50;
    double power=0.6;
    @Override
    public void runOpMode() throws InterruptedException {
        Init();
        waitForStart();
        while(opModeIsActive()){
            if(gamepad1.x&&!xWasPressed) {
                modifier+=5;
                xWasPressed=true;
            }
            if(gamepad1.y&&!yWasPressed) {
                modifier-=5;
                yWasPressed=true;
            }
            if(gamepad1.a&&!aWasPressed) {
                power+=0.05;
                aWasPressed=true;
            }
            if(gamepad1.b&&!bWasPressed) {
                power-=0.05;
                bWasPressed=true;
            }


            telemetry.addData("CP:",TestMotor.getCurrentPosition());
            telemetry.update();
            if(!gamepad1.x)
                xWasPressed=false;
            if(!gamepad1.y)
                yWasPressed=false;
            if(!gamepad1.a)
                aWasPressed=false;
            if(!gamepad1.b)
                bWasPressed=false;
            if (!gamepad1.dpad_up)
                upWasPressed=false;
        }
    }
    public double clamp(double min,double max,double val){
        if(min>val)
            return min;
        if(max<val)
            return max;
        return val;
    }
    public void Init() {



        TestMotor = hardwareMap.get(DcMotor.class, config.MANA);
        TestMotor.setTargetPosition(0);
        TestMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        TestMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

}
