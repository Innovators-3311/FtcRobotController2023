package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.AprilTags.AprilTagMaster;
import org.firstinspires.ftc.teamcode.AprilTags.DriveToTag;
import org.firstinspires.ftc.teamcode.ColorSwitch.ColorSwitch;
import org.firstinspires.ftc.teamcode.Controller.MecanumSynchronousDriver;
import org.firstinspires.ftc.teamcode.Controller.MechanicalDriveBase;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.HeightChild;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.IntakeChild;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.LinerSlideChild;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.TransferLeft;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.TransferRight;
import org.firstinspires.ftc.teamcode.util.ImuHardware;
import org.firstinspires.ftc.teamcode.util.Logging;
import org.firstinspires.ftc.teamcode.util.WebCamDoubleVision;

import java.io.IOException;

public class AutonomousBase extends LinearOpMode
{
    protected int color;
    public int isBlue = color; //Red is negative!
    public final int DELAY = 250;

    public boolean robotIsMoving = true;


    protected WebCamDoubleVision webcamDouble;

    Recognition rec = null;

    protected ImuHardware imuControl;

    /** Drive control */
    protected MecanumSynchronousDriver driver;
    protected DriveToTag driveToTag;

    protected LinerSlideChild linerSlideChild;
    protected TransferRight transferRight;
    protected TransferLeft transferleft;
    protected HeightChild heightChild;
    protected IntakeChild intakeChild;
    protected ColorSwitch colorSwitch;

    protected int aprilTagOffset;

    SpikeLineEnum zone = SpikeLineEnum.UNKNOWN;

    public enum SpikeLineEnum
    {
        LEFT_SPIKE,
        CENTER_SPIKE,
        RIGHT_SPIKE,
        UNKNOWN
    }

    protected void initMembers()
    {
        try
        {
            driver = new MecanumSynchronousDriver(this.hardwareMap, this);

            imuControl = new ImuHardware(this);

            colorSwitch = new ColorSwitch(hardwareMap);

            webcamDouble = new WebCamDoubleVision(this, colorSwitch.getTeam());

            driveToTag = new DriveToTag(hardwareMap, telemetry, new ElapsedTime(), new ElapsedTime(), new AprilTagMaster(new MechanicalDriveBase(hardwareMap), hardwareMap, webcamDouble.getAprilTag()));


            //Following are all intake or outtake items, mostly on the expansion hub.
            linerSlideChild = new LinerSlideChild(this);
            sleep(DELAY);
            transferRight = new TransferRight(this);
            sleep(DELAY);
            transferleft = new TransferLeft(this);
            sleep(DELAY);
            heightChild = new HeightChild(this);
            sleep(DELAY);
            intakeChild = new IntakeChild(this);
            sleep(DELAY);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void runOpMode() throws InterruptedException
    {

        initMembers();

        isBlue = colorSwitch.getTeam();

        telemetry.addData("isBlue: ", "%d ", isBlue);
        telemetry.update();
        sleep(2000);
        Logging.log("isBlue: " + isBlue);

        aprilTagOffset = aprilTagOffset();

        //TODO: move this to the waitForStart

        waitForStart();

        if (zone == SpikeLineEnum.UNKNOWN)
        {
            Logging.log("No team prop was detected.  Your code sucks.");
            zone = SpikeLineEnum.CENTER_SPIKE;
        }

        //once we start, we should no longer need Tfod.  Should have IDed target by now.
        webcamDouble.disableTfod();

    }

    @Override
    public void waitForStart()
    {
        super.waitForStart();

        //scan for team prop
        rec = webcamDouble.findObject();
        if (rec != null)
        {
            double x = (rec.getLeft() + rec.getRight()) / 2;
            zone = webcamDouble.findTarget(x);
        }
    }

    protected int aprilTagOffset()
    {
        // left
        if (gamepad1.x)
        {
            return 1;
        } // right
        else if (gamepad1.b)
        {
            return -1;
        } // default
        else
        {
            return 0;
        }
    }

}

