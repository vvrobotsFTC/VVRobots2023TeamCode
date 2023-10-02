package org.firstinspires.ftc.teamcode.FrenzyFreightCode;

import android.hardware.Sensor;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
@Disabled
//@Autonomous(name="Auto Test", group="Auto")
public class auto_test extends LinearOpMode {

    private final double diameter = 9.6;
    private final double countsPerMotorRev = 537.5;
    private final double gearRed = 1;
    private final double circumference = Math.PI*diameter;///30.15
    private final double actualCountsPerRev = countsPerMotorRev * gearRed;///537.5
    private final double countsPerCM = actualCountsPerRev / circumference;//17,8
    private final double robotLength = 30*1.33;// 44
    private final double robotWidth = 29*1.33;/**de modificat*/ //32
    private final double robotDiameter = Math.sqrt(robotLength*robotLength+robotWidth*robotWidth);
    private final double robotCircumference = Math.PI*robotDiameter;

    private DcMotor FRwheelMotor = null;
    private DcMotor FLwheelMotor = null;
    private DcMotor BRwheelMotor = null;
    private DcMotor BLwheelMotor = null;


    @Override
    public void runOpMode() {
        Initialize();

        waitForStart();
        DriveForward(1,10,15);

    }

    public void Initialize() {
        FRwheelMotor=hardwareMap.get(DcMotor.class,"fataDreapta");
        FLwheelMotor=hardwareMap.get(DcMotor.class,"fataStanga");
        BRwheelMotor=hardwareMap.get(DcMotor.class,"spateDreapta");
        BLwheelMotor=hardwareMap.get(DcMotor.class,"spateStanga");


        FRwheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FLwheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BRwheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BLwheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FRwheelMotor.setTargetPosition(0);
        BLwheelMotor.setTargetPosition(0);
        BRwheelMotor.setTargetPosition(0);
        FLwheelMotor.setTargetPosition(0);

        FRwheelMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FLwheelMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BRwheelMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BLwheelMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        FRwheelMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        FLwheelMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        BRwheelMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        BLwheelMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    public boolean Semicomp(int a, int b, int Error) {
        if(a-b>-Error&&a-b<Error)
            return false;
        else
            return true;

    }
    public void DriveTo(double speed, double frontRightCM, double backRightCM, double frontLeftCM, double backLeftCM,int Error) {

        if(opModeIsActive()) {

            int frontRightNewPos = FRwheelMotor.getCurrentPosition() + (int) (frontRightCM * countsPerCM);
            int backRightNewPos = BRwheelMotor.getCurrentPosition() + (int) (backRightCM * countsPerCM);
            int frontLeftNewPos = FLwheelMotor.getCurrentPosition() + (int) (frontLeftCM * countsPerCM);
            int backLeftNewPos = BLwheelMotor.getCurrentPosition() + (int) (backLeftCM * countsPerCM);

            FRwheelMotor.setTargetPosition(frontRightNewPos);
            BRwheelMotor.setTargetPosition(backRightNewPos);
            FLwheelMotor.setTargetPosition(frontLeftNewPos);
            BLwheelMotor.setTargetPosition(backLeftNewPos);

            FRwheelMotor.setPower(speed);
            BRwheelMotor.setPower(speed);
            FLwheelMotor.setPower(speed);
            BLwheelMotor.setPower(speed);


            while(  opModeIsActive() &&
                    (
                            Semicomp(FRwheelMotor.getCurrentPosition(), FRwheelMotor.getTargetPosition(),Error) ||
                            Semicomp(BRwheelMotor.getCurrentPosition(), BRwheelMotor.getTargetPosition(),Error) ||
                            Semicomp(FLwheelMotor.getCurrentPosition(), FLwheelMotor.getTargetPosition(),Error) ||
                            Semicomp(BLwheelMotor.getCurrentPosition(), BLwheelMotor.getTargetPosition(),Error)
                    )
            );
            FRwheelMotor.setPower(0);
            BRwheelMotor.setPower(0);
            FLwheelMotor.setPower(0);
            BLwheelMotor.setPower(0);
        }
    }

    public void DriveForward(double speed, double distanceCM,int Error) {
        DriveTo(speed,distanceCM,distanceCM,distanceCM,distanceCM,Error);
    }

    public void DriveBackwards(double speed, double distanceCM,int Error) {
        DriveTo(speed,-distanceCM,-distanceCM,-distanceCM,-distanceCM,Error);
    }
    public void DriveRight(double speed, double distanceCM,int Error) {
        DriveTo(speed,-distanceCM,distanceCM,distanceCM,-distanceCM,Error);
    }
    public void DriveLeft(double speed, double distanceCM,int Error) {
        DriveTo(speed,distanceCM,-distanceCM,-distanceCM,distanceCM,Error);
    }
    public void TurnRight(double speed, double degrees,int Error) {
        double arcRatio=degrees/360;
        double distanceCM = robotCircumference*arcRatio;
        DriveTo(speed,distanceCM,distanceCM,-distanceCM,-distanceCM,Error);
    }
    public void TurnLeft(double speed, double degrees,int Error) {
        double arcRatio=degrees/360;
        double distanceCM = (robotCircumference*arcRatio);
        DriveTo(speed,-distanceCM,-distanceCM,distanceCM,distanceCM,Error);
    }
}