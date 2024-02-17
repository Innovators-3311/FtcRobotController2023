package org.firstinspires.ftc.teamcode.TeleOp.TeleOpGoBuilda;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Controller.MecanumDriveBases.MecanumDriveBaseOD;

@TeleOp(name = "OD drive", group = "drive")
@Disabled
public class ODDrive extends OpMode
{
    MecanumDriveBaseOD mecanumDriveBaseOD;

    @Override
    public void init()
    {
        mecanumDriveBaseOD = new MecanumDriveBaseOD(hardwareMap);
        telemetry.addData("Initialized", " Press start");
        telemetry.update();
    }

    @Override
    public void loop()
    {
        mecanumDriveBaseOD.gamepadController(gamepad1);
        mecanumDriveBaseOD.driveBaseTelemetry(telemetry);
    }
}
