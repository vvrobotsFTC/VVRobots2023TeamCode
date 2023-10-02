/**Copyright Dobrinel */
package org.firstinspires.ftc.teamcode.FrenzyFreightCode;

//Librarii
import android.hardware.Sensor;
import android.webkit.WebStorage;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.internal.camera.delegating.DelegatingCaptureSequence;
@Disabled

//mn  g@TeleOp(name="Stef VVRobots FTC 2022 v 2.0", group="Linear Opmode")
public class Ste_control extends LinearOpMode {
    private DcMotor roata = null;
    private DcMotor Brat = null;

    private DcMotor Ypozition = null;
    private DcMotor Xpozition = null;
    private DcMotor FRmotor = null;
    private DcMotor FLmotor = null;
    private DcMotor BRmotor = null;
    private DcMotor BLmotor = null;
    private Servo RightServo=null;
    private Servo LeftServo;
    DigitalChannel  Xbutton;
    DigitalChannel  Ybutton;
    private boolean bratStrans = false;
    private boolean bool1 = true;
    private boolean bool2 = true;
    private int direction = 1;
    void initialise() {
        roata = hardwareMap.get(DcMotor.class, "Roata");
        FRmotor = hardwareMap.get(DcMotor.class, "fataDreapta");
        FLmotor = hardwareMap.get(DcMotor.class, "fataStanga");
        BRmotor = hardwareMap.get(DcMotor.class, "spateDreapta");
        BLmotor = hardwareMap.get(DcMotor.class, "spateStanga");
        Ypozition = hardwareMap.get(DcMotor.class, "Ymotor");
        Xpozition = hardwareMap.get(DcMotor.class, "Xmotor");
        Brat = hardwareMap.get(DcMotor.class, "Brat");

        RightServo = hardwareMap.get(Servo.class, "RightServo");
        LeftServo = hardwareMap.get(Servo.class, "LeftServo");

        Xbutton=hardwareMap.get(DigitalChannel.class,"Xbutton");//done
        Ybutton=hardwareMap.get(DigitalChannel.class,"Ybutton");//done


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
        Brat.setDirection(DcMotorSimple.Direction.REVERSE);
        RightServo.setDirection(Servo.Direction.FORWARD);
        LeftServo.setDirection(Servo.Direction.REVERSE);
        Xbutton.setMode(DigitalChannel.Mode.INPUT);
        Ybutton.setMode(DigitalChannel.Mode.INPUT);
    }

    void open() {

        bratStrans = false;
        while (close.isAlive()) ;
        RightServo.setPosition(0.25);
        LeftServo.setPosition(0.32);
    }

    Thread close = new Thread(() -> {
        bratStrans = true;
        while (opModeIsActive() && bratStrans) {
            RightServo.setPosition(0.15);
            LeftServo.setPosition(0.2);
        }
    });
    void maxopen(){
        bratStrans=false;
        while(close.isAlive());
        RightServo.setPosition(0.48);
        LeftServo.setPosition(0.63);
    }

    void close() {
        close.start();
    }

