package org.firstinspires.ftc.teamcode.Controller;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class CRServoControl
{
    private CRServo servo;
    private String servoName;

    private HardwareMap hardwareMap;
    private Telemetry telemetry;
    protected Gamepad gamepad1;
    protected Gamepad gamepad2;

    private CRServoControl(OpMode opMode)
    {
        this.hardwareMap = opMode.hardwareMap;
        this.telemetry = opMode.telemetry;
        this.gamepad1 = opMode.gamepad1;
        this.gamepad2 = opMode.gamepad2;
    }

    protected CRServoControl(String CRServoName, OpMode opMode)
    {
        this(opMode);

        try
        {
            this.servoName = CRServoName;
            this.servo = this.hardwareMap.crservo.get(this.servoName);

        }
        catch (IllegalArgumentException e)
        {
            telemetry.addData("Exception:", "%s not found in Hardware Map", servoName);
        }
    }

    protected void CRServoDrive(boolean argument1, boolean argument2)
    {
        if (argument1)
        {
            servo.setPower(1);
        }
        else if (argument2)
        {
            servo.setPower(-1);
        }
        else
        {
            servo.setPower(0);
        }
    }

}
