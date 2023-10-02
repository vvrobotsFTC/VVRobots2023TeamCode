/**Copyright Dobrinel */
package org.firstinspires.ftc.teamcode.FrenzyFreightCode;

//Librarii

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
@Disabled

//@TeleOp(name="Pune pozitie 0", group="Linear Opmode")
public class placePoz0 extends LinearOpMode {
    private DcMotor Ypozition = null;
    private DcMotor Xpozition = null;
    private Servo RightServo;
    private Servo LeftServo;
    DigitalChannel  XButton;
    DigitalChannel  YButton;

    void initialise() {
        Ypozition = hardwareMap.get(DcMotor.class, "Ymotor");
        Xpozition = hardwareMap.get(DcMotor.class, "Xmotor");
        RightServo = hardwareMap.get(Servo.class, "RightServo");
        LeftServo = hardwareMap.get(Servo.class, "LeftServo");
        XButton = hardwareMap.get(DigitalChannel.class, "Xbutton");
        YButton = hardwareMap.get(DigitalChannel.class, "Ybutton");

        XButton.setMode(DigitalChannel.Mode.INPUT);
        YButton.setMode(DigitalChannel.Mode.INPUT);
        Ypozition.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Xpozition.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Ypozition.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Xpozition.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        Ypozition.setDirection(DcMotorSimple.Direction.FORWARD);
        Xpozition.setDirection(DcMotorSimple.Direction.FORWARD);
        RightServo.setDirection(Servo.Direction.FORWARD);
        LeftServo.setDirection(Servo.Direction.REVERSE);
    }

    @Override
    public void runOpMode() {
        initialise();
        waitForStart();

        RightServo.setPosition(0.25);
        LeftServo.setPosition(0.32);

        while(XButton.getState())
        {
            Xpozition.setPower(0.45);
            sleep(1);
        }
        Xpozition.setPower(0);


        while(YButton.getState())
        {
            Ypozition.setPower(0.15);
            sleep(1);
        }
        Ypozition.setPower(0);
        Ypozition.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        /*Ypozition.setTargetPosition(-1700);
        Ypozition.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Ypozition.setPower(0.75);
        while(Ypozition.isBusy()) sleep(1);*/
        RightServo.setPosition(0.48);
        LeftServo.setPosition(0.63);
        while (opModeIsActive())
            sleep(1000);

    }
}

