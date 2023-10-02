package org.firstinspires.ftc.teamcode.PowerplayCode.TeleOp;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.internal.system.AppUtil;
import org.firstinspires.ftc.teamcode.PowerplayCode.RobotComponents;
import org.firstinspires.ftc.teamcode.PowerplayCode.RobotParamaters;
import org.firstinspires.ftc.teamcode.PowerplayCode.config;
import org.firstinspires.inspection.GamepadInspection;

import java.util.Locale;
import java.util.Vector;

@TeleOp(name="Control")
public class Control extends LinearOpMode {
    @Override
    public void runOpMode() {

        Init();
        waitForStart();
        while (opModeIsActive()) {

            Movement();
            MoveLift();
            Cleste();
            RotireTank();
            MoveMana();

            telemetry.addData("angle gyro: ", get_heading());
            telemetry.addData("angle joystick: ", Math.toDegrees(Math.atan2(gamepad1.left_stick_x, -gamepad1.left_stick_y)));
            telemetry.update();
        }
        RobotComponents.FRwheelMotor.setPower(0);
        RobotComponents.BRwheelMotor.setPower(0);
        RobotComponents.FLwheelMotor.setPower(0);
        RobotComponents.BLwheelMotor.setPower(0);
        RobotComponents.Lift.setPower(0);
        RobotComponents.RotireTank.setPower(0);
        RobotComponents.Lift2.setPower(0);
        RobotComponents.Mana.setPower(0);
    }

