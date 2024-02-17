package org.firstinspires.ftc.teamcode.TeleOp.OldRobot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Controller.MecanumDriveBases.MecanumDriveBaseOldHippo;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Basic drive\nOLD HIPPO", group = "old robot")
public class BasicDriveOldHippo extends OpMode
{
    MecanumDriveBaseOldHippo mecanumDriveBaseOldHippo;

    @Override
    public void init()
    {
        mecanumDriveBaseOldHippo = new MecanumDriveBaseOldHippo(hardwareMap);
        telemetry.addData("Initialized", " Press start");
        telemetry.update();
    }

    @Override
    public void loop()
    {
        mecanumDriveBaseOldHippo.gamepadController(gamepad1);
        mecanumDriveBaseOldHippo.driveBaseTelemetry(telemetry);
    }
}
