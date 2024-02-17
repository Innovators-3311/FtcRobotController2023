package org.firstinspires.ftc.teamcode.TeleOpFunctions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.Controller.MotorControl;

public class Intake extends MotorControl
{

    //Constructor calls parent constructor using hardcoded input
    public Intake(OpMode opmode)
    {
        super("intake", false, false, opmode, null);
    }

    //Calls all methods and then is called itself in the OpMode loop
    public void IntakeDrive()
    {
        this.simpleDrive();
        this.telemetry();
    }

    private void simpleDrive()
    {
        super.simpleDrive(1, gamepad2.right_bumper, gamepad2.right_trigger > 0.5);
    }

    @Override
    protected void telemetry()
    {
        super.telemetry();
    }
}
