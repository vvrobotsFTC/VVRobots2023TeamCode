package org.firstinspires.ftc.teamcode.PowerplayCode;

public class RobotParamaters {
    public static final double diameter = 9.6;
    public static final double countsPerMotorRev = 537.5;
    public static final double gearRed = 1;
    public static final double circumference = Math.PI*diameter;///30.15
    public static final double actualCountsPerRev = countsPerMotorRev * gearRed;///537.5
    public static final double countsPerCM = actualCountsPerRev / circumference;//17,8
    public static final double robotLength = 31*Math.sqrt(2);// 44
    public static final double robotWidth = 36*Math.sqrt(2); //32
    public static final double robotDiameter = Math.sqrt(robotLength*robotLength+robotWidth*robotWidth);
    public static final double robotCircumference = Math.PI*robotDiameter;

    public static final int liftStart=0;
    public static final int liftEnd=1890;
    public static final int rotTankStart=-1560;
    public static final int rotTankEnd=1560;
    public static final int mamaStart=0;
    public static final int manaEnd=750;
    public static final int manaEndDriver=700;
    public static final double servoClose=0;
    public static final double servoOpen=0.7;

    public static final String RED="Rosu";
    public static final String GREEN="Verde";
    public static final String BLUE="Albastru";


}
