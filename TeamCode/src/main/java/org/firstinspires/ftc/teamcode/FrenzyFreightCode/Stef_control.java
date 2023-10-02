/**Copyright Dobrinel */
package org.firstinspires.ftc.teamcode.FrenzyFreightCode;

//Librarii
import android.hardware.Sensor;

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

//@TeleOp(name="Stef VVRobots FTC 2022 v 1.0", group="Linear Opmode")
public class Stef_control extends LinearOpMode {
    private DcMotor roata=null;
    private DcMotor maniera=null;
    private DcMotor mana=null;
    private DcMotor brat=null;
    private DcMotor FRmotor=null;
    private DcMotor FLmotor=null;
    private DcMotor BRmotor=null;
    private DcMotor BLmotor=null;
    private int direction=1;
    private boolean bratStrans=false;
    private boolean bool1=true;
    private boolean bool2=true;

    Thread open = new Thread(() -> {
        mana.setPower(-0.4);
        sleep(300);
        mana.setPower(0);
    });

    Thread close = new Thread(() -> {
        mana.setPower(0.6);
        sleep(500);
        mana.setPower(0.3);
    });

    @Override public void runOpMode()    {

        double smoothMovement = 0.65;//viteza pentru smoot movment
        double speed=1;
        roata=hardwareMap.get(DcMotor.class,"roata" );
        maniera=hardwareMap.get(DcMotor.class,"maniera" );
        mana=hardwareMap.get(DcMotor.class,"mana");
        brat=hardwareMap.get(DcMotor.class,"brat");
        FRmotor=hardwareMap.get(DcMotor.class,"fataDreapta");
        FLmotor=hardwareMap.get(DcMotor.class,"fataStanga");
        BRmotor=hardwareMap.get(DcMotor.class,"spateDreapta");
        BLmotor=hardwareMap.get(DcMotor.class,"spateStanga");



        roata.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        maniera.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        brat.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mana.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FRmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FLmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BRmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BLmotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        roata.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        maniera.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        brat.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        brat.setDirection(DcMotorSimple.Direction.FORWARD);
        roata.setDirection(DcMotorSimple.Direction.FORWARD);
        maniera.setDirection(DcMotorSimple.Direction.FORWARD);
        FRmotor.setDirection(DcMotorSimple.Direction.REVERSE);
        BRmotor.setDirection(DcMotorSimple.Direction.REVERSE);
        FLmotor.setDirection(DcMotorSimple.Direction.FORWARD);
        BLmotor.setDirection(DcMotorSimple.Direction.FORWARD);
        mana.setDirection(DcMotorSimple.Direction.FORWARD);

        mana.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        brat.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();
        close.start();

        while (opModeIsActive()) {

            if(gamepad2.x&&!open.isAlive()&&!close.isAlive()){
                if (bratStrans) open.start(); else close.start();
                bratStrans=!bratStrans;
            }
            maniera.setPower(maniera.getCurrentPosition() < -1400 && gamepad2.left_stick_y < 0  ? -0.15 : gamepad2.left_stick_y == 0 ? -0.15 : gamepad2.left_stick_y < 0 ? -0.65 : 0.5);

            ///brat.setPower(gamepad2.right_stick_y*0.8+(gamepad2.right_stick_y > 0 ? -0.295 : 0.05));
            brat.setPower(gamepad2.right_stick_y*0.8+(gamepad2.right_stick_y > 0 ? -0.295 : gamepad2.right_stick_y==0?-0.15:0 ));

            if(gamepad1.left_bumper==true&&bool2==true){
                if(speed==1) speed=0.7;
                else  speed=1;
                bool2=false;
            }
            if(gamepad1.left_bumper==false&&bool2==false)  bool2=true;

            if(gamepad1.x==true&&bool1==true) {
                if(direction==1) direction=-1;
                else       direction=1;
                bool1=false;
            }
            if(gamepad1.x==false&&bool1==false)  bool1=true;
            /**BIND MISCARE PRIMARA ROTI 2 CATE 2 PE LATERALE
             * G1 BUTON JOYSTICK AXA Y*/
            /**++MARSARIER*/
            FRmotor.setPower(gamepad1.right_stick_y*direction*speed);
            BRmotor.setPower(gamepad1.right_stick_y*direction*speed);
            FLmotor.setPower(gamepad1.left_stick_y*direction*speed);
            BLmotor.setPower(gamepad1.left_stick_y*direction*speed);
            /**BIND PT SMOOT MOVEMENT FATA-SPATE-LATERALE
             * G1 BUTON D-PAD*/
            if (gamepad1.dpad_up){
                FRmotor.setPower(-smoothMovement*direction*speed);
                BRmotor.setPower(-smoothMovement*direction*speed);
                FLmotor.setPower(-smoothMovement*direction*speed);
                BLmotor.setPower(-smoothMovement*direction*speed);}
            if (gamepad1.dpad_down){
                FRmotor.setPower(smoothMovement*direction*speed);
                BRmotor.setPower(smoothMovement*direction*speed);
                FLmotor.setPower(smoothMovement*direction*speed);
                BLmotor.setPower(smoothMovement*direction*speed);}
            if (gamepad1.dpad_left){
                FRmotor.setPower(-smoothMovement*direction*speed);
                BRmotor.setPower(smoothMovement*direction*speed);
                FLmotor.setPower(smoothMovement*direction*speed);
                BLmotor.setPower(-smoothMovement*direction*speed);}
            if (gamepad1.dpad_right){
                FRmotor.setPower(smoothMovement*direction*speed);
                BRmotor.setPower(-smoothMovement*direction*speed);
                FLmotor.setPower(-smoothMovement*direction*speed);
                BLmotor.setPower(smoothMovement*direction*speed);}

            if(gamepad2.y)
                roata.setPower(0.4);
            else
                roata.setPower(0);
        }
    }
}