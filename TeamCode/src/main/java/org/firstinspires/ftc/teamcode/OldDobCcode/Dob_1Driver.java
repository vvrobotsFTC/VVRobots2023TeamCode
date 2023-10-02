/**Copyright Dobrinel */
package org.firstinspires.ftc.teamcode.OldDobCcode;

//Librarii
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.configuration.annotations.DigitalIoDeviceType;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
@Disabled
public class Dob_1Driver extends LinearOpMode {

    /**DECLARARE VARIABILE/OBIECTE*/

    private DcMotor FRmotor=null;
    private DcMotor FLmotor=null;
    private DcMotor BRmotor=null;
    private DcMotor BLmotor=null;

    private DcMotor rampa1=null;
    private DcMotor lansator1=null;
    private Servo servo0=null;

    private ColorRangeSensor lowSensor =null;
    private Rev2mDistanceSensor highSensor = null;

    private int marsarier=-1;


    boolean booly100=false;
    boolean booly200=false;
    boolean booly3=false;
    boolean booly4=false;
    boolean booly30=false;
    boolean booly40=false;
    boolean booly300=false;
    boolean booly400=false;

    Thread paletaThread = new Thread(new Runnable() {
        @Override
        public void run() {
            servo0.setPosition(0.4);
            sleep(500);
            servo0.setPosition(0);
            sleep(500);
        }
    });

