package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.util.Logging;

import java.io.IOException;
@Autonomous(name = "Plan Gamma", group = "auto")
public class PlanGamma extends AutonomousBase
{

    @Override
    public void runOpMode() throws InterruptedException
    {
        try
        {
            Logging.setup();
            Logging.log("Starting Logging for Plan Gamma");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        super.runOpMode();

        try
        {
            planGamma(zone);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


        int wallTarget = 0;
        if (isBlue == -1)
        {
            wallTarget = 3;
        }


        transferRight.autonomousControl(false);
        transferleft.autonomousControl(false);
        transferRight.autonomousControl(true);
        transferleft.autonomousControl(true);

        linerSlideChild.encoderControl(-200, 0.5);

        sleep(2000);
        //driveToTag.drive(7, zone.ordinal() + 1 + wallTarget, 11, 0);
        driveToTag.drive(3, zone.ordinal() + 1 + wallTarget, 7, isBlue == 1 ? 1 : -1);

        transferRight.autonomousControl(false);
        transferleft.autonomousControl(false);


        sleep(1000);
        //Park robot
        try {
            parkRobot(zone, isBlue);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * There is always a plan B.  ;)
     */
    public void planGamma(SpikeLineEnum zone) throws IOException, InterruptedException {

        //If target is in the center...
        if(zone == SpikeLineEnum.CENTER_SPIKE)
        {
            Logging.log("Spike Line is CENTER_SPIKE");
            centerRoute(isBlue);
        }
        //If target is on the left...
        else if(zone == SpikeLineEnum.LEFT_SPIKE)
        {
            Logging.log("Spike Line is LEFT_SPIKE");
            if (isBlue == 1)
            {
                stageRoute(isBlue);
            }
            else if (isBlue == -1)
            {
                wingRoute(isBlue);
            }
        }
        else if (zone == SpikeLineEnum.RIGHT_SPIKE)
        {
            Logging.log("Spike Line is RIGHT_SPIKE");
            if(isBlue == 1)
            {
                wingRoute(isBlue);
            }
            else if  (isBlue == -1)
            {
                stageRoute(isBlue);
            }
        }
    }

    //Left
    public void stageRoute(int isBlue) throws IOException, InterruptedException
    {
        //Go forward just enough to turn
        driver.forward(17, 1, 0.6);
           
        //Turn to place pixel
        driver.rotate2(-45*isBlue, imuControl);

        //Push pixel into place
        driver.forward(4, 1, 0.6);

        //Go backward after placing pixel
        driver.forward(4, -1, 0.6);

        //Adjust (left)
        driver.rotate2(-45*isBlue, imuControl);

        ///driver.forward(7, 1, 0.5);
        driver.forward(23, 1, 0.5);

    }

    public void centerRoute(int isBlue) throws IOException, InterruptedException {

        //Go forward to place pixel
        driver.forward(26, 1, 0.6);

        //Go backward into position
        driver.forward(3, -1, 0.6);

        //Face right (left for blue)
        driver.rotate2(-90 * isBlue,imuControl);

        //Go to AprilTag
        driver.forward(10,1,0.6);

        //Strafe so that camera detects AprilTag 5 (or 2)
        driver.strafe(5, isBlue,1, imuControl);

        //Go forward so that camera detects AprilTag
        driver.forward(3, 1, 0.3);

    }

    public void wingRoute(int isBlue) throws IOException, InterruptedException {
        //Go forward just enough to turn
        driver.forward(17, 1, 0.6);

        //Face right to place pixel
        driver.rotate2(45*isBlue, imuControl);

        //Push pixel into place
        driver.forward(4, 1, 0.6);

        //Go backward after placing pixel
        driver.forward(4, -1, 0.6);

        //Turn around to face backboard
        driver.rotate2(-135*isBlue, imuControl);

        //Drive forward to detect pixel
        driver.forward(23, 1, 0.5);

        //Strafe in front of AprilTag 4 (or 1) so that camera detects it
        driver.strafe(13, isBlue, 0.5, imuControl);

    }
    public void parkRobot(SpikeLineEnum zone, int isBlue) throws IOException, InterruptedException
    {

        double defaultSpeed = 0.6;
        int defaultWaitTime = 5;
        //Added following variables just because
        int red = -1;
        int blue = 1;
        int left = -1;
        int right = 1;
        int forward = 1;
        int backward = -1;


        //Go backward for space
        driver.forward(5, backward, defaultSpeed);

        if(zone == SpikeLineEnum.CENTER_SPIKE)
        {
            //Center
            if(isBlue == blue)
            {
                driver.strafe(26, left, defaultSpeed,imuControl, defaultWaitTime);
            }
            else if(isBlue == red)
            {
                driver.strafe(26, -isBlue, defaultSpeed, imuControl, defaultWaitTime);

            }

        }
        else if(zone == SpikeLineEnum.LEFT_SPIKE)
        {
            //Left
            if(isBlue == blue)
            {
                driver.strafe(19, left, defaultSpeed, imuControl, defaultWaitTime);

            }
            else if(isBlue == red)
            {
                driver.strafe(32.5, -isBlue, defaultSpeed, imuControl, defaultWaitTime);
            }
        }
        else if(zone == SpikeLineEnum.RIGHT_SPIKE)
        {
            //Right
            if(isBlue == blue)
            {
                driver.strafe(30, left, defaultSpeed, imuControl, defaultWaitTime);
            }
            else if (isBlue == red)
            {
                driver.strafe(19, right, defaultSpeed, imuControl, defaultWaitTime);
            }

        }

        driver.forward(14, forward, defaultSpeed);
    }

}
