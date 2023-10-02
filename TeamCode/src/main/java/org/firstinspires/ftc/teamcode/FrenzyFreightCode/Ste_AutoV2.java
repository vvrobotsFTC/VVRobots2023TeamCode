package org.firstinspires.ftc.teamcode.FrenzyFreightCode;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Disabled
public class Ste_AutoV2 extends LinearOpMode {

  private final double diameter = 9.6;
  private final double countsPerMotorRev = 537.5;
  private final double gearRed = 1;
  private final double circumference = Math.PI*diameter;///30.15
  private final double actualCountsPerRev = countsPerMotorRev * gearRed;///537.5
  private final double countsPerCM = actualCountsPerRev / circumference;//17,8
  private final double robotLength = 30*1.33;// 44
  private final double robotWidth = 29*1.33; //32
  private final double robotDiameter = Math.sqrt(robotLength*robotLength+robotWidth*robotWidth);
  private final double robotCircumference = Math.PI*robotDiameter;
  private char DuckPosition='D';
  private DcMotor roata = null;
  private DcMotor Brat = null;
  private DcMotor Ypozition = null;
  private DcMotor Xpozition = null;
  private DcMotor FRwheelMotor = null;
  private DcMotor FLwheelMotor = null;
  private DcMotor BRwheelMotor = null;
  private DcMotor BLwheelMotor = null;
  private boolean bratStrans;
  private Servo RightServo=null;
  private Servo LeftServo;
  private Rev2mDistanceSensor SensorRatusca = null;
  private DigitalChannel Xbutton;
  private DigitalChannel  Ybutton;
  private int bratWantedPoz=0;
  private int manieraWantedPoz=0;

  void open() {

    bratStrans = false;
    while (close.isAlive()) ;
    RightServo.setPosition(0.25);
    LeftServo.setPosition(0.32);
  }

  Thread close = new Thread(() -> {
    bratStrans = true;
    while (bratStrans) {

      RightServo.setPosition(0.15);
      LeftServo.setPosition(0.2);
    }
  });

  void close() {
    close.start();
  }
  Thread Afisare = new Thread(() -> {
    while (opModeIsActive()) {
      telemetry.addData("distanta",SensorRatusca.getDistance(DistanceUnit.CM));
      telemetry.addData("Caz",DuckPosition);
      telemetry.addData("Brat CP",Brat.getCurrentPosition());
      telemetry.addData("Brat WP",Brat.getTargetPosition());
      telemetry.update();
    }
  });
  @Override
  public void runOpMode() {
    Initialize();
    close();
    Ypozition.setTargetPosition(-100);
    Ypozition.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    Ypozition.setPower(1);
    waitForStart();
    Afisare.start();
    Brat.setTargetPosition(-150);
    Brat.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    Brat.setPower(1);
    Xpozition.setTargetPosition(-7000);
    Xpozition.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    Xpozition.setPower(1);
    DriveDiagonalaDreapta(1,38, 15, 0.4);
    for (int i=1; i<=10;i++)
      if(SensorRatusca.getDistance(DistanceUnit.CM)<55)
        DuckPosition='M';
    DriveLeft(1, 25, 15);
    for ( int i = 1; i<=10; i++)
      if(SensorRatusca.getDistance(DistanceUnit.CM)<55)
        DuckPosition='S';
      TurnRight(1,101,15);
    DriveBackwards(1,15,20);
    DriveDiagonalaStanga(1,-39,20,0);
    roata.setPower(-0.55);
    sleep(2800);
    roata.setPower(0);
    DriveLeft(1,6,15);
    switch (DuckPosition){
      case 'M':
        ScenarioMij();
        break;
      case 'D':
        ScenarioDr();
        break;
      case 'S':
        ScenarioSt();
        break;
    }

    }

