package org.firstinspires.ftc.teamcode.TeleOpFunctions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Controller.MotorControl;

public class LinerSlide extends MotorControl
{
    private final int resetPosition = -50;
    private final int upperPosition = -750;

    TouchSensor touchSensor;

    //Constructor calls parent constructor using hardcoded input
    public LinerSlide(OpMode opMode, boolean direction, ElapsedTime time)
    {
        super("slide", direction, true, opMode, time);
        touchSensor = opMode.hardwareMap.get(TouchSensor.class, "touchLiner");
    }
    //Calls all methods and then is called in the OpMode loop
    public void linerSlideDrive()
    {
        touchSensorOverride();
        if (!gamepad1.b && !gamepad1.x)
        {
            super.encoderControl(0, 1, gamepad1.left_trigger);
            super.encoderControl(-2000, 1, gamepad1.left_bumper);
        }
//        this.analogControl();
        this.telemetry();
    }

    private void analogControl()
    {
        super.analogControl(1, gamepad2.right_stick_y,false);
    }

    @Override
    public void encoderControl(int target, double speed)
    {
        super.encoderControl(target, speed);
    }

    public void linerSlideDriveSimple()
    {
        this.analogControlSimple();
        this.telemetry();
    }

    private void analogControlSimple()
    {
        super.analogControl(1, gamepad2.right_stick_y, false);
    }


    @Override
    protected void telemetry()
    {
        super.telemetry();
    }

    private void touchSensorOverride()
    {
        if (touchSensor.isPressed() && motor.getPower() > 0)
        {
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            motor.setPower(0);
        }
    }

    public TouchSensor getTouchSensor()
    {
        return touchSensor;
    }
}
