package org.firstinspires.ftc.teamcode.OldDobCcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
@Disabled
//@Autonomous(name="Dob_Autonomus", group="Auto")
public class Dob_Autonomus extends LinearOpMode {

    private final double diameter = 10;
    private final double countsPerMotorRev = 1120;
    private final double gearRed = 1;
    private final double circumference = Math.PI*diameter;
    private final double actualCountsPerRev = countsPerMotorRev * gearRed;
    private final double countsPerCM = actualCountsPerRev / circumference;
    private final double robotLength = 36;
    private final double robotWidth = 23;
    private final double robotDiameter = Math.sqrt(robotLength*robotLength+robotWidth*robotWidth);
    private final double robotCircumference = Math.PI*robotDiameter;
    private int nrRings = 0;
    private final double servoStart=0;   //Daca nu setati direct servoul modificati aici pana da bine
    private final double servoEnd=0.4;

    private DcMotor RampMotor = null;
    private DcMotor LauncherMotor = null;
    private DcMotor FRwheelMotor = null;
    private DcMotor FLwheelMotor = null;
    private DcMotor BRwheelMotor = null;
    private DcMotor BLwheelMotor = null;
    private Servo LauncherServo = null;
    private Servo ServoClaw = null;
    private Servo ServoArm = null;
    private ColorRangeSensor lowSensor =null;
    private Rev2mDistanceSensor highSensor = null;

    Thread Fire = new Thread(() -> {
        LauncherServo.setPosition(servoEnd);
        sleep(500);
        LauncherServo.setPosition(servoStart);
        sleep(1500);
    });


    @Override
    public void runOpMode()
    {

        Initialize();

        telemetry.addData("Status:", "Gata");
        telemetry.update();
        waitForStart();
        DriveForward(1,93);
        nrRings=ScanRings();
        LauncherMotor.setPower(1);
        DriveForward(1,60);
        sleep(1000);

        TurnLeft(0.6,10);
        Launch(1);
        TurnLeft(0.6,10);
        Launch(1);
        TurnLeft(0.6,10);
        Launch(1);

        /*TurnLeft(0.5,30);
        Launch(1);
        LauncherMotor.setPower(0.9);
        sleep(1500);
        TurnRight(0.5,10);
        Launch(1);
        LauncherMotor.setPower(0.9);
        sleep(1500);
        TurnRight(0.5,5);
        Launch(1);
        TurnRight(0.5,15);*/
        sleep(100);
        LauncherMotor.setPower(0);
        TurnRight(0.6,30);

        switch (nrRings)
        {
            case 0:
                ScenarioA();
                break;
            case 1:
                ScenarioB();
                break;
            case 4:
                ScenarioC();
                break;
        }



    }

    public void Initialize()
    {
        FRwheelMotor = hardwareMap.get(DcMotor.class,"fataDreapta");
        FLwheelMotor = hardwareMap.get(DcMotor.class,"fataStanga");
        BRwheelMotor = hardwareMap.get(DcMotor.class,"spateDreapta");
        BLwheelMotor = hardwareMap.get(DcMotor.class,"spateStanga");

        RampMotor = hardwareMap.get(DcMotor.class,"rampa1");
        LauncherMotor = hardwareMap.get(DcMotor.class,"lansator1");

        LauncherServo = hardwareMap.get(Servo.class,"servo0");
        ServoClaw=hardwareMap.get(Servo.class,"servoWobble");
        ServoArm=hardwareMap.get(Servo.class,"servoNush");

        lowSensor =  hardwareMap.get(ColorRangeSensor.class, "senzorCuloare");
        highSensor = hardwareMap.get(Rev2mDistanceSensor.class, "senzorDistanta");

        FRwheelMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FLwheelMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BRwheelMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BLwheelMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        FRwheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FLwheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BRwheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BLwheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        FRwheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FLwheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BRwheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BLwheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        RampMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        LauncherMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        FRwheelMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        BRwheelMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        FLwheelMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        BLwheelMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        RampMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        LauncherMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        LauncherServo.setPosition(servoStart);
        ServoClaw.setPosition(1);
        ServoArm.setDirection(Servo.Direction.REVERSE);
        ServoArm.setPosition(0);


    }

    public void ScenarioA()
    {
        DriveForward(1,30);
        DriveRight(0.5,55);
        DropWobble();
    }

    public void ScenarioB()
    {
        DriveRight(0.5,28);
        RampMotor.setPower(1);
        DriveBackwards(1,60);
        sleep(1500);
        LauncherMotor.setPower(1);
        sleep(1000);
        DriveForward(1,60);
        RampMotor.setPower(0);
        sleep(500);
        Launch(1);
        LauncherMotor.setPower(0);
        DriveForward(1,90);
        DriveLeft(0.5,28);
        DropWobble();
        DriveBackwards(1,70);
    }

