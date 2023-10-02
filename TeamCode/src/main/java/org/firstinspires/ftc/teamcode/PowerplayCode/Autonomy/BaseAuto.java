package org.firstinspires.ftc.teamcode.PowerplayCode.Autonomy;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.robot.Robot;

import org.firstinspires.ftc.teamcode.PowerplayCode.RobotComponents;
import org.firstinspires.ftc.teamcode.PowerplayCode.RobotParamaters;
import org.firstinspires.ftc.teamcode.PowerplayCode.config;
import org.opencv.core.Point;


public abstract class BaseAuto extends LinearOpMode {
    private int LiftPos=0;
    private int ManaPos=0;
    private int TankPos=0;
    public String Culoare="";
   private double LiftPower=0;
   private double ManaPower=0;
   private double TankPower=0;
    private void AddDataDCM(DcMotor dcMotor,String component){
        telemetry.addData(component,"C:%d,T:%d,P:%.2f",dcMotor.getCurrentPosition(),dcMotor.getTargetPosition(),dcMotor.getPower());

    }
    public Thread MainThread=new Thread(()->{
        while (opModeIsActive()||opModeInInit()){
            AddDataDCM(RobotComponents.FRwheelMotor,"FR");
            AddDataDCM(RobotComponents.BRwheelMotor,"BR");
            AddDataDCM(RobotComponents.FLwheelMotor,"FL");
            AddDataDCM(RobotComponents.BLwheelMotor,"BL");
            telemetry.addData("Color:",Culoare);
            telemetry.update();
            RobotComponents.Lift.setTargetPosition(Math.max(RobotComponents.Lift2.getCurrentPosition()-300,LiftPos));
            RobotComponents.Lift2.setTargetPosition(Math.max(RobotComponents.Lift2.getCurrentPosition()-300,LiftPos));
            RobotComponents.Lift.setPower(LiftPower);
            RobotComponents.Lift2.setPower(LiftPower);
            RobotComponents.Mana.setPower(ManaPower);
            int distanta = Math.abs(RobotComponents.RotireTank.getCurrentPosition() - RobotComponents.RotireTank.getTargetPosition());
            if(distanta < 70)
                RobotComponents.RotireTank.setPower(clamp(0.2, TankPower, distanta/70-1+TankPower));
            else{
                RobotComponents.RotireTank.setPower(TankPower);
            }
            sleep(10);
        }
    });


