package org.firstinspires.ftc.teamcode.TeleOp.FlyingHippo;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Controller.MecanumDriveBases.MecanumDriveBaseOldHippo;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.DroneLauncher;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.DronePosition;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.Hanging;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.HippoHead;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.Intake;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.LinerSlide;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.TransferLeft;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.TransferRight;

@TeleOp(name = "Simple TeleOp \nFLY HIPPO", group = "robot")
@Disabled

public class TeleOpSimpleFlyingHippo extends OpMode
{
    MecanumDriveBaseOldHippo mecanumDriveBaseOldHippo;
    ElapsedTime time;

    LinerSlide linerSlide;
    HippoHead hippoHead;
    Hanging hanging;
    Intake intake;

    TransferRight transferRight;
    TransferLeft transferLeft;
    DronePosition dronePosition;
    DroneLauncher droneLauncher;

    @Override
    public void init()
    {
        time = new ElapsedTime();
        mecanumDriveBaseOldHippo = new MecanumDriveBaseOldHippo(hardwareMap);

        linerSlide = new LinerSlide(this, false, null);
        hippoHead = new HippoHead(this, true, linerSlide.getTouchSensor());
        hanging = new Hanging(this, time);
        intake = new Intake(this, false);

        transferRight = new TransferRight(this);
        transferLeft = new TransferLeft(this);
        dronePosition = new DronePosition(this);
        droneLauncher = new DroneLauncher(this);

    }

    @Override
    public void loop()
    {
        mecanumDriveBaseOldHippo.gamepadController(gamepad1);
        linerSlide.linerSlideDriveSimple();
        hippoHead.heightDriveSimple();
        hanging.hangingDrive();
        intake.IntakeDrive(linerSlide.getTouchSensor());

        transferRight.transferDrive();
        transferLeft.transferDrive();
        droneLauncher.launcherControl();
        dronePosition.PositionControl();

    }
}
