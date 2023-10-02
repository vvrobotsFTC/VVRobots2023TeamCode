package org.firstinspires.ftc.teamcode.OldDobCcode;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
@Disabled
//@Autonomous(name="Ste Auto", group="Auto")
public class AutoTest extends LinearOpMode {

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
  Thread Afsare = new Thread(() -> {
    while(opModeIsActive()) {
      telemetry.addData("caz", DuckPosition);
      telemetry.addData("Brat Target", brat.getTargetPosition());
      telemetry.addData("brat Current", brat.getCurrentPosition());
      telemetry.addData("Maniera Target", maniera.getTargetPosition());
      telemetry.addData("Maniera Current", maniera.getCurrentPosition());
      telemetry.update();
    }
    });
  private void ManaDeschide() {
    mana.setPower(-1);
    sleep(10);
    mana.setPower(0);
  }
  @Override
  public void runOpMode() {
    Initialize();
    mana.setPower(0.8);
    waitForStart();
    Afsare.start();
    brat.setPower(1);
    maniera.setPower(1);

    DriveForward(1.0,40,10);
    DuckPosition=ScanDucks();

    switch (DuckPosition) {
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
    TurnRight(0.8, 77,3);
    //pozmaniera(-500);
    DriveBackwards(1, 116.5,10);
    roata.setPower(0.45);
    sleep(1800);
    roata.setPower(0);
    TurnRight(1,18,10);
    maniera.setTargetPosition(-150);
    brat.setTargetPosition(0);

    DriveForward(1,20,15);
    DriveLeft(1,10,15);
    FRwheelMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    FLwheelMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    BRwheelMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    BLwheelMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    DriveForward2(0.7,200);
  }


  public void Initialize() {
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
    brat.setTargetPosition(0);
    maniera.setTargetPosition(0);
    brat.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    maniera.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    mana.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    FRwheelMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    BRwheelMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    FLwheelMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    mana.setDirection(DcMotorSimple.Direction.FORWARD);
    BLwheelMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    brat.setPower(1);
    maniera.setPower(1);
  }
 /* Thread pozBratT = new Thread(() -> {
    brat.setPower(1);
  brat.setTargetPosition(bratWantedPoz);
  });
  public void pozbrat(int wantedPos) {
    bratWantedPoz=wantedPos;
    pozBratT.start();
  }
  Thread pozManieraT = new Thread(() -> {
    maniera.setPower(1);
    maniera.setTargetPosition(manieraWantedPoz);
  });
  public void pozmaniera(int wantedPos){
    manieraWantedPoz=wantedPos;
    pozManieraT.start();
  }*/

  public void ScenarioSt() {
    brat.setTargetPosition(0);
    maniera.setTargetPosition(-1402);
    while(brat.isBusy()&&maniera.isBusy()) sleep(20);
    DriveLeft(1.0, 40,15);
    DriveForward(1.0, 11,15);
    brat.setTargetPosition(40);
    while(brat.isBusy())sleep(10);
    ManaDeschide();
    brat.setTargetPosition(0);

    //while(pozBratT.isAlive())sleep(10);
    DriveBackwards(1.0,16,15);

  }

  public void ScenarioMij() {
    brat.setTargetPosition(100);
    maniera.setTargetPosition(-1402);
    while(brat.isBusy()||maniera.isBusy()) sleep(20);
    DriveLeft(1.0, 40,15);
    DriveForward(1.0, 9,15);
    ManaDeschide();
    DriveBackwards(1.0,14,15);
  }

  public void ScenarioDr(){
    brat.setTargetPosition(110);
    maniera.setTargetPosition(-560);
//    while(brat.isBusy()||maniera.isBusy()) sleep(20);
    DriveLeft(1.0, 40,15);
    DriveForward(1,3,15);
    ManaDeschide();
    DriveBackwards(1.0,8,15);
  }

  public char ScanDucks() {
    if (SensorRatusca.getDistance(DistanceUnit.CM) < 30) {
      DriveLeft(1,22,15);
      return 'M';

    }
    DriveLeft(1,22,15);
    return SensorRatusca.getDistance(DistanceUnit.CM) < 30? 'S':'D';
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
  public void DriveTo2(double speed, double frontRightCM, double backRightCM, double frontLeftCM, double backLeftCM)/**fara encoder*/{

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
  public void DriveForward(double speed, double distanceCM,int Error) {
    DriveTo(speed,distanceCM,distanceCM,distanceCM,distanceCM,Error);
  }
  public void DriveForward2(double speed, double distanceCM) {
    DriveTo2(speed,distanceCM,distanceCM,distanceCM,distanceCM);
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