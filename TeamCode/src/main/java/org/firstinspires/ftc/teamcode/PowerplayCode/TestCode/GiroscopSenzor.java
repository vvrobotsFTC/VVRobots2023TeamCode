package org.firstinspires.ftc.teamcode.PowerplayCode.TestCode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;


public class GiroscopSenzor extends LinearOpMode
{
    private DcMotor rightMotor;
    private DcMotor leftMotor;

    private GyroSensor gyro;

    private int lastRawGyroHeading_ = 0;
    private int gyroHeading_ = 0;


    private int getGyroHeading()
    {
        int rawGyroHeading = gyro.getHeading();

        // Rollover from 0 to 359 detected.
        if (rawGyroHeading - 180 > lastRawGyroHeading_)
        {
            gyroHeading_ =
                    gyroHeading_ + rawGyroHeading - 360 - lastRawGyroHeading_;
        }
        // Roll over from 359 to 0 detected.
        else if (rawGyroHeading + 180 < lastRawGyroHeading_)
        {
            gyroHeading_ =
                    gyroHeading_ + rawGyroHeading + 360 - lastRawGyroHeading_;
        }
        // No rollover detected, add the change in angle to the know value.
        else
        {
            gyroHeading_ =
                    gyroHeading_ + rawGyroHeading - lastRawGyroHeading_;
        }

        lastRawGyroHeading_ = rawGyroHeading;
        return gyroHeading_;
    }



    /**
     * Between turns, you are going to want to reset the gyro heading.
     */
    private void resetGyroHeading()
    {
        lastRawGyroHeading_ = 0;
        gyroHeading_ = 0;
        gyro.resetZAxisIntegrator();
    }

    private void turn(double power, int angle)
    {
        resetGyroHeading();

        if (opModeIsActive())
        {
            // Keeps turning unto the heading that you want is reached.
            while (Math.abs(angle) < getGyroHeading() && opModeIsActive())
            {
                // (angle / Math.abs(angle) is 1 if angle is positive and -1
                // if angle is negative so the power is set in the right
                // direction.

                rightMotor.setPower(power * (angle / Math.abs(angle)));
                leftMotor.setPower(-power * (angle / Math.abs(angle)));
                idle();
                sleep(50);
            }
        }

        rightMotor.setPower(0.0);
        leftMotor.setPower(0.0);
    }

    @Override public void runOpMode() throws InterruptedException
    {
        rightMotor = hardwareMap.dcMotor.get("right_motor");
        leftMotor = hardwareMap.dcMotor.get("left_motor");

        gyro = hardwareMap.gyroSensor.get("gyro");


        gyro.calibrate();

        sleep(1000);
        while(gyro.isCalibrating())
        {
            idle();
            sleep(50);
        }

        waitForStart();

        resetGyroHeading();

        turn(1.0, 90);
        sleep(5000);
        turn(1.0,  -90);
    }

}