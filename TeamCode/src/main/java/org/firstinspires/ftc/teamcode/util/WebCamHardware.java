package org.firstinspires.ftc.teamcode.util;

import android.util.Size;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.Autonomous.AutonomousBase;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;
import java.util.List;

//Is this class relevant anymore!?

public class WebCamHardware
{

   private static final boolean USE_WEBCAM = true;  // true for webcam, false for phone camera

   private static final String TFOD_MODEL_FILE = "/sdcard/FIRST/tflitemodels/RedBlue11_25.tflite";

   AprilTagProcessor aprilTag; // Used for managing the AprilTag detection process.

   private static final String[] LABELS =
   {
         "Trash Panda"
   };

   /**
    *  Declare OpMode members.
    *  */
   private LinearOpMode mOpMode = null; // gain access to methods in the calling OpMode.


   /**
    * The variable to store our instance of the TensorFlow Object Detection processor.
    */
   private TfodProcessor tfod;

   /**
    * The variable to store our instance of the vision portal.
    */
   private VisionPortal visionPortal;

   public WebCamHardware (LinearOpMode opmode)
   {
      mOpMode = opmode;
   }

   /**
    * Initialize the TensorFlow Object Detection processor.
    */
//   public void initTfod() {
//
//      // Create the TensorFlow processor by using a builder.
//      aprilTag = new AprilTagProcessor.Builder().build();
//      tfod = new TfodProcessor.Builder()
//            .setModelFileName(TFOD_MODEL_FILE)
//            .setModelLabels(LABELS)
//            .setModelAspectRatio(16.0 / 9.0)
//            .build();
//
//      // Create the vision portal by using a builder.
//      VisionPortal.Builder builder = new VisionPortal.Builder();
//
//      // Set the camera (webcam vs. built-in RC phone camera).
//      if (USE_WEBCAM)
//      {
//         builder.setCamera(mOpMode.hardwareMap.get(WebcamName.class, "Top"));
//      }
//      else
//      {
//         builder.setCamera(BuiltinCameraDirection.BACK);
//      }
//
//      // Choose a camera resolution. Not all cameras support all resolutions.
//      builder.setCameraResolution(new Size(640, 480));
//
//      // Set and enable the processor.
//      builder.addProcessor(tfod);
//
//      // Build the Vision Portal, using the above settings.
//      visionPortal = builder.build();
//
//      // Set confidence threshold for TFOD recognitions, at any time.
//      tfod.setMinResultConfidence(0.7f);
//
//      tfod.setClippingMargins(0, 100, 0, 100);
//
//   }   // end method initTfod()

//   public void initAprilTag()
//   {
//      if (visionPortal != null)
//      {
//         visionPortal.close();
//      }
//      aprilTag = new AprilTagProcessor.Builder().build();
//
//      // Create the vision portal by using a builder.
//      VisionPortal.Builder builder = new VisionPortal.Builder();
//
//      // Set the camera (webcam vs. built-in RC phone camera).
//      if (USE_WEBCAM)
//      {
//         builder.setCamera(mOpMode.hardwareMap.get(WebcamName.class, "Top"));
//      }
//      else
//      {
//         builder.setCamera(BuiltinCameraDirection.BACK);
//      }
//
//      // Set and enable the processor.
//      builder.addProcessor(aprilTag);
//
//      // Build the Vision Portal, using the above settings.
//      visionPortal = builder.build();
//
//   }

  // public AprilTagProcessor getAprilTagProcessor()
   //{
     // return aprilTag;
   //}

   /**
    * Add telemetry about TensorFlow Object Detection (TFOD) recognitions.
    */
//   public void telemetryTfod()
//   {
//
//      List<Recognition> currentRecognitions = tfod.getRecognitions();
//      mOpMode.telemetry.addData("# Objects Detected", currentRecognitions.size());
//
//      // Step through the list of recognitions and display info for each one.
//      for (Recognition recognition : currentRecognitions) {
//         double x = (recognition.getLeft() + recognition.getRight()) / 2 ;
//         double y = (recognition.getTop()  + recognition.getBottom()) / 2 ;
//
//         mOpMode.telemetry.addData(""," ");
//         mOpMode.telemetry.addData("Image", "%s (%.0f %% Conf.)", recognition.getLabel(), recognition.getConfidence() * 100);
//         mOpMode.telemetry.addData("- Position", "%.0f / %.0f", x, y);
//         mOpMode.telemetry.addData("- Size", "%.0f x %.0f", recognition.getWidth(), recognition.getHeight());
//      }   // end for() loop
//
//   }   // end method telemetryTfod()


//   public Recognition findObject()
//   {
//      Recognition bestRecognition = null;
//      Recognition currentRecognition = null;
//
//      List<Recognition> currentRecognitions = tfod.getRecognitions();
//      mOpMode.telemetry.addData("# Objects Detected", currentRecognitions.size());
//
//      // Step through the list of recognitions and display info for each one.
//      for (Recognition recognition : currentRecognitions)
//      {
//         double x = (recognition.getLeft() + recognition.getRight()) / 2;
//         double y = (recognition.getTop() + recognition.getBottom()) / 2;
//
//         if (bestRecognition == null)
//         {
//            bestRecognition = recognition;
//         }
//         else
//         {
//            if (bestRecognition.getConfidence() < recognition.getConfidence())
//            {
//               bestRecognition = recognition;
//            }
//         }
//
//         if (!(currentRecognitions.isEmpty()))
//         {
//            mOpMode.telemetry.addData("", " ");
//            mOpMode.telemetry.addData("Image", "%s (%.0f %% Conf.)", bestRecognition.getLabel(), bestRecognition.getConfidence() * 100);
//             x = (bestRecognition.getLeft() + bestRecognition.getRight()) / 2;
//             y = (bestRecognition.getTop() + bestRecognition.getBottom()) / 2;
//            mOpMode.telemetry.addData("- Position", "%.0f / %.0f", x, y);
//            mOpMode.telemetry.addData("- Size", "%.0f x %.0f", bestRecognition.getWidth(), bestRecognition.getHeight());
//
//            return bestRecognition;
//         }
//         else
//         {
//            return null;
//         }
//      }
//      return null;
//   }

//   public AutonomousBase.SpikeLineEnum findTarget(double x)
//   {
//      //This is supposed to find the target's position. (Made more sense than writing plain code.)
//      AutonomousBase.SpikeLineEnum targetPosition = AutonomousBase.SpikeLineEnum.UNKNOWN; //("targetPosition" means "position of the target", not "goal" position)
//
//      int leftMaximum = 220;
//      int centerMinimum = 221;
//      int centerMaximum = 459;
//      int rightMinimum = 460;
//
//      if(x < leftMaximum)
//      {   //Range for left 50-150
//         targetPosition = AutonomousBase.SpikeLineEnum.LEFT_SPIKE;
//
//      }
//      else if(x > centerMinimum && x <= centerMaximum){
//         //Range for the center 200 - 459
//         targetPosition = AutonomousBase.SpikeLineEnum.CENTER_SPIKE;
//      }
//      else if(x >= rightMinimum)
//      {
//         //Range for the right
//         targetPosition = AutonomousBase.SpikeLineEnum.RIGHT_SPIKE;
//      }
//
//      return targetPosition;
//   }

//   public void closeWebcam()
//   {
//      tfod.shutdown();
//   }

}
