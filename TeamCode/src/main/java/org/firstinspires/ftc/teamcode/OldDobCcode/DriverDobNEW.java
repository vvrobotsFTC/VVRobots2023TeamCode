        /**Copyright Dobrinel */
package org.firstinspires.ftc.teamcode.OldDobCcode;

//Librarii
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

        @Disabled
//@TeleOp(name="DriverDobNEW", group="Linear Opmode")
public class DriverDobNEW extends LinearOpMode {
    /**DECLARARE VARIABILE/OBIECTE*/
    private DcMotor FRmotor=null;
    private DcMotor FLmotor=null;
    private DcMotor BRmotor=null;
    private DcMotor BLmotor=null;
    private DcMotor legalM=null;
    private DcMotor rampa1=null;
    private DcMotor lansator1=null;
    private Servo brat1=null;
    private int marsarier=-1;


    boolean booly100=false;
    boolean booly200=false;

    boolean booly3=false;
    boolean booly4=false;
    boolean booly30=false;
    boolean booly40=false;
    boolean booly300=false;
    boolean booly400=false;

    @Override
    public void runOpMode() {

        /**SETUP MOTOARE*/
        FRmotor=hardwareMap.get(DcMotor.class,"fataDreapta");
        FLmotor=hardwareMap.get(DcMotor.class,"fataStanga");
        BRmotor=hardwareMap.get(DcMotor.class,"spateDreapta");
        BLmotor=hardwareMap.get(DcMotor.class,"spateStanga");
        legalM=hardwareMap.get(DcMotor.class,"legalM");
        rampa1=hardwareMap.get(DcMotor.class,"rampa1");
        lansator1=hardwareMap.get(DcMotor.class,"lansator1");
        brat1=hardwareMap.get(Servo.class, "brat1");

        FRmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FLmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BRmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BLmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        legalM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rampa1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lansator1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        FRmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FLmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BRmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BLmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        legalM.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rampa1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lansator1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        FRmotor.setDirection(DcMotorSimple.Direction.FORWARD);
        BRmotor.setDirection(DcMotorSimple.Direction.FORWARD);

        FLmotor.setDirection(DcMotorSimple.Direction.REVERSE);
        BLmotor.setDirection(DcMotorSimple.Direction.REVERSE);

        legalM.setDirection(DcMotorSimple.Direction.REVERSE);
        rampa1.setDirection(DcMotorSimple.Direction.REVERSE);
        lansator1.setDirection(DcMotorSimple.Direction.REVERSE);

        brat1.setDirection(Servo.Direction.FORWARD);
        brat1.setPosition(0.5);

        telemetry.addData("Status", "Initializat");
        telemetry.update();
        /**AICI INCEPE DISTRACTIA*/
        waitForStart();

        while (opModeIsActive()) {

            brat1.setPosition(-gamepad2.right_stick_y/2+0.5);
            
            /**TEST MOTOR LEGALIZAT
             * BUTON X*/
            if((gamepad2.x==true)&&(booly3==false)) {booly4=true;}
            if(booly4==true) {
                legalM.setPower(1);                    /** motor mic putere -1.00 - 1.00*/
                 }
            if((gamepad2.x==false)&&(booly4==true)) {booly3=true;}
            if((gamepad2.x==true)&&(booly3==true)) {booly4=false;}
            if(booly4==false) {
                legalM.setPower(0);
               }
            if((gamepad2.x==false)&&(booly4==false)) {booly3=false;}


            /**RAMPA
             * BUTON Y*/
            if((gamepad2.y==true)&&(booly30==false)) {booly40=true;}
            if(booly40==true) {
                rampa1.setPower(1);                     /** motor rampa -1.00 - 1.00*/
            }
            if((gamepad2.y==false)&&(booly40==true)) {booly30=true;}
            if((gamepad2.y==true)&&(booly30==true)) {booly40=false;}
            if(booly40==false) {
                rampa1.setPower(gamepad2.left_stick_y);
            }
            if((gamepad2.y==false)&&(booly40==false)) {booly30=false;}

            /**LANSATOR
             * BUTON B*/
            if((gamepad2.b==true)&&(booly300==false)) {booly400=true;}
            if(booly400==true) {
                lansator1.setPower(1);                     /** motor lansator -1.00 - 1.00*/
            }
            if((gamepad2.b==false)&&(booly400==true)) {booly300=true;}
            if((gamepad2.b==true)&&(booly300==true)) {booly400=false;}
            if(booly400==false) {
                lansator1.setPower(0);
            }
            if((gamepad2.b==false)&&(booly400==false)) {booly300=false;}



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

            /**test*/
            /*if(gamepad1.b){
                FRmotor.setPower(1*marsarier);
                BRmotor.setPower(-1*marsarier);
                FLmotor.setPower(1*marsarier);
                BLmotor.setPower(-1*marsarier);}*/

             /**test*/

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
            if(booly3==false) telemetry.addData("Poland motor","OFF");
            if(booly3==true) telemetry.addData("Poland motor","ON");


            telemetry.addData("A -","forward/reverse gearbox");
            telemetry.addData("B -","launcher motor");
            telemetry.addData("Y -","ramp motor");
            telemetry.addData("X -","Polish motor");

            telemetry.update();

        }   /**while (opModeIsActive())*/
    }   /**RunOpMode*/

}   /**LinearOpMode*/
