//package org.firstinspires.ftc.teamcode.AprilTags;
//
//import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.qualcomm.robotcore.util.ElapsedTime;
//import org.firstinspires.ftc.robotcore.external.Telemetry;
//import org.firstinspires.ftc.teamcode.Controller.MecanumSynchronousDriver;
//import org.firstinspires.ftc.teamcode.util.WebCamHardware;
//import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
//
//public class InitAprilTags
//{
//    AprilTagProcessor aprilTagProcessor;
//    AprilTagMaster aprilTagMaster;
//    DriveToTag driveToTag;
//    ElapsedTime elapsedTime;
//
//    public void initAprilTags(WebCamHardware webcam, MecanumSynchronousDriver driver, HardwareMap hardwareMap, Telemetry telemetry, boolean doubleVision)
//    {
//        elapsedTime = new ElapsedTime();
//        if (!doubleVision)
//        {
//            webcam.initAprilTag();
//            aprilTagProcessor = webcam.getAprilTagProcessor();//initAprilTags.initAprilTags(hardwareMap);
//        }
//
//        aprilTagMaster = new AprilTagMaster(driver, hardwareMap);
//
//        driveToTag = new DriveToTag(hardwareMap, telemetry, elapsedTime, new ElapsedTime(), aprilTagMaster);
//    }
//
//    public AprilTagMaster getAprilTagMaster()
//    {
//        return aprilTagMaster;
//    }
//
//    public DriveToTag getDriveToTag()
//    {
//        return driveToTag;
//    }
//}
