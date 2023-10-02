package org.firstinspires.ftc.teamcode.FrenzyFreightCode;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
@Disabled
//@Autonomous(name="Edi Auto", group="Auto")
public class Ste_Autonomus extends LinearOpMode {

    private final double diameter = 9.6;
    private final double countsPerMotorRev = 537.5;
    private final double gearRed = 1;
    private final double circumference = Math.PI*diameter;
    private final double actualCountsPerRev = countsPerMotorRev * gearRed;
    private final double countsPerCM = actualCountsPerRev / circumference;
    private final double robotLength = 31*1.33;
    private final double robotWidth = 17*1.33;/**de modificat*/
    private final double robotDiameter = Math.sqrt(robotLength*robotLength+robotWidth*robotWidth);
    private final double robotCircumference = Math.PI*robotDiameter;
    private char DuckPosition=' ';
    private DcMotor roata=null;
    private DcMotor maniera=null;
    private DcMotor mana=null;
    private DcMotor brat=null;
    private DcMotor FRwheelMotor = null;
    private DcMotor FLwheelMotor = null;
    private DcMotor BRwheelMotor = null;
    private DcMotor BLwheelMotor = null;
    private Rev2mDistanceSensor SensorRatusca = null;
    private int bratWantedPoz=0;
    private int manieraWantedPoz=0;
    Thread senzorus = new Thread(() -> {
        Boolean strans = false;
        while (!strans) {
            if (SensorRatusca.getDistance(DistanceUnit.CM) < 20)
            {
                strans = true;
                mana.setPower(0.8);
            }
        }
    });

    Thread ScanDuck = new Thread(() -> {
        telemetry.addData("dist init", SensorRatusca.getDistance(DistanceUnit.CM));
        telemetry.update();
        if (SensorRatusca.getDistance(DistanceUnit.CM) < 30)
            DuckPosition = 'M';
        else {
            sleep(30);
            while (FRwheelMotor.isBusy()) {
                if (SensorRatusca.getDistance(DistanceUnit.CM) < 30) {
                    DuckPosition = 'S';
                }
                sleep(15);
            }
        }
        if (DuckPosition == ' ')
            DuckPosition = 'D';
    });

    @Override
    public void runOpMode()
    {
        Initialize();
        senzorus.start();
        waitForStart();

        DriveForward(1.0,40);
        ScanDuck.start();
        DriveLeft(1,62);
        telemetry.addData("ratusca: ", DuckPosition);
        telemetry.update();
        switch (DuckPosition)
        {
            case 'D':
                ScenarioDr();
                break;
            case 'M':
                ScenarioMij();
                break;
            case 'S':
                ScenarioSt();
                break;
        }
        TurnRight(1, 75);
        //pozmaniera(-500);
        DriveBackwards(1, 111.5);
        roata.setPower(0.45);
        sleep(1800);
        roata.setPower(0);
        TurnRight(1,10);
        pozmaniera(-150);
        pozbrat(0);
        DriveForward(1,30);
        DriveRight(1,20);

        //DriveLeft(1,24);
        sleep(100);
        FRwheelMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FLwheelMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BRwheelMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BLwheelMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        DriveForward2(0.8,200);
        sleep(5000);
    }


    public void Initialize()
    {
        FRwheelMotor = hardwareMap.get(DcMotor.class,"fataDreapta");
        FLwheelMotor = hardwareMap.get(DcMotor.class,"fataStanga");
        BRwheelMotor = hardwareMap.get(DcMotor.class,"spateDreapta");
        BLwheelMotor = hardwareMap.get(DcMotor.class,"spateStanga");
        roata=hardwareMap.get(DcMotor.class,"roata" );
        maniera=hardwareMap.get(DcMotor.class,"maniera" );
        brat=hardwareMap.get(DcMotor.class,"brat");
        mana= hardwareMap.get(DcMotor.class,"mana");
        SensorRatusca = hardwareMap.get(Rev2mDistanceSensor.class, "senzorDistantaRatusca");

        FRwheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FLwheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BRwheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BLwheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        brat.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mana.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        maniera.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        maniera.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FRwheelMotor.setTargetPosition(0);
        BRwheelMotor.setTargetPosition(0);
        FLwheelMotor.setTargetPosition(0);
        BLwheelMotor.setTargetPosition(0);

        FRwheelMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FLwheelMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BRwheelMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BLwheelMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        maniera.setTargetPosition(0);
        brat.setTargetPosition(0);
        maniera.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        brat.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        maniera.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FRwheelMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        BRwheelMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        FLwheelMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        mana.setDirection(DcMotorSimple.Direction.FORWARD);

        BLwheelMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        brat.setPower(-0.1);
    }
    Thread pozBratT = new Thread(() -> {
        brat.setTargetPosition(bratWantedPoz);
        brat.setPower(0.5);
        while(brat.isBusy()&& opModeIsActive() );
        brat.setPower(0.1);
    });
    public void pozbrat(int wantedPos) {
        bratWantedPoz=wantedPos;
        pozBratT.start();
    }
    Thread pozManieraT = new Thread(() -> {
        maniera.setTargetPosition(manieraWantedPoz);
        maniera.setPower(0.8);
        while(  maniera.isBusy()&& opModeIsActive() );
        maniera.setPower(0.1);
    });
    public void pozmaniera(int wantedPos){
        manieraWantedPoz=wantedPos;
        pozManieraT.start();
    }

