/**Copyright Dobrinel */
package org.firstinspires.ftc.teamcode.FrenzyFreightCode;

//Librarii

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
@Disabled

//@TeleOp(name="Sensor test", group="Linear Opmode")
public class
test_Sensor extends LinearOpMode {
    private ColorRangeSensor colorSensor =null;


    @Override public void runOpMode()    {

        colorSensor = hardwareMap.get(ColorRangeSensor.class, "senzorCuloare");
        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("Culoare R",colorSensor.red());
            telemetry.addData("Culoare G",colorSensor.green());
            telemetry.addData("Culoare B",colorSensor.blue());
            if(colorSensor.red()>=colorSensor.blue()&&colorSensor.red()>=colorSensor.green())
                telemetry.addData("Culoare max","ROSU");
            if(colorSensor.green()>=colorSensor.blue()&&colorSensor.green()>=colorSensor.red())
                telemetry.addData("Culoare max","VERDE");
            if(colorSensor.blue()>=colorSensor.red()&&colorSensor.blue()>=colorSensor.green())
                telemetry.addData("Culoare max","ALBASTRU");
            telemetry.update();


        }
    }
}