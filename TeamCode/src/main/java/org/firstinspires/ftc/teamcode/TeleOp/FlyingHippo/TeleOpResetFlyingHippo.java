package org.firstinspires.ftc.teamcode.TeleOp.FlyingHippo;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Controller.MecanumDriveBases.MecanumDriveBaseFlyingHippo;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.Hanging;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.HippoHead;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.LinerSlide;

@TeleOp(name = "reset", group = "robot")
public class TeleOpResetFlyingHippo extends OpMode
{
    MecanumDriveBaseFlyingHippo mecanumDriveBaseFlyingHippo;
    ElapsedTime time;
    LinerSlide linerSlide;
    Hanging hanging;
    HippoHead hippoHead;

    @Override
    public void init()
    {
        time = new ElapsedTime();
        mecanumDriveBaseFlyingHippo = new MecanumDriveBaseFlyingHippo(hardwareMap, true, false, true, false);

        linerSlide = new LinerSlide(this, time);
        hippoHead = new HippoHead(this);
        hanging = new Hanging(this, time);
    }

    @Override
    public void loop()
    {
        linerSlide.linerSlideDriveSimple();
        hippoHead.heightDriveSimple();
        hanging.hangingDrive();
    }
}
