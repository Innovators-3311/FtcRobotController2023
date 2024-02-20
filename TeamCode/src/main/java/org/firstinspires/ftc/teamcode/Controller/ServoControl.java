package org.firstinspires.ftc.teamcode.Controller;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class ServoControl
{
    private Servo servo;
    private String servoName;
    double minPosition, maxPosition;

    private HardwareMap hardwareMap;
    private Telemetry telemetry;
    protected Gamepad gamepad1;
    protected Gamepad gamepad2;

    ElapsedTime time;
    private int numberOfPosition = 1;
    private double lastChanged = 0;


    private ServoControl(OpMode opMode, ElapsedTime time)
    {
        this.time = time;
        this.hardwareMap = opMode.hardwareMap;
        this.telemetry = opMode.telemetry;
        this.gamepad1 = opMode.gamepad1;
        this.gamepad2 = opMode.gamepad2;
    }

    protected ServoControl(String servoName, double minPosition, double maxPosition, OpMode opMode, ElapsedTime time)
    {
        this(opMode, time);

        try
        {
            this.servoName = servoName;
            servo = hardwareMap.servo.get(servoName);

            this.minPosition = minPosition;
            this.maxPosition = maxPosition;
        }
        catch (IllegalArgumentException e)
        {
            telemetry.addData("Exception:", "%s not found in Hardware Map",servoName);
            telemetry.update();
        }

    }

    protected void toggleDrive(boolean argument, double target1, double target2)
    {
        if (time.seconds() == 0)
        {
            time.startTime();
        }

        if (argument && numberOfPosition == 1 && (lastChanged + 0.25) < time.seconds())
        {
            lastChanged = time.seconds();
            numberOfPosition = 2;
            driveServo(target1);
        }
        else if (argument && numberOfPosition == 2 && (lastChanged + 0.25) < time.seconds())
        {
            lastChanged = time.seconds();
            numberOfPosition = 1;
            driveServo(target2);
        }
        else
        {
            telemetry.addData("ToggleDrive", "Something went wrong in toggleDrive");
        }
    }

    protected void driveServo(double target)
    {
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
            driveServo(target);
        }
    }

    protected void goToCurrentPosition()
    {
        servo.setPosition(servo.getPosition());
    }


    protected void telemetry()
    {
        telemetry.addData(servoName, "minPosition: %.2f\n" +
                "\tmaxPosition: %.2f", minPosition, maxPosition);
    }

}
