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
        driveServo(1, gamepad2.left_bumper || gamepad2.y);
        driveServo(0, gamepad2.left_trigger > 0.25 || gamepad2.a);
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
