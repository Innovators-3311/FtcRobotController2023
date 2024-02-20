package org.firstinspires.ftc.teamcode.Controller.MecanumDriveBases;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class MecanumDriveBaseOD extends MecanumDriveBase
{

    /**
     * Constructor for MecanumDriveBaseOldHippo from the hardware map
     *
     * @param hardwareMap the hardware map
     */
    public MecanumDriveBaseOD(HardwareMap hardwareMap)
    {
        super(hardwareMap, false, true, true, false);
    }

    @Override
    public void gamepadController(Gamepad gamepad)
    {
        super.gamepadController(gamepad);
    }

    @Override
    public void driveMotors(double drive, double turn, double strafe, double speed)
    {
        leftPowerFront  = (drive + turn + strafe);
        rightPowerFront = (drive - turn - strafe);
        leftPowerBack   = (drive + turn - strafe);
        rightPowerBack  = (drive - turn + strafe);
        // This code is awful.
        double maxAbsVal = maxAbsVal(leftPowerFront, leftPowerBack,
                rightPowerFront, rightPowerBack);
        maxAbsVal = Math.max(1.0, maxAbsVal);
        lf.setPower(leftPowerFront/maxAbsVal * speed);
        rf.setPower(rightPowerFront/maxAbsVal * speed);
        lb.setPower(leftPowerBack/maxAbsVal * speed);
        rb.setPower(rightPowerBack/maxAbsVal * speed);
    }
}