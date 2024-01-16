package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.AprilTags.AprilTagMaster;
import org.firstinspires.ftc.teamcode.AprilTags.DriveToTag;
//import org.firstinspires.ftc.teamcode.AprilTags.InitAprilTags;
import org.firstinspires.ftc.teamcode.Autonomous.AutonomousBase;
import org.firstinspires.ftc.teamcode.Controller.MecanumSynchronousDriver;
import org.firstinspires.ftc.teamcode.Controller.MechanicalDriveBase;
import org.firstinspires.ftc.teamcode.IMU.IMUControl;

import org.firstinspires.ftc.teamcode.TeleOpFunctions.HeightChild;
import org.firstinspires.ftc.teamcode.TeleOpFunctions.LinerSlideChild;

@Autonomous(name = "Mez test", group = "Mez")
//@Disabled
public class LinearOpModeMez extends AutonomousBase
{

//    IMUControl imuControl;
    private final double ticksPerInch = (8192 * 1) / (2 * 3.1415); // == 1303
    private final double ticksPerDegree = (ticksPerInch * 50.24) / 360;


    HeightChild heightChild;
    LinerSlideChild linerSlideChild;

    @Override
    public void runOpMode() throws InterruptedException
    {

//        heightChild = new HeightChild(this);
//        linerSlideChild = new LinerSlideChild(this);

//        waitForStart();

//        linerSlideChild.encoderControl(-1000, 0.5);



//        while (opModeIsActive())
//        {
////            heightChild.encoderControl(500, 0.7);
//
//            Thread.sleep(3000);
//        }


        try
        {
            Logging.setup();
            Logging.log("Starting Logging for Mez test");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        super.runOpMode();

//        initAprilTags.initAprilTags(webcam, driver, hardwareMap, telemetry, false);
//        aprilTagMaster = initAprilTags.getAprilTagMaster();
//        driveToTag = initAprilTags.getDriveToTag();

        waitForStart();




//        start();






        while (opModeIsActive())
        {
//            heightChild.encoderControl(10,.3);
//            sleep(3000);
//            heightChild.encoderControl(-10,.3);
//            sleep(3000);

            strafeTest();

        }
    }

//    public void testDeadWheels()
//    {
//        int lfTicks, rfTicks, rbTicks = 0;
//        lfTicks = driver.lf.getCurrentPosition();
//        rfTicks = driver.rf.getCurrentPosition();
//        rbTicks = driver.rb.getCurrentPosition();
//
//        telemetry.addData("testDeadWheels", "lf = " + lfTicks + "\nrf = " +  rfTicks + "\nrb = " + rbTicks);
//        telemetry.update();
//    }

    public void strafeTest()
    {
        driver.strafe(24*2, 1, 0.8, imuControl);
        sleep(3000);
        driver.strafe(24*2, -1, 0.8, imuControl);
        sleep(3000);
    }

    public void driveStraightTest()
    {

        driver.forward(24, 1, .6);
        sleep(3000);
        driver.forward(24 * 4, -1, .6);
        sleep(8000);
        driver.forward(24 * 4, 1, 1);
        sleep(3000);
        driver.forward(24 * 4, -1, 1);

    }

    public void rotateTest() throws InterruptedException, IOException
    {
        double rotateSpeed = 0.4;
//        sleep(3000);
//        driver.turn(30, 1, rotateSpeed);
//        sleep(3000);
//        driver.turn(30, 1, rotateSpeed);
//        sleep(3000);
//        driver.turn(30, 1, rotateSpeed);

//        sleep(2000);
//        driver.rotateOd(360, 1);

//        int startPos = driver.rb.getCurrentPosition();
//        Logging.setup();
//        while (opModeIsActive())
//        {
//            driver.driveMotors(0, .5, 0, 1);
//
//            telemetry.addData("rotateOd2", "startPos:  %d   targetPos: %d ", startPos, driver.rb.getCurrentPosition() - startPos);
//            //mOpMode.telemetry.addData("rotateOd2", "power: %f currPos:  %d", power, this.rb.getCurrentPosition() - startPos);
//            telemetry.update();
//
//            Logging.log("#rotateOd startPos:  %d   Pos: %d ", startPos,driver.rb.getCurrentPosition() - startPos);
//
//        }
//
//        telemetry.addData("rotateOd2", "startPos:  %d   targetPos: %d ", startPos, driver.rb.getCurrentPosition() - startPos);
//        telemetry.update();


//        sleep(2000);
//        driver.rotateOd(180, 0.5);
//        sleep(2000);
//        driver.rotateOd(180, .5);
//        sleep(2000);
//        driver.rotateOd(180, .5);
//        sleep(2000);
//        driver.rotateOd(180, .5);
//        sleep(2000);
//        driver.rotateOd(180, .5);
//        sleep(2000);
//        driver.rotateOd(180, .5);

        sleep(2000);
        driver.rotateOd(45, 0.5);
        sleep(2000);
        driver.rotateOd(45, .5);
        sleep(2000);
        driver.rotateOd(90, .5);
        sleep(2000);
        driver.rotateOd(180, .5);
        sleep(2000);
        //driver.rotateOd(180, .5);
        sleep(2000);
        //driver.rotateOd(-180, .5);



//        sleep(2000);
//        driver.turn(90, -1, rotateSpeed);
//        sleep(2000);
//        driver.turn(90, -1, rotateSpeed);
//        sleep(2000);
//        driver.turn(90, -1, rotateSpeed);
//        sleep(2000);
//        driver.turn(90, -1, rotateSpeed);

        sleep(3000);
//        driver.turn(90, -1, rotateSpeed);

        sleep(3000);
//        driver.turn(180, -1, rotateSpeed);

        sleep(3000);
//        driver.turn(360, 1, rotateSpeed);
    }

    public void aroundyTest()
    {
        double speed = 0.7;
//        driver.forward(12 * 4,1,speed);
//        sleep(100);
//        driver.turn(90, -1, .5);
//        sleep(100);
//        driver.forward(12 * 1.5,1,speed);
//        sleep(100);
//        driver.turn(90, -1, .5);
//        sleep(100);
//        driver.forward(12 * 4,1,speed);
//        sleep(100);
//        driver.turn(90, -1, .5);
//        sleep(100);
//        driver.forward(12 * 1.5,1,speed);
//        sleep(100);
//        driver.turn(90, -1, .5);
    }



}
