
package org.firstinspires.ftc.teamcode.FrenzyFreightCode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
@Disabled

//@Autonomous(name="test stefan", group="Linear Opmode")
//@Disabled
public class stefanIlincaTest extends LinearOpMode
{
    DcMotor         leftMotor;
    DcMotor         rightMotor;
    DigitalChannel  touch;

    // called when init button is  pressed.
    @Override
    public void runOpMode() throws InterruptedException
    {



        // get a reference to our digitalTouch object.
        touch = hardwareMap.get(DigitalChannel.class, "Ybutton");

        // set the digital channel to input.
        touch.setMode(DigitalChannel.Mode.INPUT);

        telemetry.addData("Mode", "waiting");
        telemetry.update();

        // wait for start button.

        waitForStart();
        while(opModeIsActive()) {
            telemetry.addData("Value", touch.getState());
            telemetry.update();
        }
    }
}