package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.AprilTags.AprilTagMaster;
import org.firstinspires.ftc.teamcode.AprilTags.DriveToTag;
import org.firstinspires.ftc.teamcode.AprilTags.InitAprilTags;
import org.firstinspires.ftc.teamcode.Autonomous.AutonomousBase;
import org.firstinspires.ftc.teamcode.Controller.MecanumSynchronousDriver;
import org.firstinspires.ftc.teamcode.Controller.MechanicalDriveBase;
import org.firstinspires.ftc.teamcode.IMU.IMUControl;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.HeightChild;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.LinerSlideChild;
import org.firstinspires.ftc.teamcode.util.ImuHardware;
import org.firstinspires.ftc.teamcode.util.Logging;
import org.firstinspires.ftc.teamcode.util.WebCamHardware;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import java.io.IOException;
import java.lang.annotation.ElementType;

@Autonomous(name = "Mez test", group = "Mez")
@Disabled
public class LinearOpModeMez extends LinearOpMode
{
    HeightChild heightChild;
    LinerSlideChild linerSlideChild;

    @Override
    public void runOpMode() throws InterruptedException
    {

        heightChild = new HeightChild(this);
        linerSlideChild = new LinerSlideChild(this);

        waitForStart();

        linerSlideChild.encoderControl(-100, 1);

        Thread.sleep(1000);

    }

}
