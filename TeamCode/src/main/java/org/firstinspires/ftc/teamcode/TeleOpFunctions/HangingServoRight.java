package org.firstinspires.ftc.teamcode.TeleOpFunctions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Controller.ServoControl;

public class HangingServoRight extends ServoControl
{
    private boolean flag = false;

    public HangingServoRight(OpMode opMode, ElapsedTime time)
    {
        super("hangingRight", 0, 1, opMode, time);
    }

    public void hangingRightDrive()
    {
        toggleDrive();
        goToCurrentPosition();
    }

    protected void toggleDrive()
    {
        super.toggleDrive(gamepad1.left_bumper, 1, 0);
    }

    protected void goToCurrentPosition()
    {
        if (gamepad1.back || flag)
        {
            flag = true;
            super.goToCurrentPosition();
        }
    }
}
