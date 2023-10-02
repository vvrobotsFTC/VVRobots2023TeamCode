package org.firstinspires.ftc.teamcode.PowerplayCode.Autonomy;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.PowerplayCode.RobotComponents;
import org.firstinspires.ftc.teamcode.PowerplayCode.RobotParamaters;

@Autonomous
public class AutoLeft extends BaseAuto {

    @Override
    public void runOpMode() {
        Init();
        CloseCleste();
        telemetry.addLine("Ready");
        DcMotor Mana=RobotComponents.Mana;
        DcMotor Tank=RobotComponents.RotireTank;
        DcMotor Lift1=RobotComponents.Lift;
        telemetry.update();

        waitForStart();
        Culoare=RobotComponents.Camera.GetPredominantColor();
        RobotComponents.Camera.StopCamera();
        MainThread.start();
        setManaPos(350,1);
        DriveDiag(0.5, 172, 10, 0);
        setLiftPos(1860,1);
        setManaPos(530,1);
        WaitMotor(Tank,20);
        WaitMotor(Lift1,30);
        setRotTankPos(260,0.3);
        WaitMotor(Tank,20);
        sleep(800 );
        OpenCleste();/** con1*/
        sleep(400);
        DriveLeft(0.3,19,0);
        int height=350;
        for(int i=1;i<=2;i++) {
            setManaPos(300, 1);
            setLiftPos(height+160, 0.8);
            setRotTankPos(-410, 0.5);
            WaitMotor(Lift1, 50);
            WaitMotor(Tank, 40);
            setLiftPos(height, 1);
            sleep(100);
            WaitMotor(Lift1, 20);

            sleep(400);
            setManaPos(750, 1);
            WaitMotor(Mana, 30);
            CloseCleste();/**take con*/
            sleep(300);
            setLiftPos(600, 1);
            WaitMotor(Lift1, 30);
            setLiftPos(1860, 1);
            setManaPos(300, 1);
            setRotTankPos(180, 0.5);
            WaitMotor(Tank, 20);
            WaitMotor(Lift1, 40);
            setManaPos(760, 1);
            WaitMotor(Mana, 50);
            setRotTankPos(260, 0.3);
            WaitMotor(Tank, 40);
            sleep(800);
            OpenCleste();
            sleep(500);
            height-=40;
        }


        setManaPos(300,1);
        setRotTankPos(460,0.5);
        sleep(300);
        setLiftPos(0,1);
        switch(Culoare){
            case RobotParamaters.RED:
                DriveLeft(0.4,45,0);
                break;
            case RobotParamaters.BLUE:
                DriveRight(0.4,24,0);
                break;
            case RobotParamaters.GREEN:
                DriveRight(0.4,95,0);
                break;

        }
        setManaPos(240,1);
        setRotTankPos(0,0.5);

        DriveBackwards(0.5,20,0);
        //TODO parcarea


    }
}