  public void Initialize() {
    roata=hardwareMap.get(DcMotor.class,"Roata" );
    FRwheelMotor=hardwareMap.get(DcMotor.class,"fataDreapta");
    FLwheelMotor=hardwareMap.get(DcMotor.class,"fataStanga");
    BRwheelMotor=hardwareMap.get(DcMotor.class,"spateDreapta");
    BLwheelMotor=hardwareMap.get(DcMotor.class,"spateStanga");
    Ypozition=hardwareMap.get(DcMotor.class,"Ymotor");
    Xpozition=hardwareMap.get(DcMotor.class,"Xmotor");
    Brat=hardwareMap.get(DcMotor.class,"Brat");
    RightServo=hardwareMap.get(Servo.class,"RightServo");
    LeftServo=hardwareMap.get(Servo.class,"LeftServo");
    Xbutton=hardwareMap.get(DigitalChannel.class,"Xbutton");
    Ybutton=hardwareMap.get(DigitalChannel.class,"Ybutton");
    SensorRatusca = hardwareMap.get(Rev2mDistanceSensor.class, "senzorDistantaRatusca");

    roata.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    FRwheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    FLwheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    BRwheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    BLwheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    Ypozition.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    Xpozition.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    Xbutton.setMode(DigitalChannel.Mode.INPUT);
    Ybutton.setMode(DigitalChannel.Mode.INPUT);
    Brat.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    FRwheelMotor.setTargetPosition(0);
    BLwheelMotor.setTargetPosition(0);
    BRwheelMotor.setTargetPosition(0);
    FLwheelMotor.setTargetPosition(0);

    FRwheelMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
     FLwheelMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
     BRwheelMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
     BLwheelMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    roata.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    Ypozition.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    Xpozition.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    Brat.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    roata.setDirection(DcMotorSimple.Direction.FORWARD);
    FRwheelMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    FLwheelMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    BRwheelMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    BLwheelMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    Ypozition.setDirection(DcMotorSimple.Direction.FORWARD);
    Xpozition.setDirection(DcMotorSimple.Direction.FORWARD);
    Brat.setDirection(DcMotorSimple.Direction.REVERSE);
    RightServo.setDirection(Servo.Direction.FORWARD);
    LeftServo.setDirection(Servo.Direction.REVERSE);
  }


  public void ScenarioDr() {
    Brat.setTargetPosition(-155);
    Ypozition.setTargetPosition(-2700);
    DriveDiagonalaStanga(1, 135, 20,0.55);
    TurnLeft(1,101,20);
    DriveForward(1, 9, 15);
    open();
    DriveBackwards(1,15,15);
    TurnRight(1,101,20);
    DriveRight(1, 45, 20);
    DriveForward2(1,130,20);
  }
  public void ScenarioMij() {
    Brat.setTargetPosition(-155);
    Ypozition.setTargetPosition(-480);
    DriveDiagonalaStanga(1, 135, 20,0.55);
    TurnLeft(1,101,20);
    DriveForward(1, 9, 15);
    open();
    DriveBackwards(1,15,15);
    TurnRight(1,101,20);
    DriveRight(1, 45, 20);
    DriveForward2(1,130,20);
  }
  public void ScenarioSt(){
    Brat.setTargetPosition(0);
    Ypozition.setTargetPosition(-2300);
    DriveDiagonalaStanga(1, 135, 20,0.55);
    TurnLeft(1,101,20);
    DriveForward(1, 4, 15);
    open();
    DriveBackwards(1,13,15);
    TurnRight(1,101,20);
    DriveRight(1, 45, 20);
    DriveForward2(1,130,20);
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
              ));
