package org.firstinspires.ftc.teamcode.Autonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import java.io.IOException;

@Autonomous(name = "Plan Alpha", group = "auto")

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
            wallTarget = 3; //originally 3
        }

        transferRight.autonomousControl(false);
        transferleft.autonomousControl(false);
        transferRight.autonomousControl(true);
        transferleft.autonomousControl(true);

        sleep(1000);
        linerSlide.encoderControl(-500, 0.5);


        driveToTag.drive(4, zone.ordinal() + 1 + wallTarget, 7, isBlue == 1 ? 0 : -1);


        hippoHead.encoderControl(0, 0.7);

        sleep(3000);

        transferRight.autonomousControl(false);
        transferleft.autonomousControl(false);

        sleep(3000);
    }

    /**
     * Plan Alpha.  You will design different routes based on what intel the other team provides.
     * We don't want to run into their robot, so we need different plans.
     */
    public void planAlpha(SpikeLineEnum zone, int isBlue) throws IOException, InterruptedException
    {
        planPurple(zone, isBlue);

        if(zone == SpikeLineEnum.CENTER_SPIKE)
        {

            //Turn left to go through truss
            driver.rotate2(-90*isBlue, imuControl);

            //Go through truss
            this.hippoHead.encoderControl(1800, 0.6);

            sleep(2000);

            driver.forward(70, 1, 0.8);

            //Strafe to let AprilTag take over
            driver.strafe(20, isBlue, 0.5, imuControl);
            
        }
        else if (zone == SpikeLineEnum.LEFT_SPIKE)
        {

            //Turn to truss
            driver.rotate2(-90 * isBlue, imuControl);

            this.hippoHead.encoderControl(1800, 0.7);

            sleep(2000);

            //Go through truss
            driver.forward(70, 1, 0.6);

            //Strafe to let AprilTag take over
            if (isBlue == 1)
            {
                driver.strafe(18, isBlue, 0.5, imuControl);
            }
            else
            {
                driver.strafe(28, isBlue, 0.5, imuControl);
            }

        }
        else if (zone == SpikeLineEnum.RIGHT_SPIKE)
        {

            //Turn left to go through truss
            driver.rotate2(-90*isBlue, imuControl);

            this.hippoHead.encoderControl(1800, 0.6);
            sleep(2000);

            //Go through truss
            driver.forward(70, 1, 0.8);

            //Strafe to let AprilTag take over
            if(isBlue == 1) driver.strafe(33, isBlue, 0.5,  imuControl);
            else if(isBlue == -1) driver.strafe(18, isBlue, 0.5, imuControl);


        }

    }


    public void planPurple(SpikeLineEnum zone, int isBlue) throws IOException, InterruptedException
    {

        int forward = 1;
        int backward = -1;

        if(zone == SpikeLineEnum.CENTER_SPIKE)
        {
            //Go forward to determine whether object is left/center/right
            driver.forward(27, forward, 0.6);
            //Go forward and place pixel

            //Go backward into position
            driver.forward(22, backward, 0.6);

        }


        //If target is on the left...
        else if(zone == SpikeLineEnum.LEFT_SPIKE)
        {
            //Go forward just enough to turn
            driver.forward(17, forward, 0.6);

            sleep(DELAY);

            driver.rotate2(-45, imuControl);

            //Push pixel into place
            driver.forward(5, forward, 0.6);

            sleep(DELAY);

            //Go backward after placing pixel
            driver.forward(5, backward, 0.6);
            sleep(DELAY);

            //Adjust (right)
            driver.rotate2(45, imuControl);

            sleep(DELAY);
            //Go backward into position
            driver.forward(17, backward, 0.6);

        }

        else if(zone == SpikeLineEnum.RIGHT_SPIKE)
        {
            //Go forward just enough to turn
            driver.forward(17, forward, 0.6);

            sleep(DELAY);

            driver.rotate2(45, imuControl);

            sleep(DELAY);

            //Push pixel into place
            driver.forward(6, forward, 0.6);

            sleep(DELAY);

            //Go backward after placing pixel
            driver.forward(6, backward, 0.6);

            sleep(DELAY);

            //Adjust (left)
            driver.rotate2(-45, imuControl);

            //Go back
            driver.forward(17, -1, 0.6);
        }

        //Wait for next command...
        sleep(DELAY);

    }

}