    boolean ClesteInchis=true;
    boolean XWasPressed=false;
    boolean BWasPressed=false;
    float start_angle;
    void Cleste(){
        if(gamepad2.x&&!XWasPressed){
            if(ClesteInchis){
                ClesteInchis=false;
                RobotComponents.Cleste.setPosition(RobotParamaters.servoOpen);
            }
            else{
                RobotComponents.Cleste.setPosition(RobotParamaters.servoClose);
                ClesteInchis=true;
            }
            XWasPressed=true;
        }
        if(!gamepad2.x){
            XWasPressed=false;
        }
    }
    void RotireTank(){
        int modifier=140;
        double power=1;
        RobotComponents.RotireTank.setPower(power);
        if (gamepad2.y)
            RobotComponents.RotireTank.setTargetPosition(0);
        else {
            int dirtank = 0;
            if (gamepad2.left_bumper)
                dirtank = -1;
            else if (gamepad2.right_bumper)
                dirtank = 1;
            if(dirtank==0||RobotComponents.Mana.getCurrentPosition()<150)
                return;
            RobotComponents.RotireTank.setTargetPosition(dirtank * modifier + RobotComponents.RotireTank.getCurrentPosition());
        }
    }
    void MoveLift(){
        if (gamepad2.b)
        {
            RobotComponents.Lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            RobotComponents.Lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            RobotComponents.Lift2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            RobotComponents.Lift2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        if(RobotComponents.Mana.getCurrentPosition()<150)
            return;
        double power = -gamepad2.left_stick_y;

        if (power > 0 && RobotComponents.Lift.getCurrentPosition() <= RobotParamaters.liftEnd && RobotComponents.Lift2.getCurrentPosition() <= RobotParamaters.liftEnd)
        {
            RobotComponents.Lift.setPower(power);
            RobotComponents.Lift2.setPower(power);
        }
        else if (power < 0 && RobotComponents.Lift.getCurrentPosition() > RobotParamaters.liftStart && RobotComponents.Lift2.getCurrentPosition() > RobotParamaters.liftStart){
            RobotComponents.Lift.setPower(power/2);
            RobotComponents.Lift2.setPower(power/2);
        }
        else {
            RobotComponents.Lift.setPower(0);
            RobotComponents.Lift2.setPower(0);
        }
        /*
        if (gamepad2.b)
        {
            RobotComponents.Lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            RobotComponents.Lift.setTargetPosition(0);
            RobotComponents.Lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            RobotComponents.Lift2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            RobotComponents.Lift2.setTargetPosition(0);
            RobotComponents.Lift2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        double modifier=80;
        double power=0.6;
        int CurrentPos1=RobotComponents.Lift.getCurrentPosition();
        int CurrentPos2=RobotComponents.Lift2.getCurrentPosition();
        int WantedPos=(int)clamp(RobotParamaters.liftStart,RobotParamaters.liftEnd,-gamepad2.left_stick_y*modifier+CurrentPos1);
        RobotComponents.Lift .setTargetPosition(WantedPos);
        RobotComponents.Lift2.setTargetPosition(WantedPos);

        RobotComponents.Lift .setPower((Math.abs(WantedPos-CurrentPos1)>5)?power:0);
        RobotComponents.Lift2.setPower((Math.abs(WantedPos-CurrentPos2)>5)?power:0);*/
    }
    void MoveMana(){
        RobotComponents.Mana.setPower(0);
        if (gamepad2.right_stick_y<0 && RobotComponents.Mana.getCurrentPosition() >= RobotParamaters.manaEndDriver-25)
            return;
        if (gamepad2.right_stick_y>0 && RobotComponents.Mana.getCurrentPosition() <= RobotParamaters.mamaStart+25)
            return;
        RobotComponents.Mana.setPower(-gamepad2.right_stick_y*0.6);

    }

    private float get_heading() {
        Orientation yes = RobotComponents.giroscop.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        return AngleUnit.DEGREES.normalize(start_angle - AngleUnit.DEGREES.fromUnit(yes.angleUnit, yes.firstAngle));
    }

    public double clamp(double min,double max,double val){
        if(min>val)
            return min;
        if(max<val)
            return max;
        return val;
    }

    private double getTurnAngle(double robotAngle,double targetAngle){
       /* if (robotAngle < 0)
            robotAngle += 360;
        if (targetAngle < 0)
            targetAngle += 360;

        double val1 = robotAngle - targetAngle;
        double val2 = robotAngle + 360 - targetAngle;

        return val1 < val2 ? val1 : val2;
        */

        if(targetAngle<robotAngle)
            targetAngle+=360;
        double finalAngle= targetAngle-robotAngle;
        if(Math.abs(finalAngle)>180)
            return -360+Math.abs(finalAngle);
        else
            return finalAngle;
    }
    private void Movement() {
        if (gamepad1.b) {
            Orientation RobotAngle = RobotComponents.giroscop.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

            start_angle = AngleUnit.DEGREES.normalize(AngleUnit.DEGREES.fromUnit(RobotAngle.angleUnit, RobotAngle.firstAngle));
        }
        double speed = Math.sqrt(gamepad1.left_stick_y*gamepad1.left_stick_y + gamepad1.left_stick_x*gamepad1.left_stick_x);

        double angle_joystick = Math.toDegrees(Math.atan2(gamepad1.left_stick_x, -gamepad1.left_stick_y));
        double angle_joystick2 = Math.toDegrees(Math.atan2(gamepad1.right_stick_x, -gamepad1.right_stick_y));
        double angle = angle_joystick - get_heading();
        double distanceFRBL=Math.cos(Math.toRadians(angle+45));
        double distanceFLBR=Math.cos(Math.toRadians(angle-45));

        telemetry.addData("Joystick angle", angle_joystick2);
        telemetry.addData("Drive angle", angle);
        telemetry.addData("Dist FRBL", distanceFRBL);
        telemetry.addData("Dist FLBR", distanceFLBR);

        double t1=0,t2=0,t3=0,t4=0;

        if ((Math.abs(gamepad1.right_stick_x) > 0.05 || Math.abs(gamepad1.right_stick_y) > 0.05)) {
            angle = getTurnAngle(get_heading(), angle_joystick2);

            if (Math.abs(angle) > 2) {
                double dir = angle > 0 ? 1 : -1;
                if (angle < 10)
                    dir = clamp(-1,1, angle/45);
                if (Math.abs(dir) < 0.15)
                {
                    if (dir < 0)
                        dir = -0.15;
                    else if (dir > 0) dir = 0.15;
                }

                t1 = -dir;
                t2 = -dir;
                t3 = dir;
                t4 = dir;
                /*
                RobotComponents.FRwheelMotor.setPower(-dir);
                RobotComponents.BRwheelMotor.setPower(-dir);
                RobotComponents.FLwheelMotor.setPower(dir);
                RobotComponents.BLwheelMotor.setPower(dir);*/
            }
        }

        if (speed == 0 && (t1 != 0 || t2 != 0 || t3 != 0 || t4 != 0)) {
            RobotComponents.FRwheelMotor.setPower(t1);
            RobotComponents.BRwheelMotor.setPower(t2);
            RobotComponents.FLwheelMotor.setPower(t3);
            RobotComponents.BLwheelMotor.setPower(t4);
        }
        else {
            RobotComponents.FRwheelMotor.setPower(speed * (distanceFRBL + t1));
            RobotComponents.BRwheelMotor.setPower(speed * (distanceFLBR + t2));
            RobotComponents.FLwheelMotor.setPower(speed * (distanceFLBR + t3));
            RobotComponents.BLwheelMotor.setPower(speed * (distanceFRBL + t4));
        }
    }

    public void Init() {

        RobotComponents.FRwheelMotor = hardwareMap.get(DcMotor.class, config.FRMOTOR);
        RobotComponents.BRwheelMotor = hardwareMap.get(DcMotor.class, config.BRMOTOR);
        RobotComponents.FLwheelMotor = hardwareMap.get(DcMotor.class, config.FLMOTOR);
        RobotComponents.BLwheelMotor = hardwareMap.get(DcMotor.class, config.BLMOTOR);
        RobotComponents.giroscop = hardwareMap.get(BNO055IMU.class, config.GIROSCOP);

        RobotComponents.Lift = hardwareMap.get(DcMotor.class, config.LIFT);
        RobotComponents.Lift2 = hardwareMap.get(DcMotor.class, config.LIFT2);
        RobotComponents.RotireTank = hardwareMap.get(DcMotor.class, config.ROTIRE_TANK);
        RobotComponents.Mana=hardwareMap.get(DcMotor.class,config.MANA);
        RobotComponents.Cleste=hardwareMap.get(Servo.class,config.CLESTE);

        RobotComponents.FRwheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RobotComponents.BRwheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RobotComponents.FLwheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RobotComponents.BLwheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        RobotComponents.FRwheelMotor.setTargetPosition(0);
        RobotComponents.BRwheelMotor.setTargetPosition(0);
        RobotComponents.FLwheelMotor.setTargetPosition(0);
        RobotComponents.BLwheelMotor.setTargetPosition(0);
        RobotComponents.RotireTank.setTargetPosition(0);
        RobotComponents.Mana.setTargetPosition(0);

        RobotComponents.FRwheelMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        RobotComponents.BRwheelMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        RobotComponents.FLwheelMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        RobotComponents.BLwheelMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        RobotComponents.Lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RobotComponents.Lift2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RobotComponents.RotireTank.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RobotComponents.Mana.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        RobotComponents.BLwheelMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        RobotComponents.FLwheelMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        RobotComponents.RotireTank.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        RobotComponents.FRwheelMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RobotComponents.BRwheelMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RobotComponents.FLwheelMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RobotComponents.BLwheelMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        RobotComponents.giroscop.initialize(parameters);
        sleep(100);

        Orientation yes = RobotComponents.giroscop.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        start_angle = AngleUnit.DEGREES.normalize(AngleUnit.DEGREES.fromUnit(yes.angleUnit, yes.firstAngle));

        telemetry.addLine("Giroscop calibrat, cf Stefane");
        telemetry.update();
    }

    String formatAngle(AngleUnit angleUnit, double angle) {
        return formatDegrees(AngleUnit.DEGREES.fromUnit(angleUnit, angle));
    }

    String formatDegrees(double degrees){
        return String.format(Locale.getDefault(), "%.1f", AngleUnit.DEGREES.normalize(degrees));
    }
}