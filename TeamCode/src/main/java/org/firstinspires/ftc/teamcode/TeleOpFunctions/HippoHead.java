package org.firstinspires.ftc.teamcode.TeleOpFunctions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.TouchSensor;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Controller.MotorControl;

public class HippoHead extends MotorControl
{

    // + for down during auto
    // - for down in TeleOp

    private final int lowerBound = -1500;
    private final int upperBound = -2550;

    TouchSensor touch;
    Telemetry telemetry;

    //Constructor calls parent constructor using hardcoded input
    public HippoHead(OpMode opmode)
    {
        super("hippoHead", false, true, opmode, null);
        touch = opmode.hardwareMap.get(TouchSensor.class, "touchHead");
        telemetry = opmode.telemetry;
    }

    //Calls all methods and then is called itself in the OpMode loop
    public void heightDrive()
    {
        this.resetDrive();
        this.analogControl();
        this.encoderDrive();
        this.telemetry();
    }

    private void analogControl()
    {
        if (!gamepad2.back)
        {
            super.analogControl(1, gamepad2.left_stick_y, false, touch.isPressed(), lowerBound);
            telemetry.addData("Touch sensor", touch.isPressed());
            telemetry.addData("Touch sensor", touch.getValue());
        }

    }

    private void resetDrive()
    {
        if (Math.abs(gamepad2.left_stick_y) == 0)
        {
            super.simpleDrive(-1, gamepad2.back, false);
        }
    }

    private void encoderDrive()
    {
        encoderControl(lowerBound, 1, gamepad2.dpad_down);
        encoderControl(-2000, 1, gamepad2.dpad_up);
    }

    @Override
    public void encoderControl(int target, double speed)
    {
        super.encoderControl(target, speed);
        telemetry();
    }

    public void heightDriveSimple()
    {
        this.analogControlSimple();
        this.telemetry();
    }

    private void analogControlSimple()
    {
        super.analogControl(1, gamepad2.left_stick_y,false);
    }

    @Override
    public void telemetry()
    {
        super.telemetry();
    }

    @Override
    protected int getMotorPosition()
    {
        return super.getMotorPosition();
    }
}
