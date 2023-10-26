package org.firstinspires.ftc.teamcode.AprilTags;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

public class DetectAprilTag
{


    private static final boolean USE_WEBCAM = true;  // true for webcam, false for phone camera

    /**
     * The variable to store our instance of the AprilTag processor.
     */
    private AprilTagProcessor aprilTag;

    /**
     * The variable to store our instance of the vision portal.
     */
    private VisionPortal visionPortal;

    public DetectAprilTag(HardwareMap hardwareMap)
    {
//        initAprilTag(hardwareMap);
    }

    // Needs called at the end of OpMode
    public void closeAprilTags()
    {
        visionPortal.close();
    }

    public void detectTags(Telemetry telemetry)
    {
        // Push telemetry to the Driver Station.
        telemetryAprilTag(telemetry);
        telemetry.update();
    }

    /**
     * Add telemetry about AprilTag detections.
     */
    private void telemetryAprilTag(Telemetry telemetry)
    {

        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        telemetry.addData("# AprilTags Detected", currentDetections.size());

        // Step through the list of detections and display info for each one.
        for (AprilTagDetection detection : currentDetections)
        {
            if (detection.metadata != null)
            {
                telemetry.addLine(String.format("\n==== (ID %d) %s", detection.id, detection.metadata.name));
                telemetry.addLine(String.format("XYZ %6.1f %6.1f %6.1f  (inch)", detection.ftcPose.x, detection.ftcPose.y, detection.ftcPose.z));
                telemetry.addLine(String.format("PRY %6.1f %6.1f %6.1f  (deg)", detection.ftcPose.pitch, detection.ftcPose.roll, detection.ftcPose.yaw));
                telemetry.addLine(String.format("RBE %6.1f %6.1f %6.1f  (inch, deg, deg)", detection.ftcPose.range, detection.ftcPose.bearing, detection.ftcPose.elevation));
            }
            else
            {
                telemetry.addLine(String.format("\n==== (ID %d) Unknown", detection.id));
                telemetry.addLine(String.format("Center %6.0f %6.0f   (pixels)", detection.center.x, detection.center.y));
            }
        }   // end for() loop

        // Add "key" information to telemetry
        telemetry.addLine("\nkey:\nXYZ = X (Right), Y (Forward), Z (Up) dist.");
        telemetry.addLine("PRY = Pitch, Roll & Yaw (XYZ Rotation)");
        telemetry.addLine("RBE = Range, Bearing & Elevation");

    }   // end method telemetryAprilTag()

    public double getX()
    {
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        AprilTagDetection detection = currentDetections.get(0);
        return detection.ftcPose.x;
    }

    public double getY()
    {
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        AprilTagDetection detection = currentDetections.get(0);
        return detection.ftcPose.x;
    }

    // Shouldn't need Z

    public double getPitch()
    {
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        AprilTagDetection detection = currentDetections.get(0);
        return detection.ftcPose.pitch;
    }

    public double getRoll()
    {
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        AprilTagDetection detection = currentDetections.get(0);
        return detection.ftcPose.roll;
    }

    public double getYaw()
    {
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        AprilTagDetection detection = currentDetections.get(0);
        return detection.ftcPose.yaw;
    }

    public double getBearing()
    {
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        AprilTagDetection detection = currentDetections.get(0);
        return detection.ftcPose.bearing;
    }

    public double getRange()
    {
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        AprilTagDetection detection = currentDetections.get(0);
        return detection.ftcPose.range;
    }

    public double getElevation()
    {
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        AprilTagDetection detection = currentDetections.get(0);
        return detection.ftcPose.elevation;
    }

    public int getDetectionID()
    {
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        for (AprilTagDetection detection : currentDetections)
        {
            if (detection.metadata != null)
            {
                return detection.id;
            }
        }
        return -1;
    }

    private void initAprilTag(HardwareMap hardwareMap)
    {
        // Create the AprilTag processor the easy way.
        aprilTag = AprilTagProcessor.easyCreateWithDefaults();

        // Create the vision portal the easy way.
        if (USE_WEBCAM)
        {
            visionPortal = VisionPortal.easyCreateWithDefaults(
                    hardwareMap.get(WebcamName.class, "Webcam 1"), aprilTag);
        }
        else
        {
            visionPortal = VisionPortal.easyCreateWithDefaults(
                    BuiltinCameraDirection.BACK, aprilTag);
        }

    }   // end method initAprilTag()

}   // end class