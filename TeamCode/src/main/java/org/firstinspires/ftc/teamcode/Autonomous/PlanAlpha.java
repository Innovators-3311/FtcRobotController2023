package org.firstinspires.ftc.teamcode.Autonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import java.io.IOException;

@Autonomous(name = "Plan Alpha", group = "Group3311")

//Formerly BlueStageRight
//Formerly BlueStage
public class PlanAlpha extends AutonomousBase
{

    @Override
    public void runOpMode() throws InterruptedException
    {
        super.runOpMode();

        try
        {
            planAlpha(zone, isBlue);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        int wallTarget = 0;
        if (isBlue == -1)
        {
            wallTarget = 3;
        }

        sleep(1000);
        driveToTag.drive(7, zone.ordinal() + 1 + wallTarget, 5, 1);

    }

    /**
     * Plan Alpha.  You will design different routes based on what intel the other team provides.
     * We don't want to run into their robot, so we need different plans.
     */
    public void planAlpha(SpikeLineEnum zone, int isBlue) throws IOException, InterruptedException
    {
        planPurple(zone, isBlue);

        if(zone == SpikeLineEnum.CENTER_SPIKE){

            //Turn left to go through truss
            driver.rotate2(-90*isBlue, imuControl);

            //Go through truss
            driver.forward(70, 1, 0.8);

            //Strafe to let AprilTag take over
            driver.strafe(20, isBlue, 0.5, imuControl);
            
        }
        else if (zone == SpikeLineEnum.LEFT_SPIKE){

         //Turn to truss
        driver.rotate2(-90 * isBlue, imuControl);

        //Go through truss
        driver.forward(70, 1, 0.6);

        //Strafe to let AprilTag take over
        driver.strafe(18, isBlue, 0.5, imuControl);
        }
        else if (zone == SpikeLineEnum.RIGHT_SPIKE){

            //Turn left to go through truss
            driver.rotate2(-90*isBlue, imuControl);

            //Go through truss
            driver.forward(70, 1, 0.8);

            //Strafe to let AprilTag take over
            if(isBlue == 1) driver.strafe(24, isBlue, 0.5,  imuControl);
            else if(isBlue == -1) driver.strafe(23, isBlue, 0.5, imuControl);

        }
    }

    //This should be in the planAlpha class, since it's the only one that uses it
    public void planPurple(SpikeLineEnum zone, int isBlue) throws IOException, InterruptedException
    {
        //Works only with planAlpha

        //If target is in the center...
        if(zone == SpikeLineEnum.CENTER_SPIKE)
        {
            //Go forward to determine whether object is left/center/right
            driver.forward(27, 1, 0.6);
            //Go forward and place pixel

            //Go backward into position
            driver.forward(22, -1, 0.6);

        }

        //If target is on the left...
        else if(zone == SpikeLineEnum.LEFT_SPIKE)
        {
            //Go forward just enough to turn
            driver.forward(17, 1, 0.6);

            driver.rotate2(45 * isBlue, imuControl);

            //Push pixel into place
            driver.forward(5, 1, 0.6);

            //Go backward after placing pixel
            driver.forward(5, -1, 0.6);

            //Adjust (right)
            driver.rotate2(-45*isBlue, imuControl);

            //Go backward into position
            driver.forward(17, -1, 0.6);

        }

        else if(zone == SpikeLineEnum.RIGHT_SPIKE)
        {
            //Go forward just enough to turn
            driver.forward(17, 1, 0.6);

            //Turn right to place pixel
            driver.rotate2(-45*isBlue, imuControl);

            //Push pixel into place
            driver.forward(6, 1, 0.6);

            //Go backward after placing pixel
            driver.forward(6, -1, 0.6);

            //Adjust (left)
            driver.rotate2(45 * isBlue, imuControl);

            //Go back
            driver.forward(17, -1, 0.6);
        }

        //Wait for next command...
        sleep(DELAY);

    }
}
