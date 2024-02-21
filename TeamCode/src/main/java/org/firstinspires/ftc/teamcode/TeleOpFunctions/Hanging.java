package org.firstinspires.ftc.teamcode.TeleOpFunctions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Controller.MotorControl;

public class Hanging extends MotorControl
{
    private int upperPosition = 0;
    private int lowerPosition = 0;

    public Hanging(OpMode opMode, ElapsedTime time) {super("hanging", true, true, opMode, time);}

    public void hangingDrive()
    {
        analogControl();
//        toggleDrive();
        telemetry();
    }

    protected void analogControl()
    {
        super.simpleDrive(1, gamepad1.dpad_up, gamepad1.dpad_down);
    }

    protected void toggleDrive()
    {
        super.toggleDrive(gamepad1.left_bumper, upperPosition, lowerPosition);
    }

    @Override
    protected void telemetry()
    {
        super.telemetry();
    }
}
