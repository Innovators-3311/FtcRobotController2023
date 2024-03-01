package org.firstinspires.ftc.teamcode.TeleOpFunctions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Controller.ServoControl;

// Expansion hub servo 0 "transferLeft"
public class TransferLeft extends ServoControl
{

    public TransferLeft(OpMode opMode)
    {
        super("transferLeft", 0, 0, opMode, null);
    }

    public void transferDrive()
    {
        managePosition();
        telemetry();
    }

    private void managePosition()
    {
        driveServo(1, gamepad1.left_bumper || gamepad2.y);
        driveServo(0, gamepad1.left_trigger > 0.25 || gamepad2.a);
    }

    public void autonomousControl(boolean up)
    {
        if (up)
        {
            driveServo(1);
        }
        else
        {
            driveServo(0);
        }
    }

    @Override
    protected void telemetry()
    {
        super.telemetry();
    }
}
