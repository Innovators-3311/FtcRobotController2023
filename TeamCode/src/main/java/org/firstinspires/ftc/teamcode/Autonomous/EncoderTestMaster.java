package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Controller.MecanumDriveBases.MecanumDriveBaseOldHippo;
import org.firstinspires.ftc.teamcode.IMU.IMUControl;

@Autonomous(name = "EncoderTest", group = "EncoderTest")
@Disabled
public class EncoderTestMaster extends LinearOpMode
{
    IMUControl imuControl;
    MecanumDriveBaseOldHippo mecanumDriveBaseOldHippo;
    final double  COUNTS_PER_INCH = (8192 * 1) / (2 * 3.1415); // 1,303.835747254496

    @Override
    public void runOpMode() throws InterruptedException
    {
        imuControl = new IMUControl(hardwareMap, telemetry);
        mecanumDriveBaseOldHippo = new MecanumDriveBaseOldHippo(hardwareMap);
        waitForStart();

        while (opModeIsActive())
        {
//            mecanumDriveBaseOldHippo.strafeWithEncoders(COUNTS_PER_INCH * 12, true, 0.5);
            sleep(2000);
//            mecanumDriveBaseOldHippo.strafeWithEncoders(COUNTS_PER_INCH * 12, true, 0.5);

        }
    }
}
