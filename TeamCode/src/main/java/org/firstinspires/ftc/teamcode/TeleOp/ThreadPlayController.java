package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Controller.MecanumDriveBases.MecanumDriveBaseOldHippo;

@TeleOp(name = "threadcode", group = "threadplay")
@Disabled
public class ThreadPlayController extends OpMode
{
    MecanumDriveBaseOldHippo mecanumDriveBase;
    ColorSensorClass colorSensorClass;

    @Override
    public void init()
    {
        colorSensorClass = new ColorSensorClass(hardwareMap);
        mecanumDriveBase = new MecanumDriveBaseOldHippo(hardwareMap);
        telemetry.addData("Initialized", " Press start");
        telemetry.update();
    }

    @Override
    public void loop()
    {
        mecanumDriveBase.gamepadController(gamepad1);
        mecanumDriveBase.driveBaseTelemetry(telemetry);
    }
}
