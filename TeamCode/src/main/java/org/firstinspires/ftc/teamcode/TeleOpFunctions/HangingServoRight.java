package org.firstinspires.ftc.teamcode.TeleOpFunctions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Controller.CRServoControl;
import org.firstinspires.ftc.teamcode.Controller.ServoControl;

public class HangingServoRight extends CRServoControl
{

    public HangingServoRight(OpMode opMode)
    {
        super("hangingRight", opMode);
    }

    public void hangingRightDrive()
    {
        CRServoDrive();
    }

    protected void CRServoDrive()
    {
        super.CRServoDrive(gamepad1.dpad_up, gamepad1.dpad_down);
    }
}
