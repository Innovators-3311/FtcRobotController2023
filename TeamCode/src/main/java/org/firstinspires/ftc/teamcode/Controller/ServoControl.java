package org.firstinspires.ftc.teamcode.Controller;

import android.graphics.Path;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ServoControl
{
    Servo servo;
    private String servoName;
    double minPosition, maxPosition;

    private HardwareMap hardwareMap;
    private Telemetry telemetry;
    protected Gamepad gamepad1;
    protected Gamepad gamepad2;

    private ServoControl(OpMode opMode)
    {
        this.hardwareMap = opMode.hardwareMap;
        this.telemetry = opMode.telemetry;
        this.gamepad1 = opMode.gamepad1;
        this.gamepad2 = opMode.gamepad2;
    }

    protected ServoControl(String servoName, double minPosition, double maxPosition, OpMode opMode)
    {
        this(opMode);

        this.servoName = servoName;
        servo = hardwareMap.servo.get(servoName);

        this.minPosition = minPosition;
        this.maxPosition = maxPosition;
    }

    protected void driveServo(double target)
    {

        target = Range.clip(target, minPosition, maxPosition);
        if (servo.getPosition() != target)
        {
            servo.setPosition(target);
        }
        else
        {
            servo.setPosition(servo.getPosition());
        }

    }

    protected void driveServo(double target, boolean argument)
    {
        if (argument)
        {
            target = Range.clip(target, minPosition, maxPosition);
            if (servo.getPosition() != target)
            {
                servo.setPosition(target);
            }
            else
            {
                servo.setPosition(servo.getPosition());
            }
        }
    }

    protected void driveServo(double target, double argument)
    {
        if (argument > 0.25)
        {
            target = Range.clip(target, minPosition, maxPosition);
            if (servo.getPosition() != target)
            {
                servo.setPosition(target);
            }
            else
            {
                servo.setPosition(servo.getPosition());
            }
        }
    }

    protected void telemetry()
    {
        telemetry.addData(servoName, "minPosition: %.2f\n\tmaxPosition: %.2f", minPosition, maxPosition);
    }

}