/**Copyright Dobrinel */
package org.firstinspires.ftc.teamcode.OldDobCcode;

//Librarii
import android.annotation.SuppressLint;

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

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
@Disabled
//@TeleOp(name="DriverDob", group="Linear Opmode")
public class DriverDob extends LinearOpMode {

    /**DECLARARE VARIABILE/OBIECTE*/

    private DcMotor FRmotor=null;
    private DcMotor FLmotor=null;
    private DcMotor BRmotor=null;
    private DcMotor BLmotor=null;
    private DcMotor T1motor=null;
    private DcMotor T2motor=null;
    private Servo SVmotor1=null;
    private Servo SVmotor2=null;
    private Servo SVmotor3=null;
    private Servo SVmotor4=null;
    private int marsarier=1;

    private ColorRangeSensor lowSensor =null;
    private Rev2mDistanceSensor highSensor = null;

    //private DigitalChannel sA=null;
    //private DigitalChannel sB=null;
    boolean booly1=false;
    boolean booly2=false;
    boolean booly10=false;
    boolean booly20=false;
    boolean booly100=false;
    boolean booly200=false;
    boolean booly1000=false;
    boolean booly2000=false;
    boolean booly10000=false;
    boolean booly20000=false;
    Thread paletaThread = new Thread(new Runnable() {
        @Override
        public void run() {
            SVmotor4.setPosition(0.2);
            sleep(1500);
            SVmotor4.setPosition(1);
        }
    });
    @SuppressLint("SuspiciousIndentation")
    @Override
    public void runOpMode() {

        /**SETUP MOTOARE*/
        FRmotor=hardwareMap.get(DcMotor.class,"fataDreapta");
        FLmotor=hardwareMap.get(DcMotor.class,"fataStanga");
        BRmotor=hardwareMap.get(DcMotor.class,"spateDreapta");
        BLmotor=hardwareMap.get(DcMotor.class,"spateStanga");
        T1motor=hardwareMap.get(DcMotor.class,"motorTraction1");
        T2motor=hardwareMap.get(DcMotor.class,"motorTraction2");
        SVmotor1=hardwareMap.get(Servo.class,"servoBrat1");
        SVmotor2=hardwareMap.get(Servo.class,"servoBrat2");
        SVmotor3=hardwareMap.get(Servo.class,"servoBrat3");
        SVmotor4=hardwareMap.get(Servo.class, "servoLansator");
        lowSensor =  hardwareMap.get(ColorRangeSensor.class, "senzorCuloare");


        highSensor = hardwareMap.get(Rev2mDistanceSensor.class, "senzorDistanta");
        lowSensor.enableLed(true);
        //sA=hardwareMap.get(DigitalChannel.class,"semD");
        //sB=hardwareMap.get(DigitalChannel.class, "semS");


        FRmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FLmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BRmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BLmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        T1motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        T2motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        FRmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FLmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BRmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BLmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        T1motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        T2motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        FRmotor.setDirection(DcMotorSimple.Direction.FORWARD);
        BRmotor.setDirection(DcMotorSimple.Direction.FORWARD);

        FLmotor.setDirection(DcMotorSimple.Direction.REVERSE);
        BLmotor.setDirection(DcMotorSimple.Direction.REVERSE);
        T2motor.setDirection(DcMotorSimple.Direction.REVERSE);

        SVmotor1.setDirection(Servo.Direction.FORWARD);
        SVmotor1.setPosition(0);
        SVmotor2.setDirection(Servo.Direction.FORWARD);
        SVmotor2.setPosition(1);
        SVmotor3.setDirection(Servo.Direction.FORWARD);
        SVmotor3.setPosition(0.5);
        SVmotor4.setDirection(Servo.Direction.FORWARD);
        SVmotor4.setPosition(1);

        telemetry.addData("Status", "Initializat");
        telemetry.update();
        /**AICI INCEPE DISTRACTIA*/
        waitForStart();

        while (opModeIsActive()) {
            /**BIND MISCARE PRIMARA ROTI 2 CATE 2 PE LATERALE
             * G1 BUTON JOYSTICK AXA Y*/
            /**++MARSARIER*/
                if((gamepad1.a==true)&&(booly100==false)) {booly200=true;}
                if(booly200==true) {
                    marsarier=-1;
                    FRmotor.setPower(gamepad1.right_stick_y*marsarier);
                    BRmotor.setPower(gamepad1.right_stick_y*marsarier);
                    FLmotor.setPower(gamepad1.left_stick_y*marsarier);
                    BLmotor.setPower(gamepad1.left_stick_y*marsarier); }
                if((gamepad1.a==false)&&(booly200==true)) {booly100=true;}
                if((gamepad1.a==true)&&(booly100==true)) {booly200=false;}
                if(booly200==false) {
                    marsarier=1;
                    FRmotor.setPower(gamepad1.left_stick_y*marsarier);
                    BRmotor.setPower(gamepad1.left_stick_y*marsarier);
                    FLmotor.setPower(gamepad1.right_stick_y*marsarier);
                    BLmotor.setPower(gamepad1.right_stick_y*marsarier);}
                if((gamepad1.a==false)&&(booly200==false)) {booly100=false;}

            /**BIND SWTICH PENTRU BANDA RULANTA KAUFLAND 1
             * G2 BUTON Y/JOISTICK*/
                if(gamepad2.y) T1motor.setPower(1);
                else T1motor.setPower(gamepad2.left_stick_y);

            /**BIND SWTICH PENTRU LANSATOR PRODUSE KAUFLAND 2
             * G2 BUTON A*/
            if((gamepad2.a==true)&&(booly1000==false)) {booly2000=true;}
            if(booly2000==true) {T2motor.setPower(1);}
            if((gamepad2.a==false)&&(booly2000==true)) {booly1000=true;}

            if((gamepad2.a==true)&&(booly1000==true)) {booly2000=false;}
            if(booly2000==false) {T2motor.setPower(0);}
            if((gamepad2.a==false)&&(booly2000==false)) {booly1000=false;}


            /**BIND SWTICH PENTRU PALETA LANSATOR
             * G2 BUTON B*/
            /*if((gamepad2.b==true)&&(booly10000==false)) {booly20000=true;}
            if(booly20000==true) {SVmotor4.setPosition(1);}
            if((gamepad2.b==false)&&(booly20000==true)) {booly10000=true;}
            if((gamepad2.b==true)&&(booly10000==true)) {booly20000=false;}
            if(booly20000==false) {SVmotor4.setPosition(0);}
            if((gamepad2.b==false)&&(booly20000==false)) {booly10000=false;}*/
            if(gamepad2.b && !paletaThread.isAlive())
                paletaThread.start();

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

            /**BIND BRAT
             * G2 BUMPERS/DPAD LEFT-UP-RIGHT/JOYSTICK*/
                if(gamepad2.dpad_left) SVmotor1.setPosition(0);         /**ROTATIE CAP DE BRAT*/
                if(gamepad2.dpad_up) SVmotor1.setPosition(0.7);
                if(gamepad2.dpad_right) SVmotor1.setPosition(1);
                SVmotor3.setPosition(-gamepad2.right_stick_y/2+0.5);     /**RIDICARE/COBORARE BRAT*/

                if(gamepad2.left_bumper) SVmotor1.setPosition(SVmotor1.getPosition()-0.005);
                if(gamepad2.right_bumper) SVmotor1.setPosition(SVmotor1.getPosition()+0.005);

            if((gamepad2.x==true)&&(booly10==false)) {booly20=true;}
            if(booly20==true) {SVmotor2.setPosition(0.4);;}
            if((gamepad2.x==false)&&(booly20==true)) {booly10=true;}
            if((gamepad2.x==true)&&(booly10==true)) {booly20=false;}
            if(booly20==false) {SVmotor2.setPosition(1);;}
            if((gamepad2.x==false)&&(booly20==false)) {booly10=false;}

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

            lowSensor.enableLed(booly100);

            if(booly100==false) telemetry.addData("Gearbox","1st Gear");
            if(booly100==true) telemetry.addData("Gearbox","Reverse");
            if(booly2==false) telemetry.addData("Ramp","Manual");
            if(booly2==true) telemetry.addData("Ramp","Full Throttle");
            if(booly2000==false) telemetry.addData("Launcher","Off");
            if(booly2000==true) telemetry.addData("Launcher","Full Throttle");
            telemetry.addData("ClawServo:",SVmotor2.getPosition());
            telemetry.addData("lowSensor:", lowSensor.getDistance(DistanceUnit.CM));
            telemetry.addData("highSensor:", highSensor.getDistance(DistanceUnit.CM));

            telemetry.update();

        }   /**while (opModeIsActive())*/
    }   /**RunOpMode*/

}   /**LinearOpMode*/
