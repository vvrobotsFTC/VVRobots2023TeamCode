package org.firstinspires.ftc.teamcode.PowerplayCode.Autonomy;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.PowerplayCode.RobotComponents;
import org.firstinspires.ftc.teamcode.PowerplayCode.RobotParamaters;

@Autonomous
public class testAuto extends BaseAuto {

    @Override
    public void runOpMode() {
        Init();
        telemetry.addLine("Ready");
        DcMotor Mana=RobotComponents.Mana;
        DcMotor Tank=RobotComponents.RotireTank;
        DcMotor Lift1=RobotComponents.Lift;
        DcMotor Lift2=RobotComponents.Lift2;
        telemetry.update();

        waitForStart();
        RobotComponents.Camera.StopCamera();
        MainThread.start();
        setLiftPos(1860, 1);
        WaitMotor(Lift1,40);
        sleep(300);
        setLiftPos(0,1);
        WaitMotor(Lift1,50);
        sleep(4000);

        //TODO parcarea

    }
}
