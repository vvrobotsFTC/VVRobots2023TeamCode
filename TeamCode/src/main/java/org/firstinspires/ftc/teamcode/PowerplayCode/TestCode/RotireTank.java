package org.firstinspires.ftc.teamcode.PowerplayCode.TestCode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.PowerplayCode.RobotComponents;
import org.firstinspires.ftc.teamcode.PowerplayCode.config;
@TeleOp(name="RotireTank")
public class RotireTank extends LinearOpMode  {

    Boolean xWasPressed=false;
    Boolean yWasPressed=false;
    Boolean aWasPressed=false;
    Boolean bWasPressed=false;
    int modifier=0;
    double power=0;
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
            telemetry.addData("Modifier:",modifier);
            telemetry.addData("Power:",power);
            telemetry.addData("g1RS:",gamepad1.right_stick_y);
            telemetry.addData("encoder",RobotComponents.RotireTank.getCurrentPosition());
            telemetry.update();

            if(!gamepad1.x)
                xWasPressed=false;
            if(!gamepad1.y)
                yWasPressed=false;
            if(!gamepad1.a)
                aWasPressed=false;
            if(!gamepad1.b)
                bWasPressed=false;
            RobotComponents.RotireTank.setPower(power);
            RobotComponents.RotireTank.setTargetPosition((int)(RobotComponents.RotireTank.getCurrentPosition()+gamepad1.right_stick_y*modifier));
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

        RobotComponents.RotireTank = hardwareMap.get(DcMotor.class, config.ROTIRE_TANK);
        RobotComponents.RotireTank.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RobotComponents.RotireTank.setTargetPosition(0);
        RobotComponents.RotireTank.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }

}
