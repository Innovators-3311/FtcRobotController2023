package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.util.Logging;
import java.io.IOException;

@Autonomous(name = "plan Delta", group = "auto")
public class PlanDelta extends AutonomousBase
{
    @Override
    public void runOpMode() throws InterruptedException
    {

        super.runOpMode();

        //If target is in the center...
        if(zone == SpikeLineEnum.CENTER_SPIKE)
        {
            centerRoute(isBlue);
        }
        //If target is on the left...
        else if(zone == SpikeLineEnum.LEFT_SPIKE)
        {
            if (isBlue == 1)
            {
                try
                {
                    stageRoute(isBlue);
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            else if (isBlue == -1)
            {
                try
                {
                    wingRoute(isBlue);
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        else if (zone == SpikeLineEnum.RIGHT_SPIKE)
        {
            Logging.log("Spike Line is RIGHT_SPIKE");
            if(isBlue == 1)
            {
                try
                {
                    wingRoute(isBlue);
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            else if  (isBlue == -1)
            {
                try
                {
                    stageRoute(isBlue);
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public void wingRoute(int isBlue) throws IOException, InterruptedException
    {
        //Go forward just enough to turn
        driver.forward(17, 1, 0.6);

        //Turn left to face pixel
        driver.rotate2(45 * isBlue, imuControl);

        //Push pixel into place
        driver.forward(4, 1, 0.6);

        //Go backward after placing pixel
        driver.forward(5, -1, 0.6);

        //Adjust (right)
        driver.rotate2(-45 * isBlue, imuControl);
    }

    public void stageRoute(int isBlue) throws IOException, InterruptedException
    {
        //Go forward
        driver.forward(19, 1, 0.6);

        //Turn (right) to place pixel
        driver.rotate2(-45 * isBlue, imuControl);

        //Place pixel
        driver.forward(5, 1, 0.6);

        //Go back
        driver.forward(5, -1, 0.6);

        //Adjust (left)
        driver.rotate2(45 * isBlue, imuControl);

    }

    public void centerRoute(int isBlue)
    {
        //Go forward and place pixel
        driver.forward(26, 1, 0.6);

        //Go back so that robot lets go of pixel
        driver.forward(4, -1, 0.5);

    }

}
