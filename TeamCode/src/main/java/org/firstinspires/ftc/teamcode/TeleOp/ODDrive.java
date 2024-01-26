package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Controller.MechanicalDriveBase;
import org.firstinspires.ftc.teamcode.Controller.MechanicalDriveBaseOD;

@TeleOp(name = "OD drive", group = "drive")
public class ODDrive extends OpMode
{
    MechanicalDriveBaseOD mechanicalDriveBaseOD;

    @Override
    public void init()
    {
        mechanicalDriveBaseOD = new MechanicalDriveBaseOD(hardwareMap);
        telemetry.addData("Initialized", " Press start");
        telemetry.update();
    }

    @Override
    public void loop()
    {
        mechanicalDriveBaseOD.gamepadController(gamepad1);
        mechanicalDriveBaseOD.driveBaseTelemetry(telemetry);
    }
}
