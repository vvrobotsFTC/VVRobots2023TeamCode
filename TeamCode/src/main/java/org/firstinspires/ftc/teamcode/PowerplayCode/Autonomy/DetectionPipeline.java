package org.firstinspires.ftc.teamcode.PowerplayCode.Autonomy;

import org.firstinspires.ftc.teamcode.PowerplayCode.RobotParamaters;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class DetectionPipeline extends OpenCvPipeline
{
    public boolean viewportPaused;
    public double red;
    public double green;
    public double blue;
    private Point TopLeft;
    private  Point BottomRight;
    public DetectionPipeline(Point TopLeft,Point BottomRight){
        this.BottomRight=BottomRight;
        this.TopLeft=TopLeft;
    }


    public String getPredominantColor(){

        if(red>green&&red>blue)
            return RobotParamaters.RED;
        if(green>red&&green>blue)
            return RobotParamaters.GREEN;
        return RobotParamaters.BLUE;
    }
    @Override
    public Mat processFrame(Mat input)
    {
        double[] ans={0,0,0};
        int nr=0;
        for(int x=(int)TopLeft.x;x<=BottomRight.x;x+=5){
            for(int y=(int)TopLeft.y;y<=BottomRight.y;y+=5){
                double[] scan =input.get(y ,x);
                if (scan[0] > scan[1] && scan[0] > scan[2]) ans[0]+=255;
                else if (scan[1] > scan[0] && scan[1] > scan[2]) ans[1]+=255;
                else if (scan[2] > scan[0] && scan[2] > scan[1]) ans[2]+=255;

                nr++;
            }
        }
        ans[0]/=nr;
        ans[1]/=nr;
        ans[2]/=nr;
        red=ans[0];
        green=ans[1];
        blue=ans[2];

        Imgproc.rectangle(
                input,
                TopLeft,
                BottomRight,
                new Scalar(ans[0], ans[1], ans[2]), 4);
        return input;
    }

    @Override
    public void onViewportTapped()
    {

    }
}