    @Override
    public void runOpMode() {

        double smoothMovement = 0.65;//viteza pentru smoot movment
        double speed = 1;
        initialise();
        waitForStart();
        Brat.setTargetPosition(Brat.getCurrentPosition());
        //  Xpozition.setTargetPosition(Xpozition.getCurrentPosition());
        //Ypozition.setTargetPosition(Ypozition.getCurrentPosition());
        while (opModeIsActive()) {
            if ((gamepad2.x && bool1) || (gamepad1.x&&bool1)) {
                bool1 = false;
                if (bratStrans)
                    open();
                else
                    close();
            }
            if(gamepad2.b)     maxopen();
            if (gamepad2.x == false && gamepad1.x == false && bool1 == false) bool1 = true;

            if (gamepad1.y && bool2) {
                if (speed == 1) speed = 0.7;
                else speed = 1;
                bool2 = false;
            }
            /**ridicare/coborare axa Y*/

          /**  if (gamepad2.left_stick_y == 0) {
                Ypozition.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                Ypozition.setPower(1);
            } else {
                Ypozition.setTargetPosition(Ypozition.getCurrentPosition());
                if (gamepad2.left_stick_y < 0) {
                    Ypozition.setPower(gamepad2.left_stick_y);
                    Ypozition.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                } else {
                    if (Ybutton.getState()) {
                        Ypozition.setPower(gamepad2.left_stick_y);
                        Ypozition.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                    }
                    else {
                        Ypozition.setPower(1);
                        Ypozition.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    }
                }
            }**/
            /**miscare brat*/
            float ans=0;
            if(gamepad1.a)
                ans-=1f;
            if(gamepad1.y)
                ans+=1f;
            if (ans == 0) {
                Brat.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                Brat.setPower(1);
            } else
            {
                Brat.setTargetPosition(Brat.getCurrentPosition());
                Brat.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                if(ans>0)
                    Brat.setPower(ans*0.6);
                else
                    Brat.setPower(ans);
            }
            /**Miscare axa X*/
            /*if (gamepad2.right_trigger == 0 && gamepad2.left_trigger == 0) {
                Xpozition.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                Xpozition.setPower(1);
            } else {
                Xpozition.setTargetPosition(Xpozition.getCurrentPosition());
                float power = -gamepad2.right_trigger + gamepad2.left_trigger;
                Xpozition.setPower(power);
                Xpozition.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            }
            if (gamepad1.left_bumper == false && bool2 == false) bool2 = true;

            */
            /**BIND MISCARE PRIMARA ROTI 2 CATE 2 PE LATERALE
             * G1 BUTON JOYSTICK AXA Y*/
            /**++MARSARIER*/
            FRmotor.setPower(gamepad1.right_stick_y * direction * speed);
            BRmotor.setPower(gamepad1.right_stick_y * direction * speed);
            FLmotor.setPower(gamepad1.left_stick_y * direction * speed);
            BLmotor.setPower(gamepad1.left_stick_y * direction * speed);
            /**BIND PT SMOOT MOVEMENT FATA-SPATE-LATERALE
             * G1 BUTON D-PAD*/
            if (gamepad1.dpad_up) {
                FRmotor.setPower(-smoothMovement * direction * speed);
                BRmotor.setPower(-smoothMovement * direction * speed);
                FLmotor.setPower(-smoothMovement * direction * speed);
                BLmotor.setPower(-smoothMovement * direction * speed);
            }
            if (gamepad1.dpad_down) {
                FRmotor.setPower(smoothMovement * direction * speed);
                BRmotor.setPower(smoothMovement * direction * speed);
                FLmotor.setPower(smoothMovement * direction * speed);
                BLmotor.setPower(smoothMovement * direction * speed);
            }
            if (gamepad1.dpad_left) {
                FRmotor.setPower(-smoothMovement * direction * speed);
                BRmotor.setPower(smoothMovement * direction * speed);
                FLmotor.setPower(smoothMovement * direction * speed);
                BLmotor.setPower(-smoothMovement * direction * speed);
            }
            if (gamepad1.dpad_right) {
                FRmotor.setPower(smoothMovement * direction * speed);
                BRmotor.setPower(-smoothMovement * direction * speed);
                FLmotor.setPower(-smoothMovement * direction * speed);
                BLmotor.setPower(smoothMovement * direction * speed);
            }
            if(gamepad1.left_bumper){
                FRmotor.setPower(1);
                BRmotor.setPower(1);
                FLmotor.setPower(1);
                BLmotor.setPower(1);
            }
            if(gamepad1.right_bumper){
                FRmotor.setPower(-1);
                BRmotor.setPower(-1);
                FLmotor.setPower(-1);
                BLmotor.setPower(-1);
            }
            if (gamepad2.y)
                roata.setPower(-0.7);
            else
                roata.setPower(0);
        }
    }
}
