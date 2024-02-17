package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.AprilTags.AprilTagMaster;
import org.firstinspires.ftc.teamcode.AprilTags.DriveToTag;
import org.firstinspires.ftc.teamcode.ColorSwitch.ColorSwitch;
import org.firstinspires.ftc.teamcode.Controller.MecanumDriveBases.MecanumDriveBaseOldHippo;
import org.firstinspires.ftc.teamcode.Controller.MecanumDriveBases.MecanumSynchronousDriver;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.HippoHead;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.Intake;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.LinerSlide;
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

    protected LinerSlide linerSlide;
    protected TransferRight transferRight;
    protected TransferLeft transferleft;
    protected HippoHead hippoHead;
    protected Intake intake;

    protected ColorSwitch colorSwitch;

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

            driveToTag = new DriveToTag(hardwareMap, telemetry, new ElapsedTime(), new ElapsedTime(), new AprilTagMaster(new MecanumDriveBaseOldHippo(hardwareMap), hardwareMap, webcamDouble.getAprilTag()));


            //Following are all intake or outtake items, mostly on the expansion hub.
            linerSlide = new LinerSlide(this, null);
            sleep(DELAY);
            transferRight = new TransferRight(this, null);
            sleep(DELAY);
            transferleft = new TransferLeft(this, null);
            sleep(DELAY);
            hippoHead = new HippoHead(this);
            sleep(DELAY);
            intake = new Intake(this);
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

}

