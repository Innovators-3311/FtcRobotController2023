package org.firstinspires.ftc.teamcode.AprilTags;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.Logging;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.logging.Logger;


//Further tuning in AprilTagMaster.java to come, I sure of it
public class DriveToTag
{
    HardwareMap hardwareMap;
    Telemetry telemetry;
    ElapsedTime elapsedTime;
    ElapsedTime elapsedTime2;
    AprilTagMaster aprilTagMaster;
    private int target = 1;
    private double lastChanged = 0;

    public DriveToTag(HardwareMap hardwareMap, Telemetry telemetry, ElapsedTime elapsedTime, ElapsedTime elapsedTime2, AprilTagMaster aprilTagMaster)
    {
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;
        this.elapsedTime = elapsedTime;
        this.elapsedTime2 = elapsedTime2;
        this.aprilTagMaster = aprilTagMaster;
    }

    /**
     * @param time The amount of time you want the robot to drive to tag
     * @param target The aprilTag you want to drive to
     * @param allignment - is right + is left
     * **/
    public void drive(int time, int target, double range, double allignment)
    {
        elapsedTime.reset();
        elapsedTime.startTime();
        while (!aprilTagMaster.aprilTagDetected() && elapsedTime.seconds() < time)
        {
            //telemetry.addData("AprilTag", aprilTagMaster.aprilTagDetected());
            //telemetry.update();
        }

        if (aprilTagMaster.aprilTagDetected())
        {
            telemetry.addData("AprilTag detected", "AprilTag");
            while (elapsedTime.seconds() < time)
            {

                telemetry.addData("Time = ",elapsedTime.seconds() + " seconds");
                AprilTagDetection detection = aprilTagMaster.findTag(range, allignment, target, telemetry);
                if (detection != null)
                {
                    Logging.log("ftcPose.xx = %f  range = %f",detection.ftcPose.x, range );
                    if (detection.ftcPose.x == allignment && detection.ftcPose.y <= range && detection.ftcPose.yaw == 0)
                    {
                        Logging.log("exiting drive to tag");
                        break;
                    }
                }
                else
                {
                    Logging.log("Tag lost!!");
                }
                telemetry.update();
            }
        }

        Logging.log("Exiting DriveToTag");

    }

    public void legacyDrive(int time, int target, double range, double allignment)
    {
        elapsedTime.reset();
        elapsedTime.startTime();

        boolean isFound = false;

        while (!aprilTagMaster.aprilTagDetected() && elapsedTime.seconds() < time)
        {
            //telemetry.addData("AprilTag", aprilTagMaster.aprilTagDetected());
            //telemetry.update();
        }

        if (aprilTagMaster.aprilTagDetected())
        {
            telemetry.addData("AprilTag detected", "AprilTag");
            while (elapsedTime.seconds() < time)
            {

                telemetry.addData("Time = ",elapsedTime.seconds() + " seconds");
                //AprilTagDetection detection = aprilTagMaster.findTag(range, allignment, target, telemetry);
                isFound = aprilTagMaster.legacyFindTag(target, range, telemetry);
                if (isFound)
                {

                        Logging.log("exiting drive to tag");
                        break;

                }
                else
                {
                    Logging.log("Tag lost!!");
                }
                telemetry.update();
            }
        }

        Logging.log("Exiting DriveToTag");
    }

    public AprilTagDetection findTag()
    {
        AprilTagDetection detection = aprilTagMaster.findTag();

        return detection;
    }

    public void targetLocator(Gamepad gamepad)
    {
        elapsedTime2.startTime();

        if (gamepad.y && target < 10 && lastChanged < elapsedTime2.seconds())
        {
            lastChanged = elapsedTime2.seconds() + 0.25;
            target++;
        }
        else if (gamepad.a && target > 1 && lastChanged < elapsedTime2.seconds())
        {
            lastChanged = elapsedTime2.seconds() + 0.25;
            target--;
        }
        else if (gamepad.left_trigger > 0.5 || gamepad.left_bumper)
        {
            telemetry.addData("Homing", "");
            aprilTagMaster.findTag(7, 0, target, telemetry);
        }
        telemetry.addData("Current Target", target);
    }

}









