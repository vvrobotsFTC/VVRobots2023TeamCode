/**Copyright Dobrinel */
package org.firstinspires.ftc.teamcode.FrenzyFreightCode;

//Librarii

import android.view.TouchDelegate;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
@Disabled

//@TeleOp(name="macara test", group="Linear Opmode")
public class test_macara extends LinearOpMode {
    private DcMotor roata=null;
    private DcMotor Ypozition=null;
    private DcMotor Xpozition=null;
    private DcMotor Brat=null;
    private DcMotor FRmotor=null;
    private DcMotor FLmotor=null;
    private DcMotor BRmotor=null;
    private DcMotor BLmotor=null;
    private Servo RightServo=null;
    private Servo LeftServo=null;
    private DigitalChannel Xbutton=null;
    private DigitalChannel Ybutton=null;
    private Rev2mDistanceSensor SensorRatusca = null;
    private int CurrntSelect=0;
    private double p1=0;
    private double x=0;
    private boolean bool1=true;
    private boolean bool2=true;
    public void ResetPosition(){
        roata.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FRmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FLmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BRmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BLmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Ypozition.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Xpozition.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Brat.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FRmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FLmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BRmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BLmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        roata.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Ypozition.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Xpozition.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Brat.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        roata.setDirection(DcMotorSimple.Direction.FORWARD);
        FRmotor.setDirection(DcMotorSimple.Direction.REVERSE);
        BRmotor.setDirection(DcMotorSimple.Direction.REVERSE);
        FLmotor.setDirection(DcMotorSimple.Direction.FORWARD);
        BLmotor.setDirection(DcMotorSimple.Direction.FORWARD);
        Ypozition.setDirection(DcMotorSimple.Direction.FORWARD);
        Xpozition.setDirection(DcMotorSimple.Direction.FORWARD);
        Brat.setDirection(DcMotorSimple.Direction.FORWARD);
        RightServo.setDirection(Servo.Direction.FORWARD);
        LeftServo.setDirection(Servo.Direction.REVERSE);
    }
    @Override public void runOpMode()    {

        roata=hardwareMap.get(DcMotor.class,"Roata" );
        FRmotor=hardwareMap.get(DcMotor.class,"fataDreapta");
        FLmotor=hardwareMap.get(DcMotor.class,"fataStanga");
        BRmotor=hardwareMap.get(DcMotor.class,"spateDreapta");
        BLmotor=hardwareMap.get(DcMotor.class,"spateStanga");
        Ypozition=hardwareMap.get(DcMotor.class,"Ymotor");
        Xpozition=hardwareMap.get(DcMotor.class,"Xmotor");
        Brat=hardwareMap.get(DcMotor.class,"Brat");
        RightServo=hardwareMap.get(Servo.class,"RightServo");
        LeftServo=hardwareMap.get(Servo.class,"LeftServo");
        Xbutton=hardwareMap.get(DigitalChannel.class,"Xbutton");
        Ybutton=hardwareMap.get(DigitalChannel.class,"Ybutton");
        SensorRatusca = hardwareMap.get(Rev2mDistanceSensor.class, "senzorDistantaRatusca");

        roata.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FRmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FLmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BRmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BLmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Ypozition.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Xpozition.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Xbutton.setMode(DigitalChannel.Mode.INPUT);
        Ybutton.setMode(DigitalChannel.Mode.INPUT);
        Brat.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FRmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FLmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BRmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BLmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        roata.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Ypozition.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Xpozition.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Brat.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        roata.setDirection(DcMotorSimple.Direction.FORWARD);
        FRmotor.setDirection(DcMotorSimple.Direction.REVERSE);
        BRmotor.setDirection(DcMotorSimple.Direction.REVERSE);
        FLmotor.setDirection(DcMotorSimple.Direction.FORWARD);
        BLmotor.setDirection(DcMotorSimple.Direction.FORWARD);
        Ypozition.setDirection(DcMotorSimple.Direction.FORWARD);
        Xpozition.setDirection(DcMotorSimple.Direction.FORWARD);
        Brat.setDirection(DcMotorSimple.Direction.FORWARD);
        RightServo.setDirection(Servo.Direction.FORWARD);
        LeftServo.setDirection(Servo.Direction.REVERSE);
        waitForStart();
        Ypozition.setTargetPosition(Ypozition.getCurrentPosition());
        while (opModeIsActive()) {
            telemetry.addData("Roata",roata.getCurrentPosition());
            telemetry.addData("Brat",Brat.getCurrentPosition());
            telemetry.addData("Xpoz",Xpozition.getCurrentPosition());
            telemetry.addData("Ypoz",Ypozition.getCurrentPosition());
          //  telemetry.addData("FR",FRmotor.getCurrentPosition());
         //   telemetry.addData("BR",BRmotor.getCurrentPosition());
        //    telemetry.addData("FL",FLmotor.getCurrentPosition());
       //     telemetry.addData("BL",BLmotor.getCurrentPosition());
            telemetry.addData("Roata Speed",x);
            telemetry.addData("Sensor",SensorRatusca.getDistance(DistanceUnit.CM));

            telemetry.update();


            if(gamepad1.x&&Xbutton.getState())
                Xpozition.setPower(0.4);
            else if(gamepad1.y)
                Xpozition.setPower(-0.4);
            else Xpozition.setPower(0);
            if(gamepad1.a&&Ybutton.getState()){
                Ypozition.setPower(0.7);
                Ypozition.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                Ypozition.setTargetPosition(Ypozition.getCurrentPosition());

            }
            else if(gamepad1.b){
                Ypozition.setPower(-1);
                Ypozition.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                Ypozition.setTargetPosition(Ypozition.getCurrentPosition());
            }
            else {
                Ypozition.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                Ypozition.setPower(1);
            }
            if(gamepad1.start)
                ResetPosition();
            if(gamepad2.a&&bool1){x=x+0.01;bool1=false;}
            if(!gamepad2.a)bool1=true;
            if(gamepad2.b&&bool2){x=x-0.01;bool2=false;}
            if(!gamepad2.b)bool2=true;
            if(gamepad2.x) roata.setPower(x); else roata.setPower(0);
        }
    }
}