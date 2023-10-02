package org.firstinspires.ftc.teamcode.PowerplayCode.TestCode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


@TeleOp
public class movement extends LinearOpMode {

    DcMotor MotorFR;
    DcMotor MotorFL;
    DcMotor MotorBR;
    DcMotor MotorBL;
    Boolean aWasPressed=false;
    Boolean bWasPressed=false;
    double degree;
    @Override
    public void runOpMode() {

        MotorFR=hardwareMap.get(DcMotor.class,"fataDreapta");
        MotorBR=hardwareMap.get(DcMotor.class,"spateDreapta");
        MotorBL=hardwareMap.get(DcMotor.class,"spateStanga");
        MotorFL=hardwareMap.get(DcMotor.class,"fataStanga");

        MotorBL.setDirection(DcMotorSimple.Direction.REVERSE);
        MotorFL.setDirection(DcMotorSimple.Direction.REVERSE);
        MotorBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        MotorFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        MotorBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        MotorFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        MotorBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        MotorFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        MotorBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        MotorFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        MotorBL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        MotorFL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        MotorBR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        MotorFR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


            waitForStart();
            while(opModeIsActive()){
                MotorFL.setPower(gamepad1.dpad_up ? 1 : 0);
                MotorFR.setPower(gamepad1.dpad_right ? 1 : 0);
                MotorBR.setPower(gamepad1.dpad_down ? 1 : 0);
                MotorBL.setPower(gamepad1.dpad_left ? 1 : 0);
                telemetry.addData("FL", MotorFL.getCurrentPosition());
                telemetry.addData("FR", MotorFR.getCurrentPosition());
                telemetry.addData("BR", MotorBR.getCurrentPosition());
                telemetry.addData("BL", MotorBL.getCurrentPosition());
                telemetry.update();
            }
    }
}
