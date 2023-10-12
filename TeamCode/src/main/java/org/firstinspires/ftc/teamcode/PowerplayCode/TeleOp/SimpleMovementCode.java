package org.firstinspires.ftc.teamcode.PowerplayCode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.PowerplayCode.RobotComponents;
import org.firstinspires.ftc.teamcode.PowerplayCode.config;
@TeleOp
public class SimpleMovementCode extends LinearOpMode {


    DcMotor FRwheelMotor;
    DcMotor BRwheelMotor;
    DcMotor FLwheelMotor;
    DcMotor BLwheelMotor;

    boolean XWasPressed;
    boolean ClesteInchis;

    Servo Cleste;
    @Override
    public void runOpMode(){
        FRwheelMotor = hardwareMap.get(DcMotor.class, "fataDreapta");
        BRwheelMotor = hardwareMap.get(DcMotor.class, "spateDreapta");
        FLwheelMotor = hardwareMap.get(DcMotor.class, "fataStanga");
        BLwheelMotor = hardwareMap.get(DcMotor.class, "spateStanga");

        FRwheelMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BRwheelMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FLwheelMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BLwheelMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        Cleste=hardwareMap.get(Servo.class,config.CLESTE);

        FRwheelMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        BRwheelMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        FLwheelMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        BLwheelMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        waitForStart();
        double speed=0.5;
        while(opModeIsActive()){
            if (gamepad1.dpad_up) {
                FRwheelMotor.setPower(-speed);
                BRwheelMotor.setPower(-speed);
                FLwheelMotor.setPower(-speed);
                BLwheelMotor.setPower(-speed);
            }
            if (gamepad1.dpad_down) {
                FRwheelMotor.setPower(speed);
                BRwheelMotor.setPower(speed);
                FLwheelMotor.setPower(speed);
                BLwheelMotor.setPower(speed);
            }
            if (gamepad1.dpad_left) {
                FRwheelMotor.setPower(- speed);
                BRwheelMotor.setPower(speed);
                FLwheelMotor.setPower(speed);
                BLwheelMotor.setPower(-speed);
            }
            if (gamepad1.dpad_right) {
                FRwheelMotor.setPower(speed);
                BRwheelMotor.setPower(-speed);
                FLwheelMotor.setPower(-speed);
                BLwheelMotor.setPower(speed);
            }
        }
        double servoOpenPos=0.7;
        double servoClosePos=0;
        if(gamepad2.x&&!XWasPressed){
            if(ClesteInchis){
                ClesteInchis=false;
                RobotComponents.Cleste.setPosition(servoOpenPos);
            }
            else{
                RobotComponents.Cleste.setPosition(servoClosePos);
                ClesteInchis=true;
            }

            XWasPressed=true;
        }
        if(!gamepad2.x){
            XWasPressed=false;
        }

    }
}
