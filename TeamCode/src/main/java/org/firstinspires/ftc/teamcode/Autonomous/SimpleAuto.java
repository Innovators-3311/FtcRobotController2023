package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.util.Logging;

import java.io.IOException;

@Autonomous(name = "SimpleAuto", group = "auto")
public class SimpleAuto extends AutonomousBase
{
    @Override
    public void runOpMode() throws InterruptedException
    {
        try
        {
            Logging.setup();
            Logging.log("Starting Logging for Plan Gamma");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        super.runOpMode();

        try
        {
            planPurple(zone, isBlue);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
