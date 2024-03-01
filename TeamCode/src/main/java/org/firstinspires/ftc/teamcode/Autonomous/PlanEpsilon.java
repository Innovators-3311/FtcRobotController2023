package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.util.Logging;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.io.IOException;

@Autonomous(name = "Plan Epsilon", group = "auto")
public class PlanEpsilon extends AutonomousBase{


    @Override
    public void runOpMode() throws InterruptedException
    {
        super.runOpMode();

        try
        {
            planEpsilon(zone, isBlue);
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

        driveToTag.drive(5, zone.ordinal() + 1 + wallTarget, 0, aprilTagOffset);

//        try
//        {
//            driveToTag();
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }

        linerSlideChild.encoderControl(0, 0.5);

        transferRight.autonomousControl(false);
        transferleft.autonomousControl(false);

        sleep(2000);

    }

    void driveToTag() throws IOException, InterruptedException
    {
        AprilTagDetection detection = driveToTag.findTag();
        Logging.log("DiveToTag: x %5.2f, y %5.2f, yawError %5.2f", detection.ftcPose.x, detection.ftcPose.y, detection.ftcPose.yaw);
        sleep(3000);
        driver.rotate2(-detection.ftcPose.yaw, imuControl);
        sleep(3000);
        driver.strafe(detection.ftcPose.x ,-1,0.4, imuControl);
        sleep(3000);
        driver.forward(detection.ftcPose.y -6, 1, 0.3, 3);
    }



    public void planEpsilon(SpikeLineEnum zone, int isBlue) throws IOException, InterruptedException
    {

        //If target is in the center...
        if(zone == SpikeLineEnum.CENTER_SPIKE)
        {
            Logging.log("Spike Line is CENTER_SPIKE");
            centerRoute(isBlue);
            pickUpStack(isBlue, "center");
            strafeToFinish(true, 0, isBlue);


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
            pickUpStack(isBlue, "left");
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
            pickUpStack(isBlue, "right");

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

        //Strafe so that robot doesn't hit truss
        driver.strafe(0.5, isBlue, 0.4, imuControl);

        //Go forward to pick up from stack
        driver.forward(28, 1, 0.4, 3);


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

        //Strafe to side
        driver.strafe(0.5, isBlue, 0.4, imuControl);

        //Go forward for pickUpStack
        driver.forward(27, 1, 0.4);


    }

    public void centerRoute(int isBlue)
    {

            //Go forward and place pixel
            driver.forward(26, 1, 0.6);

            //Go back so that robot lets go of pixel
            driver.forward(4, -1, 0.5);

            //Strafe to left
            driver.strafe(10, isBlue, 0.6, imuControl);

            //Continue to go into position
            driver.forward(25, 1, 0.6);

    }

    public void pickUpStack(int isBlue, String route) throws InterruptedException, IOException
    {

        //RED: left strafe is negative


        if(isBlue == -1)
        {
            //Turn right (back faces stack) to pick up
            driver.rotate2(-90 * isBlue, imuControl);
            if (route.equals("left"))
            {
                //Left Red instance
               //Strafe to adjust
         //      driver.strafe(5, 1, 0.4, imuControl, 2);


                this.heightChild.encoderControl(2000, 0.8);
                sleep(2000);

                //Go backward and intake
                driver.forward(18, -1, 0.5, 2);

                //Intake pixel
                //this.intakeChild.driveTime(1);
            }
            else if (route.equals("right"))
            {
                //Right red instance


//Code not pushed as of end of 2/29 meeting


                this.heightChild.encoderControl(2000, 0.8);
                sleep(2000);

                //Intake pixel
                //this.intakeChild.driveTime(1);

                //Go backward and intake
                driver.forward(13, -1, 0.5, 2);

            }
            else
            {
                //Red center instance

                //Lower head
                this.heightChild.encoderControl(2100, 0.8);
                sleep(2000);

                //Go backward (approach stack)
                driver.forward(6, -1, 0.3, 2);

                //Strafe a bit to position
//                driver.strafe(2, isBlue, 0.3, imuControl);

            }

                //Shared instance among red
                this.intakeChild.driveTime(1);


            //this.heightChild.encoderControl(2200, 0.3);
                this.heightChild.encoderControl(2200, 0.05);

                sleep(4000);
                //Stop intake
                this.intakeChild.driveTime(0);

                //Raise intake
                // this.heightChild.encoderControl(2000, 0.8);


              //  driver.strafe(5, -1, 0.5, imuControl);

        }
        else
        {

            //Blue side instances

            //Turn right
            driver.rotate2(-90 * isBlue, imuControl);


            this.heightChild.encoderControl(2000, 0.8);

            //sleep(2000);

            this.intakeChild.driveTime(1);


            if (route.equals("left"))
            {
                //Go backward
                driver.forward(17, -1, 0.3, 2);
            }
            else if (route.equals("right"))
            {
                //Go backward
                driver.forward(12, -1, 0.3, 2);
            }
            else
            {
                //Go backward
                driver.forward(7, -1, 0.3, 2);
            }

            //Go backward
            //driver.forward(18, -1, 0.3, 4);

            //this.heightChild.encoderControl(2200, 0.3);
            this.heightChild.encoderControl(2200, 0.05);


            sleep(4000);
            this.intakeChild.driveTime(0);
            //Raise intake
            // this.heightChild.encoderControl(2000, 0.8);
        }
    }

    public void strafeToFinish(boolean center, int left, int isBlue)
    {

        /*
        Left rules:
         0 = center (left is false)
         1 = true, meaning left is true
         -1 = right (left is false)

         */

        //Strafe to avoid target and truss
        driver.strafe(2, isBlue, 0.4, imuControl, 5);
        //Drive to other side
        driver.forward(90, 1, 0.7, 8);

        if(center)
        {
            if(isBlue == 1)
            { //Center instance (AprilTag 002/005)
                //Strafe to AprilTag
                driver.strafe(30, -isBlue, 0.5, imuControl);
            }
            else
            {
                driver.strafe(20, -isBlue, 0.5, imuControl);
            }
        }
        else if (left == 1)
        {
            //Strafe to AprilTag
            if (isBlue == 1)
            { //Left blue instance (AprilTag 001)
                driver.strafe(36, -isBlue, 0.5, imuControl);
            } else
            { //Left red instance (AprilTag 004)
                driver.strafe(12, -isBlue, 0.5, imuControl);
            }
        }
        else if(left == -1)
        {
          if(isBlue == 1)
          { //Right blue instance (AprilTag 003)
            //Strafe to AprilTag
            driver.strafe(12, -isBlue, 0.5, imuControl);
          }
          else
          { //Right red instance (AprilTag 006)
            driver.strafe(20, -isBlue, 0.5, imuControl);
          }
            
        }
        else
        {
            telemetry.addLine("Value other than 0, 1, or -1 was put in strafeToFinish. Robot will not function.");            
        }
            
        //Lower head
        this.heightChild.encoderControl(2230, 0.4);

    }
    }
