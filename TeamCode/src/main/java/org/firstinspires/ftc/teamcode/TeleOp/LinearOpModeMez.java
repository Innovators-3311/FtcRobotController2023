package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.HeightChild;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.LinerSlideChild;

@Autonomous(name = "Mez test", group = "Mez")
@Disabled
public class LinearOpModeMez extends LinearOpMode
{
    HeightChild heightChild;
    LinerSlideChild linerSlideChild;

    @Override
    public void runOpMode() throws InterruptedException
    {

        heightChild = new HeightChild(this);
        linerSlideChild = new LinerSlideChild(this);

        waitForStart();

        linerSlideChild.encoderControl(-100, 1);

        Thread.sleep(1000);

    }

}