    @Override
    public void runOpMode() {

        /**SETUP MOTOARE*/
        FRmotor=hardwareMap.get(DcMotor.class,"fataDreapta");
        FLmotor=hardwareMap.get(DcMotor.class,"fataStanga");
        BRmotor=hardwareMap.get(DcMotor.class,"spateDreapta");
        BLmotor=hardwareMap.get(DcMotor.class,"spateStanga");

        rampa1=hardwareMap.get(DcMotor.class,"rampa1");
        lansator1=hardwareMap.get(DcMotor.class,"lansator1");
        servo0=hardwareMap.get(Servo.class, "servo0");

        lowSensor =  hardwareMap.get(ColorRangeSensor.class, "senzorCuloare");
        highSensor = hardwareMap.get(Rev2mDistanceSensor.class, "senzorDistanta");

        FRmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FLmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BRmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BLmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        rampa1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lansator1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        FRmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FLmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BRmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BLmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        rampa1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lansator1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        FRmotor.setDirection(DcMotorSimple.Direction.FORWARD);
        BRmotor.setDirection(DcMotorSimple.Direction.FORWARD);

        FLmotor.setDirection(DcMotorSimple.Direction.REVERSE);
        BLmotor.setDirection(DcMotorSimple.Direction.REVERSE);

        rampa1.setDirection(DcMotorSimple.Direction.REVERSE);
        lansator1.setDirection(DcMotorSimple.Direction.FORWARD);

        servo0.setDirection(Servo.Direction.FORWARD);
        servo0.setPosition(0);

        telemetry.addData("Status", "Initializat");
        telemetry.update();
        /**AICI INCEPE DISTRACTIA*/
        waitForStart();

        while (opModeIsActive()) {

            /**SERVO 0
             * BUTON X*/
            /*if((gamepad1.x==true)&&(booly3==false)) {booly4=true;}
            if(booly4==true) {
                servo0.setPosition(0.4);
            }
            if((gamepad1.x==false)&&(booly4==true)) {booly3=true;}
            if((gamepad1.x==true)&&(booly3==true)) {booly4=false;}
            if(booly4==false) {
                servo0.setPosition(0);
            }
            if((gamepad1.x==false)&&(booly4==false)) {booly3=false;}*/

            if(gamepad1.x && !paletaThread.isAlive())
                paletaThread.start();

            /**RAMPA
             * BUTON Y*/
            if((gamepad1.y==true)&&(booly30==false)) {booly40=true;}
            if(booly40==true) {
                rampa1.setPower(1);
            }
            if((gamepad1.y==false)&&(booly40==true)) {booly30=true;}
            if((gamepad1.y==true)&&(booly30==true)) {booly40=false;}
            if(booly40==false) {
                rampa1.setPower(0);
            }
            if((gamepad1.y==false)&&(booly40==false)) {booly30=false;}

            /**LANSATOR
             * BUTON B*/
            if((gamepad1.b==true)&&(booly300==false)) {booly400=true;}
            if(booly400==true) {
                lansator1.setPower(1);
            }
            if((gamepad1.b==false)&&(booly400==true)) {booly300=true;}
            if((gamepad1.b==true)&&(booly300==true)) {booly400=false;}
            if(booly400==false) {
                lansator1.setPower(0);
            }
            if((gamepad1.b==false)&&(booly400==false)) {booly300=false;}



            /**BIND MISCARE PRIMARA ROTI 2 CATE 2 PE LATERALE
             * G1 BUTON JOYSTICK AXA Y*/
            /**++MARSARIER*/
            if((gamepad1.a==true)&&(booly100==false)) {booly200=true;}
            if(booly200==true) {
                marsarier=1;

                FRmotor.setPower(gamepad1.left_stick_y*marsarier);
                BRmotor.setPower(gamepad1.left_stick_y*marsarier);
                FLmotor.setPower(gamepad1.right_stick_y*marsarier);
                BLmotor.setPower(gamepad1.right_stick_y*marsarier); }
            if((gamepad1.a==false)&&(booly200==true)) {booly100=true;}
            if((gamepad1.a==true)&&(booly100==true)) {booly200=false;}
            if(booly200==false) {
                marsarier=-1;
                FRmotor.setPower(gamepad1.right_stick_y*marsarier);
                BRmotor.setPower(gamepad1.right_stick_y*marsarier);
                FLmotor.setPower(gamepad1.left_stick_y*marsarier);
                BLmotor.setPower(gamepad1.left_stick_y*marsarier); }
            if((gamepad1.a==false)&&(booly200==false)) {booly100=false;}


            /**BIND PT MISCAREA LATERALA
             * G1 BUTON BUMPERS*/
            if (gamepad1.left_bumper) {                 /**MISCARE LATERALA STANGA*/
                FRmotor.setPower(-1*marsarier);                   /**BIND LEFT BUMPER*/
                BRmotor.setPower(1*marsarier);
                FLmotor.setPower(1*marsarier);
                BLmotor.setPower(-1*marsarier);}
            if (gamepad1.right_bumper) {                /**MISCARE LATERALA DREAPTA*/
                FRmotor.setPower(1*marsarier);                    /**BIND RIGHT BUMPER*/
                BRmotor.setPower(-1*marsarier);
                FLmotor.setPower(-1*marsarier);
                BLmotor.setPower(1*marsarier);}
            FRmotor.setPower(0);                    /**RESETARE VALORI*/
            BRmotor.setPower(0);
            FLmotor.setPower(0);
            BLmotor.setPower(0);

            /**BIND PT MISCARE FINA FATA-SPATE-LATERALE
             * G1 BUTON D-PAD*/
            if (gamepad1.dpad_up){
                FRmotor.setPower(-0.45*marsarier);
                BRmotor.setPower(-0.45*marsarier);
                FLmotor.setPower(-0.45*marsarier);
                BLmotor.setPower(-0.45*marsarier);}
            if (gamepad1.dpad_down){
                FRmotor.setPower(0.45*marsarier);
                BRmotor.setPower(0.45*marsarier);
                FLmotor.setPower(0.45*marsarier);
                BLmotor.setPower(0.45*marsarier);}
            if (gamepad1.dpad_left){
                FRmotor.setPower(-0.45*marsarier);
                BRmotor.setPower(0.45*marsarier);
                FLmotor.setPower(0.45*marsarier);
                BLmotor.setPower(-0.45*marsarier);}
            if (gamepad1.dpad_right){
                FRmotor.setPower(0.45*marsarier);
                BRmotor.setPower(-0.45*marsarier);
                FLmotor.setPower(-0.45*marsarier);
                BLmotor.setPower(0.45*marsarier);}


            FRmotor.setPower(0);
            BRmotor.setPower(0);
            FLmotor.setPower(0);
            BLmotor.setPower(0);


            if(booly100==false) telemetry.addData("Gearbox","1st Gear");
            if(booly100==true) telemetry.addData("Gearbox","Reverse");
            if(booly300==false) telemetry.addData("Launcher motor","OFF");
            if(booly300==true) telemetry.addData("Launcher motor","ON");
            if(booly30==false) telemetry.addData("Ramp motor","MANUAL");
            if(booly30==true) telemetry.addData("Ramp motor","ON");
            if(booly3==false) telemetry.addData("Servo motor","READY");
            if(booly3==true) telemetry.addData("Servo motor","LAUNCHED");
            telemetry.addData("Gamepad_1 A  -","forward/reverse gearbox");
            telemetry.addData("Gamepad_1 B -","launcher motor");
            telemetry.addData("Gamepad_1 Y -","ramp motor");
            telemetry.addData("Gamepad_1 X -","servo motor");
            telemetry.addData("lowSensor:", lowSensor.getDistance(DistanceUnit.CM));
            telemetry.addData("highSensor:", highSensor.getDistance(DistanceUnit.CM));

            telemetry.update();

        }   /**while (opModeIsActive())*/
    }   /**RunOpMode*/

}   /**LinearOpMode*/
