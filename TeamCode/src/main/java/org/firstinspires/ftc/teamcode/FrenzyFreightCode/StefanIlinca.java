package org.firstinspires.ftc.teamcode.FrenzyFreightCode;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
@Disabled
//@Autonomous(name="Ilinca Auto", group="Auto")
public class StefanIlinca extends LinearOpMode {

    private final double diameter = 9.6;
    private final double countsPerMotorRev = 537.5;
    private final double gearRed = 1;
    private final double circumference = Math.PI*diameter;
    private final double actualCountsPerRev = countsPerMotorRev * gearRed;
    private final double countsPerCM = actualCountsPerRev / circumference;
    private final double robotLength = 31*1.33;// 44
    private final double robotWidth = 34*1.33;/**de modificat*/ //32
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

    private void ManaDeschide()
    {
        mana.setPower(-1);
        sleep(10);
        mana.setPower(0);
    }
    @Override
    public void runOpMode()
    {
        Initialize();
        senzorus.start();
        waitForStart();

        DriveForward(1.0,40);
        DuckPosition=ScanDucks();
        telemetry.addData("pozitia e ",DuckPosition);
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
        TurnRight(0.7, 77);
        //pozmaniera(-500);
        DriveBackwards(1, 116.5);
        roata.setPower(0.45);
        sleep(1800);
        roata.setPower(0);
        TurnRight(1,18);
        pozmaniera(-150);
        pozbrat(0);

        DriveForward(1,20);
        DriveLeft(1,20);
        TurnRight(1,5);
        FRwheelMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FLwheelMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BRwheelMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BLwheelMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        DriveForward2(0.6,200);
    }


    public void Initialize()
    {
        FRwheelMotor = hardwareMap.get(DcMotor.class,"fataDreapta");
        FLwheelMotor = hardwareMap.get(DcMotor.class,"fataStanga");
        BRwheelMotor = hardwareMap.get(DcMotor.class,"spateDreapta");
        BLwheelMotor = hardwareMap.get(DcMotor.class,"spateStanga");

        FRwheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FLwheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BRwheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BLwheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        FRwheelMotor.setTargetPosition(0);
        BRwheelMotor.setTargetPosition(0);
        FLwheelMotor.setTargetPosition(0);
        BLwheelMotor.setTargetPosition(0);

        FRwheelMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FLwheelMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BRwheelMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BLwheelMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        FRwheelMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        BRwheelMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        FLwheelMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        BLwheelMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        roata=hardwareMap.get(DcMotor.class,"roata" );
        maniera=hardwareMap.get(DcMotor.class,"maniera" );
        brat=hardwareMap.get(DcMotor.class,"brat");
        mana= hardwareMap.get(DcMotor.class,"mana");
        SensorRatusca = hardwareMap.get(Rev2mDistanceSensor.class, "senzorDistantaRatusca");

        brat.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mana.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        maniera.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        roata.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        maniera.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        brat.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mana.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        maniera.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        roata.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        mana.setDirection(DcMotorSimple.Direction.FORWARD);
        brat.setDirection(DcMotorSimple.Direction.FORWARD);
        maniera.setDirection(DcMotorSimple.Direction.FORWARD);
        roata.setDirection(DcMotorSimple.Direction.FORWARD);

        brat.setPower(-0.1);
    }
    Thread pozBratT = new Thread(() -> {
        while(bratWantedPoz!=brat.getCurrentPosition()&& opModeIsActive() ) {
            brat.setPower(bratWantedPoz>brat.getCurrentPosition()? 0.85:-0.9);
        }
        brat.setPower(-0.1);
    });
    public void pozbrat(int wantedPos) {
        bratWantedPoz=wantedPos;
        pozBratT.start();
    }
    Thread pozManieraT = new Thread(() -> {
        while((  manieraWantedPoz-maniera.getCurrentPosition()<-20||
                manieraWantedPoz-maniera.getCurrentPosition()>20 )&& opModeIsActive() ) {
            maniera.setPower(manieraWantedPoz>maniera.getCurrentPosition()? 0.8:-0.8);
        }
        maniera.setPower(-0.05);
    });
    public void pozmaniera(int wantedPos){
        manieraWantedPoz=wantedPos;
        pozManieraT.start();
    }

    public void ScenarioSt() {
        pozbrat(100);
        pozmaniera(-660);
        while(pozBratT.isAlive()||pozManieraT.isAlive()) sleep(20);
        DriveLeft(1.0, 40);
        ManaDeschide();
        DriveBackwards(1.0,5);
    }

    public void ScenarioMij() {
        pozbrat(100);
        pozmaniera(-1520);
        while(pozBratT.isAlive()||pozManieraT.isAlive()) sleep(20);
        DriveLeft(1.0, 40);
        DriveForward(1.0, 9);
        ManaDeschide();
        DriveBackwards(1.0,14);
    }

    public void ScenarioDr(){
        pozbrat(0);
        pozmaniera(-1520);
        while(pozBratT.isAlive()&&pozManieraT.isAlive()) sleep(20);
        DriveLeft(1.0, 40);
        DriveForward(1.0, 11);
        pozbrat(40);
        while(pozBratT.isAlive())sleep(10);
        ManaDeschide();
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
        return SensorRatusca.getDistance(DistanceUnit.CM) < 30? 'S':'D';
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

            FRwheelMotor.setPower(0);
            BRwheelMotor.setPower(0);
            FLwheelMotor.setPower(0);
            BLwheelMotor.setPower(0);

        }
    }

    public void DriveTo2(double speed, double frontRightCM, double backRightCM, double frontLeftCM, double backLeftCM)/**fara encoder*/
    {

        if(opModeIsActive()) {

            int frontRightNewPos = FRwheelMotor.getCurrentPosition() + (int) (frontRightCM * countsPerCM);
            int backRightNewPos = BRwheelMotor.getCurrentPosition() + (int) (backRightCM * countsPerCM);
            int frontLeftNewPos = FLwheelMotor.getCurrentPosition() + (int) (frontLeftCM * countsPerCM);
            int backLeftNewPos = BLwheelMotor.getCurrentPosition() + (int) (backLeftCM * countsPerCM);

            FRwheelMotor.setPower(speed);
            BRwheelMotor.setPower(speed);
            FLwheelMotor.setPower(speed*0.9);
            BLwheelMotor.setPower(speed*0.9);

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
    public void TurnRight(double speed, double degrees) {
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