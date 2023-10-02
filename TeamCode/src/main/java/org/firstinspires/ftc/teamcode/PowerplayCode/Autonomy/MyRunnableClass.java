package org.firstinspires.ftc.teamcode.PowerplayCode.Autonomy;

public class MyRunnableClass implements Runnable {

    public int x;
    public double power;
    public MyRunnableClass(int pos,double power) {
        this.x=pos;
        this.power=power;
    }

    public void run() {
    }
}