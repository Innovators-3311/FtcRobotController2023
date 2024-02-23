package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.AprilTags.AprilTagMaster;
import org.firstinspires.ftc.teamcode.AprilTags.DriveToTag;
import org.firstinspires.ftc.teamcode.Controller.MechanicalDriveBase;
import org.firstinspires.ftc.teamcode.IMU.IMUControl;

@Autonomous(name = "AprilTag Test", group = "apriltag")
public class apriltagtest extends LinearOpMode
{
    IMUControl imuControl;
    MechanicalDriveBase mechanicalDriveBase;
    AprilTagMaster aprilTagMaster;
    DriveToTag  driveToTag;
    final double  COUNTS_PER_INCH = (8192 * 1) / (2 * 3.1415); // 1,303.835747254496

    @Override
    public void runOpMode() throws InterruptedException
    {
        mechanicalDriveBase = new MechanicalDriveBase(hardwareMap);
        aprilTagMaster = new AprilTagMaster(mechanicalDriveBase, hardwareMap);
        driveToTag = new DriveToTag(hardwareMap, telemetry, new ElapsedTime(), new ElapsedTime(), aprilTagMaster);
        waitForStart();

        driveToTag.drive(10, 5, 5, -0.4);
//        while (opModeIsActive())
//        {
//            aprilTagMaster.tagsTelemetry(telemetry);
//        }

    }
}
