package org.firstinspires.ftc.teamcode.TeleOpFunctions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
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
        continuousDrive();
    }

    protected void continuousDrive()
    {
        super.continuousDrive(Servo.Direction.FORWARD, gamepad1.left_bumper);
    }
}