    public void ScenarioSt() {
        pozbrat(100);
        pozmaniera(-630);
        while(pozBratT.isAlive()||pozManieraT.isAlive()) sleep(20);
//    DriveLeft(1.0, 40);
        mana.setPower(-1);
        sleep(10);
        mana.setPower(0);
        DriveBackwards(1.0,5);
    }

    public void ScenarioMij() {
        pozbrat(100);
        pozmaniera(-1520);
        while(pozBratT.isAlive()||pozManieraT.isAlive()) sleep(20);
//    DriveLeft(1.0, 40);
        DriveForward(1.0, 11);
        mana.setPower(-1);
        sleep(10);
        mana.setPower(0);
        DriveBackwards(1.0,16);
    }

    public void ScenarioDr(){
        pozbrat(0);
        pozmaniera(-1520);
        while(pozBratT.isAlive()&&pozManieraT.isAlive()) sleep(20);
//    DriveLeft(1.0, 40);
        DriveForward(1.0, 11);
        pozbrat(40);
        sleep(50);
        //while(pozBratT.isAlive())sleep(10);
        mana.setPower(-1);
        sleep(10);
        mana.setPower(0);
        pozbrat(0);

        //while(pozBratT.isAlive())sleep(10);
        DriveBackwards(1.0,16);
    }

    public char ScanDucks() {
        if (SensorRatusca.getDistance(DistanceUnit.CM) < 30)
        {
            DriveLeft(1,22);
            return 'M';

        }
        DriveLeft(1,22);
        if(SensorRatusca.getDistance(DistanceUnit.CM) < 30)
            return 'S';
        else
            return 'D';
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
            );

 /**        FRwheelMotor.setPower(0);
            BRwheelMotor.setPower(0);
            FLwheelMotor.setPower(0);
            BLwheelMotor.setPower(0);
*/
        }
    }

    public void DriveTo2(double speed, double frontRightCM, double backRightCM, double frontLeftCM, double backLeftCM)
    {

        if(opModeIsActive()) {

            int frontRightNewPos = FRwheelMotor.getCurrentPosition() + (int) (frontRightCM * countsPerCM);
            int backRightNewPos = BRwheelMotor.getCurrentPosition() + (int) (backRightCM * countsPerCM);
            int frontLeftNewPos = FLwheelMotor.getCurrentPosition() + (int) (frontLeftCM * countsPerCM);
            int backLeftNewPos = BLwheelMotor.getCurrentPosition() + (int) (backLeftCM * countsPerCM);

            FRwheelMotor.setPower(speed);
            BRwheelMotor.setPower(speed);
            FLwheelMotor.setPower(speed);
            BLwheelMotor.setPower(speed);

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
            while(  opModeIsActive() &&
                    (
                            FRwheelMotor.getCurrentPosition() < frontRightNewPos ||
                                    BRwheelMotor.getCurrentPosition() < backRightNewPos ||
                                    FLwheelMotor.getCurrentPosition() < frontLeftNewPos ||
                                    BLwheelMotor.getCurrentPosition() < backLeftNewPos
                    )
            );

            FRwheelMotor.setPower(0);
            BRwheelMotor.setPower(0);
            FLwheelMotor.setPower(0);
            BLwheelMotor.setPower(0);

        }
    }
    public void DriveForward(double speed, double distanceCM) {
        DriveTo(speed,distanceCM,distanceCM,distanceCM,distanceCM);
    }
    public void DriveForward2(double speed, double distanceCM) {
        DriveTo2(speed,distanceCM,distanceCM,distanceCM,distanceCM);
    }

    public void DriveBackwards(double speed, double distanceCM) {
        DriveTo(speed,-distanceCM,-distanceCM,-distanceCM,-distanceCM);
    }
    public void DriveRight(double speed, double distanceCM) {
        DriveTo(speed,-distanceCM,distanceCM,distanceCM,-distanceCM);
    }
    public void DriveLeft(double speed, double distanceCM) {
        DriveTo(speed,distanceCM,-distanceCM,-distanceCM,distanceCM);
    }
    public void TurnRight(double speed, double degrees){
        double arcRatio=degrees/360;
        double distanceCM = robotCircumference*arcRatio;
        DriveTo(speed,distanceCM,distanceCM,-distanceCM,-distanceCM);
    }
    public void TurnLeft(double speed, double degrees) {
        double arcRatio=degrees/360;
        double distanceCM = (robotCircumference*arcRatio);
        DriveTo(speed,-distanceCM,-distanceCM,distanceCM,distanceCM);
    }
}