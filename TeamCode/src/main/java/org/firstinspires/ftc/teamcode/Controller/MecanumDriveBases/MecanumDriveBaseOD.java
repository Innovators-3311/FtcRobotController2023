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

}