    public void Init() {
        RobotComponents.Camera = new ColorDetectCamera(new Point(0.32 , 0.01), new Point(0.5, 0.13),hardwareMap);
        RobotComponents.FRwheelMotor = hardwareMap.get(DcMotor.class, config.FRMOTOR);
        RobotComponents.BRwheelMotor = hardwareMap.get(DcMotor.class, config.BRMOTOR);
        RobotComponents.FLwheelMotor = hardwareMap.get(DcMotor.class, config.FLMOTOR);
        RobotComponents.BLwheelMotor = hardwareMap.get(DcMotor.class, config.BLMOTOR);
        RobotComponents.Lift = hardwareMap.get(DcMotor.class, config.LIFT);
        RobotComponents.Lift2 = hardwareMap.get(DcMotor.class, config.LIFT2);
        RobotComponents.RotireTank = hardwareMap.get(DcMotor.class, config.ROTIRE_TANK);
        RobotComponents.Mana=hardwareMap.get(DcMotor.class,config.MANA);
        RobotComponents.Cleste=hardwareMap.get(Servo.class,config.CLESTE);

        RobotComponents.FRwheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RobotComponents.BRwheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RobotComponents.FLwheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RobotComponents.BLwheelMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RobotComponents.Lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RobotComponents.Lift2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RobotComponents.RotireTank.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RobotComponents.Mana.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        RobotComponents.FRwheelMotor.setTargetPosition(0);
        RobotComponents.BRwheelMotor.setTargetPosition(0);
        RobotComponents.FLwheelMotor.setTargetPosition(0);
        RobotComponents.BLwheelMotor.setTargetPosition(0);
        RobotComponents.Lift.setTargetPosition(0);
        RobotComponents.Lift2.setTargetPosition(0);
        RobotComponents.RotireTank.setTargetPosition(0);
        RobotComponents.Mana.setTargetPosition(0);

        RobotComponents.FRwheelMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RobotComponents.BRwheelMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RobotComponents.FLwheelMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RobotComponents.BLwheelMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RobotComponents.Lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RobotComponents.Lift2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RobotComponents.RotireTank.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RobotComponents.Mana.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        RobotComponents.BLwheelMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        RobotComponents.FLwheelMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        RobotComponents.FRwheelMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RobotComponents.BRwheelMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RobotComponents.FLwheelMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RobotComponents.BLwheelMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RobotComponents.RotireTank.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    private int clamp(int min,int max,int val){
        if(min>val)
            return min;
        if(max<val)
            return max;
        return val;
    }
    private double clamp(double min,double max,double val){
        if(min>val)
            return min;
        if(max<val)
            return max;
        return val;
    }
    protected void WaitMotor(DcMotor motor, int error){
        while(MotorBusy(motor, error)){
            sleep(5);
        }
    }
    protected Boolean MotorBusy(DcMotor motor, int error){
        return Math.abs(motor.getCurrentPosition()-motor.getTargetPosition())>error;
    }
    protected boolean Semicomp(int a, int b, int Error) {
        if(a-b>-Error&&a-b<Error)
            return false;
        else
            return true;

    }
    protected void setLiftPos(int x,double power){
        x=(int) clamp(RobotParamaters.liftStart,RobotParamaters.liftEnd,x);
        LiftPos=x;
        LiftPower=power;
        RobotComponents.Lift.setTargetPosition(Math.max(RobotComponents.Lift2.getCurrentPosition()-500,LiftPos));
        RobotComponents.Lift2.setTargetPosition(Math.max(RobotComponents.Lift2.getCurrentPosition()-500,LiftPos));
        RobotComponents.Lift.setPower(LiftPower);
        RobotComponents.Lift2.setPower(LiftPower);
    }
    protected void setRotTankPos(int x,double power){
        x=(int) clamp(RobotParamaters.rotTankStart,RobotParamaters.rotTankEnd,x);
        TankPos=x;
        TankPower=power;
        RobotComponents.RotireTank.setTargetPosition(TankPos);
        RobotComponents.RotireTank.setPower(TankPower);
    }

    protected void setManaPos(int x,double power){
        x=(int) clamp(RobotParamaters.mamaStart,RobotParamaters.manaEnd,x);
        ManaPos=x;
        ManaPower=power;
        RobotComponents.Mana.setPower(ManaPower);
        RobotComponents.Mana.setTargetPosition(ManaPos);
    }
    protected void OpenCleste(){
        RobotComponents.Cleste.setPosition(RobotParamaters.servoOpen);
    }
    protected void CloseCleste(){
        RobotComponents.Cleste.setPosition(RobotParamaters.servoClose);
    }
    private double max4(double x,double y,double z,double t){
        return Math.max(Math.max(x,y),Math.max(z,t));

    }
    public void DriveTo(double speed, double frontRightCM, double backRightCM, double frontLeftCM, double backLeftCM,int Error) {

        if(opModeIsActive()) {
            int maxdist=(int)max4(Math.abs(frontLeftCM),Math.abs(frontRightCM),Math.abs(backLeftCM), Math.abs(backRightCM));
            int frontRightNewPos =RobotComponents.FRwheelMotor.getCurrentPosition() + (int) (frontRightCM * RobotParamaters.countsPerCM);
            int backRightNewPos = RobotComponents.BRwheelMotor.getCurrentPosition() + (int) (backRightCM * RobotParamaters.countsPerCM);
            int frontLeftNewPos = RobotComponents.FLwheelMotor.getCurrentPosition() + (int) (frontLeftCM * RobotParamaters.countsPerCM);
            int backLeftNewPos = RobotComponents.BLwheelMotor.getCurrentPosition() + (int) (backLeftCM * RobotParamaters.countsPerCM);
            RobotComponents.FRwheelMotor.setTargetPosition(frontRightNewPos);
            RobotComponents.BRwheelMotor.setTargetPosition(backRightNewPos);
            RobotComponents.FLwheelMotor.setTargetPosition(frontLeftNewPos);
            RobotComponents.BLwheelMotor.setTargetPosition(backLeftNewPos);

            RobotComponents.FRwheelMotor.setPower(speed * Math.abs(frontRightCM)/maxdist);
            RobotComponents.BRwheelMotor.setPower(speed * Math.abs(backRightCM )/maxdist);
            RobotComponents.FLwheelMotor.setPower(speed * Math.abs(frontLeftCM )/maxdist);
            RobotComponents.BLwheelMotor.setPower(speed * Math.abs(backLeftCM  )/maxdist);

            boolean bool1=true;
            boolean bool2=true;
            boolean bool3=true;
            boolean bool4=true;
            while(  opModeIsActive() &&
                    (
                            (RobotComponents.BLwheelMotor.isBusy()||
                            RobotComponents.FLwheelMotor.isBusy()||
                            RobotComponents.BRwheelMotor.isBusy()||
                            RobotComponents.FRwheelMotor.isBusy())&&opModeIsActive()
                    )) {
            }
            RobotComponents.FRwheelMotor.setPower(0);
            RobotComponents.BRwheelMotor.setPower(0);
            RobotComponents.FLwheelMotor.setPower(0);
            RobotComponents.BLwheelMotor.setPower(0);
        }

    }
    public void DriveForward(double speed, double distanceCM,int Error) {
        DriveTo(speed,distanceCM,distanceCM,distanceCM,distanceCM,Error);
    }
    public void DriveDiag(double speed, double distanceCM,int Error, double degree) {

        double distanceFRBL=Math.cos(Math.toRadians(degree+45));
        double distanceFLBR=Math.cos(Math.toRadians(degree-45));
        double distLen=Math.sqrt(distanceFRBL*distanceFRBL+distanceFLBR*distanceFRBL);
        distanceFLBR/=distLen;
        distanceFRBL/=distLen;
        DriveTo(speed, distanceCM*distanceFRBL , distanceCM*distanceFLBR, distanceCM*distanceFLBR, distanceCM*distanceFRBL, Error);
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
        double distanceCM = RobotParamaters.robotCircumference *arcRatio;
        DriveTo(speed,distanceCM,distanceCM,-distanceCM,-distanceCM,Error);
    }
    public void TurnRight(double speed, double degrees,int Error) {
        double arcRatio=degrees/360;
        double distanceCM = (RobotParamaters.robotCircumference*arcRatio);
        DriveTo(speed,-distanceCM,-distanceCM,distanceCM,distanceCM,Error);

    }

}
