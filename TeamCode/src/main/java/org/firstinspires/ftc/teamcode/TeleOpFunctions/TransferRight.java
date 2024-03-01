package org.firstinspires.ftc.teamcode.TeleOpFunctions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Controller.ServoControl;

// Expansion hub servo 2 "transferRight"
public class TransferRight extends ServoControl
{

    public TransferRight(OpMode opMode)
    {
        super("transferRight", 0, 0, opMode);
    }

    public void transferDrive()
    {
        this.toggleDrive();
        this.telemetry();
    }

    private void managePosition()
    {
        driveServo(1, gamepad2.left_bumper || gamepad2.y);
        driveServo(0, gamepad2.left_trigger > 0.5 || gamepad2.a);
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

    protected void toggleDrive()
    {
        lastGamepad2.copy(currentGamepad2);
        currentGamepad2.copy(gamepad2);
        super.toggleDrive(currentGamepad2.left_bumper, lastGamepad2.left_bumper, 1, 0);
    }

    @Override
    protected void telemetry()
    {
        super.telemetry();
    }
}
