package org.firstinspires.ftc.teamcode.TeleOp.OldRobot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.AprilTags.AprilTagMaster;
import org.firstinspires.ftc.teamcode.AprilTags.DriveToTag;
import org.firstinspires.ftc.teamcode.Controller.MecanumDriveBases.MecanumDriveBaseOldHippo;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.DroneLauncher;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.DronePosition;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.HippoHead;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.Intake;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.LinerSlide;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.TransferLeft;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.TransferRight;

@TeleOp(name = "TeleOp Lord\nOLD HIPPO", group = "old robot")
public class TeleOpLordOldHippo extends OpMode
{
    MecanumDriveBaseOldHippo mecanumDriveBaseOldHippo;
    DriveToTag driveToTag;
    ElapsedTime time;

    LinerSlide linerSlide;
    HippoHead hippoHead;
    Intake intake;

    TransferRight transferRight;
    TransferLeft transferleft;
    DronePosition dronePosition;
    DroneLauncher droneLauncher;

    @Override
    public void init()
    {
        time = new ElapsedTime();
        mecanumDriveBaseOldHippo = new MecanumDriveBaseOldHippo(hardwareMap);
        driveToTag = new DriveToTag(hardwareMap, telemetry, new ElapsedTime(), new ElapsedTime(), new AprilTagMaster(mecanumDriveBaseOldHippo, hardwareMap));

        linerSlide = new LinerSlide(this, time);
        hippoHead = new HippoHead(this);
        intake = new Intake(this);

        transferRight = new TransferRight(this, time);
        transferleft = new TransferLeft(this, time);
        dronePosition = new DronePosition(this);
        droneLauncher = new DroneLauncher(this);
    }

    @Override
    public void loop()
    {
        mecanumDriveBaseOldHippo.gamepadController(gamepad1);
        driveToTag.targetLocator(gamepad1);

        linerSlide.linerSlideDrive();
        hippoHead.heightDrive();
        intake.IntakeDrive();

        transferRight.transferDrive();
        transferleft.transferDrive();
        droneLauncher.launcherControl();
        dronePosition.PositionControl();
    }

}
