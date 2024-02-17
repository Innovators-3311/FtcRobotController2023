package org.firstinspires.ftc.teamcode.TeleOp.OldRobot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Controller.MecanumDriveBases.MecanumDriveBaseOldHippo;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.DroneLauncher;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.DronePosition;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.HippoHead;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.Intake;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.LinerSlide;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.TransferLeft;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.TransferRight;

@TeleOp(name = "Simple TeleOp\nOLD HIPPO", group = "old robot")
public class TeleOpSimpleOldHippo extends OpMode
{
    MecanumDriveBaseOldHippo mecanumDriveBaseOldHippo;
    ElapsedTime time;

    LinerSlide linerSlide;
    HippoHead hippoHead;
    Intake intake;

    TransferRight transferRight;
    TransferLeft transferLeft;
    DronePosition dronePosition;
    DroneLauncher droneLauncher;

    @Override
    public void init()
    {
        mecanumDriveBaseOldHippo = new MecanumDriveBaseOldHippo(hardwareMap);
        linerSlide = new LinerSlide(this, null);
        transferRight = new TransferRight(this, time);
        transferLeft = new TransferLeft(this, time);
        hippoHead = new HippoHead(this);
        intake = new Intake(this);
        dronePosition = new DronePosition(this);
        droneLauncher = new DroneLauncher(this);
    }

    @Override
    public void loop()
    {
        mecanumDriveBaseOldHippo.gamepadController(gamepad1);

        linerSlide.linerSlideDriveSimple();
        hippoHead.heightDriveSimple();
        intake.IntakeDrive();

        transferRight.transferDrive();
        transferLeft.transferDrive();
        droneLauncher.launcherControl();
        dronePosition.PositionControl();
    }
}
