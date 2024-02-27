package org.firstinspires.ftc.teamcode.TeleOpFunctions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.Controller.MotorControl;

public class Intake extends MotorControl
{

    //Constructor calls parent constructor using hardcoded input
    public Intake(OpMode opmode, boolean direction)
    {
        super("intake", direction, false, opmode, null);
    }

    //Calls all methods and then is called itself in the OpMode loop
    public void IntakeDrive(TouchSensor touchSensor)
    {
        this.simpleDrive(touchSensor);
        this.telemetry();
    }

    private void simpleDrive(TouchSensor touchSensor)
    {
        super.simpleDrive(1, gamepad2.right_bumper, gamepad2.right_trigger > 0.25);
    }

    @Override
    protected void telemetry()
    {
        super.telemetry();
    }
}
