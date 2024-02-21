package org.firstinspires.ftc.teamcode.TeleOp.FlyingHippo;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.AprilTags.AprilTagMaster;
import org.firstinspires.ftc.teamcode.AprilTags.DriveToTag;
import org.firstinspires.ftc.teamcode.Controller.MecanumDriveBases.MecanumDriveBase;
import org.firstinspires.ftc.teamcode.Controller.MecanumDriveBases.MecanumDriveBaseFlyingHippo;
import org.firstinspires.ftc.teamcode.Controller.MecanumDriveBases.MecanumDriveBaseOldHippo;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.DroneLauncher;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.DronePosition;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.Hanging;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.HangingServoLeft;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.HangingServoRight;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.HippoHead;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.Intake;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.LinerSlide;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.TransferLeft;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.TransferRight;

@TeleOp(name = "TeleOp Lord \n FLY HIPPO", group = "robot")
public class TeleOpLordFlyingHippo extends OpMode
{
    MecanumDriveBaseFlyingHippo mecanumDriveBaseFlyingHippo;
    DriveToTag driveToTag;
    ElapsedTime time;

    LinerSlide linerSlide;
    HippoHead hippoHead;
    Hanging hanging;
    Intake intake;

    TransferRight transferRight;
    TransferLeft transferLeft;
    DronePosition dronePosition;
    DroneLauncher droneLauncher;
    HangingServoLeft hangingServoLeft;
    HangingServoRight hangingServoRight;

    @Override
    public void init()
    {
        time = new ElapsedTime();
        mecanumDriveBaseFlyingHippo = new MecanumDriveBaseFlyingHippo(hardwareMap, true, false, true, false);
//        driveToTag = new DriveToTag(hardwareMap, telemetry, new ElapsedTime(), new ElapsedTime(), new AprilTagMaster(mecanumDriveBaseFlyingHippo, hardwareMap));

        linerSlide = new LinerSlide(this, time);
        hippoHead = new HippoHead(this);
        hanging = new Hanging(this, time);
        intake = new Intake(this);

//        transferRight = new TransferRight(this, time);
//        transferLeft = new TransferLeft(this, time);
        dronePosition = new DronePosition(this);
        droneLauncher = new DroneLauncher(this);
        hangingServoLeft = new HangingServoLeft(this, time);
        hangingServoRight = new HangingServoRight(this, time);
    }

    @Override
    public void loop()
    {
        mecanumDriveBaseFlyingHippo.gamepadController(gamepad1);
//        driveToTag.targetLocator(gamepad1);

        linerSlide.linerSlideDrive();
        hippoHead.heightDrive();
        hanging.hangingDrive();
        intake.IntakeDrive();

//        transferRight.transferDrive();
//        transferLeft.transferDrive();
        droneLauncher.launcherControl();
        dronePosition.PositionControl();
        hangingServoLeft.hangingLeftDrive();
        hangingServoRight.hangingRightDrive();
    }

}