    public void ScenarioC()
    {
        DriveRight(1,55);
        DriveForward(1,115);
        DropWobble();
        DriveBackwards(1,115);
    }

    public int ScanRings()
    {
        if(lowSensor.getDistance(DistanceUnit.CM)<50)
            if(highSensor.getDistance(DistanceUnit.CM)<50)
                return 4;
            else
                return 1;
        else
            return 0;
    }

    public void Launch(int nofRings)
    {

        while(nofRings>0 && opModeIsActive())
        {
            if(!Fire.isAlive())
            {
                Fire.start();
                nofRings--;
            }
        }
        sleep(200);
    }

    public void DropWobble()
    {
        ServoArm.setPosition(0.4);
        sleep(200);
        ServoClaw.setPosition(0);
        sleep(200);
        ServoArm.setPosition(0);
    }

    public void DriveTo(double speed, double frontRightCM, double backRightCM, double frontLeftCM, double backLeftCM)
    {

        if(opModeIsActive()) {

            int frontRightNewPos = FRwheelMotor.getCurrentPosition() + (int) (frontRightCM * countsPerCM);
            int backRightNewPos = BRwheelMotor.getCurrentPosition() + (int) (backRightCM * countsPerCM);
            int frontLeftNewPos = FLwheelMotor.getCurrentPosition() + (int) (frontLeftCM * countsPerCM);
            int backLeftNewPos = BLwheelMotor.getCurrentPosition() + (int) (backLeftCM * countsPerCM);

            FRwheelMotor.setTargetPosition(frontRightNewPos);
            BRwheelMotor.setTargetPosition(backRightNewPos);
            FLwheelMotor.setTargetPosition(frontLeftNewPos);
            BLwheelMotor.setTargetPosition(backLeftNewPos);

            FRwheelMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            FLwheelMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            BRwheelMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            BLwheelMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            FRwheelMotor.setPower(speed);
            BRwheelMotor.setPower(speed);
            FLwheelMotor.setPower(speed);
            BLwheelMotor.setPower(speed);

            while(  opModeIsActive() &&
                    (       FRwheelMotor.isBusy() ||
                            BRwheelMotor.isBusy() ||
                            FLwheelMotor.isBusy() ||
                            BLwheelMotor.isBusy()
                    )
            ) {
                telemetry.addData("Scenario:" , nrRings);

                telemetry.addData("Destination", "FR: %7d ; BR: %7d ; FL: %7d ; BL: %7d",
                        frontRightNewPos,
                        backRightNewPos,
                        frontLeftNewPos,
                        backLeftNewPos);

                telemetry.addData("Position", "FR: %7d ; BR: %7d ; FL: %7d ; BL: %7d",
                        FRwheelMotor.getCurrentPosition(),
                        BRwheelMotor.getCurrentPosition(),
                        FLwheelMotor.getCurrentPosition(),
                        BLwheelMotor.getCurrentPosition());


                telemetry.update();
            }

            FRwheelMotor.setPower(0);
            BRwheelMotor.setPower(0);
            FLwheelMotor.setPower(0);
            BLwheelMotor.setPower(0);

            FRwheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            FLwheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            BRwheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            BLwheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            sleep(200);
        }

    }
    public void DriveForward(double speed, double distanceCM)
    {
        DriveTo(speed,distanceCM,distanceCM,distanceCM,distanceCM);
    }
    public void DriveBackwards(double speed, double distanceCM)
    {
        DriveTo(speed,-distanceCM,-distanceCM,-distanceCM,-distanceCM);
    }
    public void DriveRight(double speed, double distanceCM)
    {
        DriveTo(speed,-distanceCM,distanceCM,distanceCM,-distanceCM);
    }
    public void DriveLeft(double speed, double distanceCM)
    {
        DriveTo(speed,distanceCM,-distanceCM,-distanceCM,distanceCM);
    }
    public void TurnRight(double speed, double degrees)
    {
        double arcRatio=degrees/360;
        double distanceCM = robotCircumference*arcRatio;
        DriveTo(speed,distanceCM,distanceCM,-distanceCM,-distanceCM);
    }
    public void TurnLeft(double speed, double degrees)
    {
        double arcRatio=degrees/360;
        double distanceCM = (robotCircumference*arcRatio);
        DriveTo(speed,-distanceCM,-distanceCM,distanceCM,distanceCM);
    }
}