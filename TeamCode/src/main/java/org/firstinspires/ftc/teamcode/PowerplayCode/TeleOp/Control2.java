package org.firstinspires.ftc.teamcode.PowerplayCode.TeleOp;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.robot.Robot;


import org.checkerframework.checker.units.qual.Angle;
import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.MotionDetection;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.internal.system.AppUtil;
import org.firstinspires.ftc.teamcode.PowerplayCode.RobotComponents;
import org.firstinspires.ftc.teamcode.PowerplayCode.RobotParamaters;
import org.firstinspires.ftc.teamcode.PowerplayCode.config;
import org.firstinspires.inspection.GamepadInspection;

import java.util.Locale;
import java.util.Vector;

@TeleOp(name="Control2")
public class Control2 extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        //init();
        waitForStart();
        while (opModeIsActive()) {

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
        //RobotComponents.Lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //RobotComponents.Lift2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RobotComponents.RotireTank.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //RobotComponents.Mana.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

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


    }

}
