package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Controller.MecanumDriveBases.MecanumDriveBaseOldHippo;
import org.firstinspires.ftc.teamcode.AprilTags.AprilTagMaster;
import org.firstinspires.ftc.teamcode.AprilTags.DriveToTag;
import org.firstinspires.ftc.teamcode.IMU.IMUControl;

@Autonomous(name = "AprilTag Test", group = "apriltag")
@Disabled
public class apriltagtest extends LinearOpMode
{
    IMUControl imuControl;
    MecanumDriveBaseOldHippo mecanumDriveBaseOldHippo;
    AprilTagMaster aprilTagMaster;
    DriveToTag  driveToTag;
    final double  COUNTS_PER_INCH = (8192 * 1) / (2 * 3.1415); // 1,303.835747254496

    @Override
    public void runOpMode() throws InterruptedException
    {
        mecanumDriveBaseOldHippo = new MecanumDriveBaseOldHippo(hardwareMap);
        aprilTagMaster = new AprilTagMaster(mechanicalDriveBase, hardwareMap);
        driveToTag = new DriveToTag(hardwareMap, telemetry, new ElapsedTime(), new ElapsedTime(), aprilTagMaster);
        waitForStart();

        while (opModeIsActive())
        {
        driveToTag.drive(10, 5, 5, -1);

        }
    }
}
