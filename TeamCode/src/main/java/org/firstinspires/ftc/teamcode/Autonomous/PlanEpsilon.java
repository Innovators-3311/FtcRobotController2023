package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.util.Logging;

import java.io.IOException;

@Autonomous(name = "Plan Epsilon", group = "auto")
public class PlanEpsilon extends AutonomousBase{


    @Override
    public void runOpMode() throws InterruptedException
    {
        super.runOpMode();

        try
        {
            planBeta(zone, isBlue);
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

        sleep(DELAY);

        //heightChild.encoderControl(0,0.7);

        sleep(1000);

        linerSlideChild.encoderControl(-500, 0.5);

        heightChild.encoderControl(0,0.7);

        driveToTag.drive(5, zone.ordinal() + 1 + wallTarget, 7, aprilTagOffset);

        linerSlideChild.encoderControl(0, 0.5);

        transferRight.autonomousControl(false);
        transferleft.autonomousControl(false);

        sleep(2000);

    }

    /**
     * There is always a plan B.  ;)
     */
    public void planBeta(SpikeLineEnum zone, int isBlue) throws IOException, InterruptedException
    {

        //If target is in the center...
        if(zone == SpikeLineEnum.CENTER_SPIKE)
        {
            Logging.log("Spike Line is CENTER_SPIKE");
            centerRoute(isBlue);
            pickUpStack(isBlue);
            strafeToFinish(true, 0, isBlue);

            //goThroughTrussAndFinish(true, false, isBlue);
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
            pickUpStack(isBlue);
            strafeToFinish(false, 1, isBlue);
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


            strafeToFinish(false, -1, isBlue);
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
        driver.forward(4, -1, 0.6);

        //Adjust (right)
        driver.rotate2(-45 * isBlue, imuControl);

        //Drive forward (meant to go through the middle of the truss)
        driver.forward(27, 1, 0.7);  // 26
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

        //Go to the middle
        driver.forward(25.5, 1, 0.8);
    }

    public void centerRoute(int isBlue)
    {
        //Go forward and place pixel
        driver.forward(26, 1, 0.6);

        //Go back so that robot lets go of pixel
        driver.forward(4, -1, 0.5);

        //Strafe to left
        driver.strafe(10, isBlue, 0.6, imuControl);


        //Turn left


        //Continue to go into position
        driver.forward(25, 1, 0.6);

    }

    public void pickUpStack(int isBlue) throws InterruptedException, IOException
    {



            //Turn right
            driver.rotate2(-90 * isBlue, imuControl);


            this.heightChild.encoderControl(2000, 0.8);
            //sleep(2500);

            //Lower intake


            sleep(2000);

            this.intakeChild.driveTime(1);

            //Go backward
            driver.forward(6, -1, 0.3, 4);

            //this.heightChild.encoderControl(2200, 0.3);
            this.heightChild.encoderControl(2200, 0.05);


            //Intake pixel
            //this.intakeChild.driveTime(1,3);

            sleep(6000);
            this.intakeChild.driveTime(0);
            //Raise intake
            // this.heightChild.encoderControl(2000, 0.8);


    }

    public void strafeToFinish(boolean center, int left, int isBlue) {

        /*
        Left rules:
         0 = center (left is false)
         1 = true, meaning left is true
         -1 = right (left is false)

         */



        //Strafe to avoid target and truss
        driver.strafe(2, isBlue, 0.4, imuControl, 5);
        //Drive to other side
        driver.forward(110, 1, 0.4, 5);

        if(center) {
            left = 0;
            //Strafe to AprilTag
            driver.strafe(25, -isBlue, 0.5, imuControl);

        } else if (left == 1){
            //Strafe to AprilTag
            driver.strafe(18, -isBlue, 0.5, imuControl);

        } else if(left == -1){
            //Strafe to AprilTag
            driver.strafe(36, -isBlue, 0.5, imuControl);

        }

        //Lower head
        this.heightChild.encoderControl(2230, 0.4);



    }
    }
