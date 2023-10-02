package org.firstinspires.ftc.teamcode.PowerplayCode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.PowerplayCode.Autonomy.ColorDetectCamera;
public class RobotComponents {
    public static ColorDetectCamera Camera;
    public static DcMotor FRwheelMotor=null;
    public static DcMotor BRwheelMotor=null;
    public static DcMotor FLwheelMotor=null;
    public static DcMotor BLwheelMotor=null;
    public static BNO055IMU giroscop;


    public static DcMotor Lift=null;
    public static DcMotor Lift2=null;
    public static DcMotor RotireTank =null;
    public static DcMotor Mana=null;

    public static Servo Cleste =null;

}
