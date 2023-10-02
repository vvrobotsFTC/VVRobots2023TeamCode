package org.firstinspires.ftc.teamcode.PowerplayCode.Autonomy;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.PowerplayCode.RobotComponents;
import org.firstinspires.ftc.teamcode.PowerplayCode.RobotParamaters;

@Autonomous
public class AutoRight extends BaseAuto {

    @Override
    public void runOpMode(){
        Init();
        CloseCleste();
        telemetry.addLine("Ready");
        DcMotor Mana=RobotComponents.Mana;
        DcMotor Tank=RobotComponents.RotireTank;
        DcMotor Lift1=RobotComponents.Lift;
        DcMotor Lift2=RobotComponents.Lift2;
        telemetry.update();

        waitForStart();
        Culoare=RobotComponents.Camera.GetPredominantColor();
        Culoare=RobotComponents.Camera.GetPredominantColor();
        RobotComponents.Camera.StopCamera();
        MainThread.start();
        setManaPos(350,1);
        DriveDiag(0.5, 172, 10, 0);
        setLiftPos(1860,1);
        setManaPos(530,1);
        WaitMotor(Tank,20);
        WaitMotor(Lift1,40);
        setRotTankPos(-240,0.5);
        WaitMotor(Tank,20);
        sleep(800 );
        OpenCleste();/** con1*/
        sleep(400);
        DriveRight(0.3,19,0);
        int height=350;
        for(int i=1;i<=2;i++) {
            setManaPos(300, 1);
            setLiftPos(height+160, 0.8);
            setRotTankPos(380, 0.6);
            WaitMotor(Lift1, 50);
            WaitMotor(Tank, 40);
            setLiftPos(height, 1);
            sleep(100);
            WaitMotor(Lift1, 30);
            setManaPos(680, 1);
            WaitMotor(Mana, 30);
            sleep(400);
            setManaPos(680, 1);
            WaitMotor(Mana, 30);
            CloseCleste();/**take con*/
            sleep(300);
            setLiftPos(600, 1);
            WaitMotor(Lift1, 60);
            setLiftPos(1860, 1);
            setManaPos(300, 1);
            setRotTankPos(-180, 0.5);
            WaitMotor(Tank, 20);
            WaitMotor(Lift1, 40);
            setManaPos(760, 1);
            WaitMotor(Mana, 50);
            setRotTankPos(-220, 0.5);
            WaitMotor(Tank, 40);
            sleep(800);
            OpenCleste();
            sleep(500);
            height-=40;
        }


        setManaPos(300,1);
        setRotTankPos(-460,0.4);
        sleep(300);
        setLiftPos(0,0.8);
        switch(Culoare){
            case RobotParamaters.GREEN:
                DriveRight(0.4,47,0);
                break;
            case RobotParamaters.BLUE:
                DriveLeft(0.4,24,0);
                break;
            case RobotParamaters.RED:
                DriveLeft(0.4,95,0);
                break;

        }
        setManaPos(220,1);
        setRotTankPos(0,0.5);

        DriveBackwards(0.5,20,0);
        CloseCleste();
        setManaPos(0,1);
        sleep(10000);

    }

}
