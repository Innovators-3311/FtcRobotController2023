package org.firstinspires.ftc.teamcode.TeleOpFunctions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Controller.ServoControl;

// Expansion hub servo 2 "transferRight"
public class TransferRight extends ServoControl
{

    public TransferRight(OpMode opMode, ElapsedTime time)
    {
        super("transferRight", 0, 0, opMode, time);
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
