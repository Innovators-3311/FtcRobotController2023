package org.firstinspires.ftc.teamcode.TeleOp.TeleOpGoBuilda;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Controller.MecanumDriveBases.MecanumDriveBaseGoBILDA;

@TeleOp(name = "GoBILDA drive", group = "drive")
@Disabled
public class BasicDriveGoBILDA extends OpMode
{
    MecanumDriveBaseGoBILDA mecanumDriveBaseGoBILDA;

    @Override
    public void init()
    {
        mecanumDriveBaseGoBILDA = new MecanumDriveBaseGoBILDA(hardwareMap);
    }

    @Override
    public void loop()
    {
        mecanumDriveBaseGoBILDA.gamepadController(gamepad1);
        mecanumDriveBaseGoBILDA.driveBaseTelemetry(telemetry);
    }
}
