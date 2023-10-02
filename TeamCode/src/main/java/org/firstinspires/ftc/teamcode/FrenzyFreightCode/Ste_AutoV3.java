package org.firstinspires.ftc.teamcode.FrenzyFreightCode;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
@Disabled
//@Autonomous(name="Ste AutoV3 NU IL PORNI ", group="Auto")
public class Ste_AutoV3 extends LinearOpMode {

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
  private void ManaDeschide() {
    mana.setPower(-0.5);
    sleep(10);
    mana.setPower(0);
  }
  @Override
  public void runOpMode() {
    Initialize();
    mana.setPower(0.3);

    waitForStart();
    DriveForward(1,40);
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

    roata.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    FRwheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    FLwheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    BRwheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    BLwheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    brat.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    mana.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    maniera.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    maniera.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    brat.setTargetPosition(0);
    maniera.setTargetPosition(0);

    FRwheelMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    FLwheelMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    BRwheelMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    BLwheelMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    FRwheelMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    FLwheelMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    BRwheelMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    BLwheelMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    brat.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    maniera.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    mana.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    roata.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);



    FRwheelMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    BRwheelMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    FLwheelMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    mana.setDirection(DcMotorSimple.Direction.FORWARD);
    BLwheelMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    brat.setPower(1);
    maniera.setPower(1);
  }
  public void ScenarioSt() {
    brat.setTargetPosition(40);
    maniera.setTargetPosition(-1402);
  }

  public void ScenarioMij() {
    brat.setTargetPosition(100);
    maniera.setTargetPosition(-1402);
  }

  public void ScenarioDr(){
    brat.setTargetPosition(110);
    maniera.setTargetPosition(-660);
  }

  public boolean Semicomp(int a, int b, int Error) {
    if(a-b>-Error&&a-b<Error)
      return false;
    else
      return true;

  }
  public void DriveTo(double speed, double frontRightCM, double backRightCM, double frontLeftCM, double backLeftCM) {

    if(opModeIsActive()) {

      int FRNewPos = FRwheelMotor.getCurrentPosition() + (int) (frontRightCM * countsPerCM);
      int BRNewPos = BRwheelMotor.getCurrentPosition() + (int) (backRightCM * countsPerCM);
      int FLNewPos = FLwheelMotor.getCurrentPosition() + (int) (frontLeftCM * countsPerCM);
      int BLNewPos = BLwheelMotor.getCurrentPosition() + (int) (backLeftCM * countsPerCM);

        int FRdir=FRwheelMotor.getCurrentPosition()>FRNewPos?-1:1;
        int BRdir=BRwheelMotor.getCurrentPosition()>BRNewPos?-1:1;
        int FLdir=FLwheelMotor.getCurrentPosition()>FLNewPos?-1:1;
        int BLdir=BLwheelMotor.getCurrentPosition()>BLNewPos?-1:1;
      FRwheelMotor.setPower(speed*FRdir);
      BRwheelMotor.setPower(speed*BRdir);
      FLwheelMotor.setPower(speed*FLdir);
      BLwheelMotor.setPower(speed*BLdir);
      while(  opModeIsActive()&&(
              (FRwheelMotor.getCurrentPosition()-FRNewPos)* FRdir<0||
              (BRwheelMotor.getCurrentPosition()-BRNewPos)* BRdir<0||
              (FLwheelMotor.getCurrentPosition()-FLNewPos)* FLdir<0||
              (BLwheelMotor.getCurrentPosition()-BLNewPos)* BLdir<0));
    FRwheelMotor.setPower(0);
    BRwheelMotor.setPower(0);
    FLwheelMotor.setPower(0);
    BLwheelMotor.setPower(0);
    }
  }

  public void DriveForward(double speed, double distanceCM) {
    DriveTo(speed,distanceCM,distanceCM,distanceCM,distanceCM);
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