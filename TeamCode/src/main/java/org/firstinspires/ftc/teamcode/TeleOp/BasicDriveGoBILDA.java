package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Controller.MechanicalDriveBaseGoBILDA;

@TeleOp(name = "GoBILDA drive", group = "drive")
@Disabled
public class BasicDriveGoBILDA extends OpMode
{
    MechanicalDriveBaseGoBILDA mechanicalDriveBaseGoBILDA;

    @Override
    public void init()
    {
        mechanicalDriveBaseGoBILDA = new MechanicalDriveBaseGoBILDA(hardwareMap);
    }

    @Override
    public void loop()
    {
        mechanicalDriveBaseGoBILDA.gamepadController(gamepad1);
        mechanicalDriveBaseGoBILDA.driveBaseTelemetry(telemetry);
    }
}
