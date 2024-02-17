package org.firstinspires.ftc.teamcode.TeleOpFunctions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Controller.ServoControl;

// Expansion hub servo 0 "transferLeft"
public class TransferLeft extends ServoControl
{

    public TransferLeft(OpMode opMode, ElapsedTime time)
    {
        super("transferLeft", 0, 0, opMode, time);
    }

    public void transferDrive()
    {
        managePosition();
        telemetry();
    }

    private void managePosition()
    {
        toggleDrive(gamepad2.left_trigger < 0.25, 0, 1);
    }

    public void autonomousControl(boolean up)
    {
        if (up)
        {
            driveServo(0);
        }
        else
        {
            driveServo(1);
        }
    }

    @Override
    protected void telemetry()
    {
        super.telemetry();
    }
}
