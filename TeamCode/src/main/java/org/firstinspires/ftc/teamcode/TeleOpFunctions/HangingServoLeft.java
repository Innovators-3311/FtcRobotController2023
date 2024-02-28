package org.firstinspires.ftc.teamcode.TeleOpFunctions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Controller.CRServoControl;
import org.firstinspires.ftc.teamcode.Controller.ServoControl;

public class HangingServoLeft extends CRServoControl
{

    public HangingServoLeft(OpMode opMode)
    {
        super("hangingLeft", opMode);
    }

    public void hangingLeftDrive()
    {
        CRServoDrive();
    }

    protected void CRServoDrive()
    {
        super.CRServoDrive(gamepad2.dpad_down, gamepad2.dpad_up);
    }
}
