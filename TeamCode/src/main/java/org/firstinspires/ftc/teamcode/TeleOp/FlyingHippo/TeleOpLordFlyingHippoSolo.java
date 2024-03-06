package org.firstinspires.ftc.teamcode.TeleOp.FlyingHippo;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Controller.MecanumDriveBases.MecanumDriveBaseFlyingHippo;
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

@TeleOp(name = "TeleOp Lord Solo\nFLY HIPPO", group = "robot")
public class TeleOpLordFlyingHippoSolo extends OpMode
{
    MecanumDriveBaseFlyingHippo mecanumDriveBaseFlyingHippo;
//    DriveToTag driveToTag;
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
        mecanumDriveBaseFlyingHippo = new MecanumDriveBaseFlyingHippo(hardwareMap);
//        driveToTag = new DriveToTag(hardwareMap, telemetry, new ElapsedTime(), new ElapsedTime(), new AprilTagMaster(mecanumDriveBaseFlyingHippo, hardwareMap));

        linerSlide = new LinerSlide(this, false, time);
        hippoHead = new HippoHead(this, true, linerSlide.getTouchSensor());
        hanging = new Hanging(this, time);
        intake = new Intake(this, false);


        transferLeft = new TransferLeft(this);
        transferRight = new TransferRight(this);

        dronePosition = new DronePosition(this);
        droneLauncher = new DroneLauncher(this);

        hangingServoLeft = new HangingServoLeft(this);
        hangingServoRight = new HangingServoRight(this);

        telemetry.addData("Hippo Fly Lord", "Ready to go nam nam");
        telemetry.update();
    }

    @Override
    public void loop()
    {
        mecanumDriveBaseFlyingHippo.gamepadController(gamepad1);
//        mecanumDriveBaseFlyingHippo.gamepadController(gamepad2);
//        driveToTag.targetLocator(gamepad1);


        linerSlide.linerSlideDriveSolo();
        hippoHead.heightDrive();
        hanging.hangingDrive();
        intake.IntakeDrive(linerSlide.getTouchSensor());

        transferRight.transferDrive();
        transferLeft.transferDrive();

//        droneLauncher.launcherControl();
//        dronePosition.PositionControl();

        hangingServoLeft.hangingLeftDrive();
        hangingServoRight.hangingRightDrive();
    }

}