//      {   telemetry.addData("Destination", "FR: %7d ; BR: %7d ; FL: %7d ; BL: %7d",
//              frontRightNewPos,
//              backRightNewPos,
//              frontLeftNewPos,
//              backLeftNewPos);
//
//        telemetry.addData("Position", "FR: %7d ; BR: %7d ; FL: %7d ; BL: %7d",
//                FRwheelMotor.getCurrentPosition(),
//                BRwheelMotor.getCurrentPosition(),
//                FLwheelMotor.getCurrentPosition(),
//                BLwheelMotor.getCurrentPosition());
//        telemetry.update();}
    FRwheelMotor.setPower(0);
    BRwheelMotor.setPower(0);
    FLwheelMotor.setPower(0);
    BLwheelMotor.setPower(0);
    }
  }
  public void DriveTo2(double speed, double frontRightCM, double backRightCM, double frontLeftCM, double backLeftCM,int Error) {

    if(opModeIsActive()) {

      int frontRightNewPos = FRwheelMotor.getCurrentPosition() + (int) (frontRightCM * countsPerCM);
      int backRightNewPos = BRwheelMotor.getCurrentPosition() + (int) (backRightCM * countsPerCM);
      int frontLeftNewPos = FLwheelMotor.getCurrentPosition() + (int) (frontLeftCM * countsPerCM);
      int backLeftNewPos = BLwheelMotor.getCurrentPosition() + (int) (backLeftCM * countsPerCM);

      FRwheelMotor.setTargetPosition(frontRightNewPos);
      BRwheelMotor.setTargetPosition(backRightNewPos);
      FLwheelMotor.setTargetPosition(frontLeftNewPos);
      BLwheelMotor.setTargetPosition(backLeftNewPos);

      FLwheelMotor.setPower(speed);
      BLwheelMotor.setPower(speed);
      FRwheelMotor.setPower(speed*0.86);
      BRwheelMotor.setPower(speed*0.86);


      while(  opModeIsActive() &&
              (
                      Semicomp(FRwheelMotor.getCurrentPosition(), FRwheelMotor.getTargetPosition(),Error) ||
                              Semicomp(BRwheelMotor.getCurrentPosition(), BRwheelMotor.getTargetPosition(),Error) ||
                              Semicomp(FLwheelMotor.getCurrentPosition(), FLwheelMotor.getTargetPosition(),Error) ||
                              Semicomp(BLwheelMotor.getCurrentPosition(), BLwheelMotor.getTargetPosition(),Error)
              ));
//      {   telemetry.addData("Destination", "FR: %7d ; BR: %7d ; FL: %7d ; BL: %7d",
//              frontRightNewPos,
//              backRightNewPos,
//              frontLeftNewPos,
//              backLeftNewPos);
//
//        telemetry.addData("Position", "FR: %7d ; BR: %7d ; FL: %7d ; BL: %7d",
//                FRwheelMotor.getCurrentPosition(),
//                BRwheelMotor.getCurrentPosition(),
//                FLwheelMotor.getCurrentPosition(),
//                BLwheelMotor.getCurrentPosition());
//        telemetry.update();}
      FRwheelMotor.setPower(0);
      BRwheelMotor.setPower(0);
      FLwheelMotor.setPower(0);
      BLwheelMotor.setPower(0);
    }
  }
  public void DriveForward(double speed, double distanceCM,int Error) {
    DriveTo(speed,distanceCM,distanceCM,distanceCM,distanceCM,Error);
  }
  public void DriveForward2(double speed, double distanceCM,int Error) {
    DriveTo2(speed,distanceCM,distanceCM,distanceCM,distanceCM,Error);
  }
  public void DriveDiagonalaDreapta(double speed, double distanceCM,int Error, double variatie) {
    DriveTo(speed,distanceCM*variatie,distanceCM,distanceCM,distanceCM*variatie,Error);
  } public void DriveDiagonalaStanga(double speed, double distanceCM,int Error, double variatie) {
    DriveTo(speed,distanceCM,distanceCM*variatie,distanceCM*variatie,distanceCM,Error);
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
  public void TurnLeft(double speed, double degrees,int Error) {
    double arcRatio=degrees/360;
    double distanceCM = robotCircumference*arcRatio;
    DriveTo(speed,distanceCM,distanceCM,-distanceCM,-distanceCM,Error);
  }
  public void TurnRight(double speed, double degrees,int Error) {
    double arcRatio=degrees/360;
    double distanceCM = (robotCircumference*arcRatio);
    DriveTo(speed,-distanceCM,-distanceCM,distanceCM,distanceCM,Error);
  }
}