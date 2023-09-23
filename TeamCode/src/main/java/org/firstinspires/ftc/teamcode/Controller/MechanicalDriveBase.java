package org.firstinspires.ftc.teamcode.Controller;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.IMU.IMUControl;

public class MechanicalDriveBase
{
    public DcMotor lf;
    public DcMotor lb;
    public DcMotor rb;
    public DcMotor rf;

    public double leftPowerFront = 0;
    public double rightPowerFront = 0;
    public double rightPowerBack = 0;
    public double leftPowerBack = 0;
    public double speed = 0;

    final double COUNTS_PER_INCH = (8192 * 1) / (2 * 3.1415); // 1,303.835747254496
    private double heading = 0;

    /**
     * Constructor for MechanicalDriveBase from the hardware map
     *
     * @param hardwareMap the hardware map
     */
    public MechanicalDriveBase(HardwareMap hardwareMap)
    {
        lf = hardwareMap.get(DcMotor.class, "lf");
        lb = hardwareMap.get(DcMotor.class, "lb");
        rb = hardwareMap.get(DcMotor.class, "rb");
        rf = hardwareMap.get(DcMotor.class, "rf");

        lf.setDirection(DcMotor.Direction.FORWARD);
        rf.setDirection(DcMotor.Direction.REVERSE);
        lb.setDirection(DcMotor.Direction.FORWARD);
        rb.setDirection(DcMotor.Direction.REVERSE);

        // Run Without Encoders
        resetRunMode();

        // reset encoders
        resetRunMode();

        // Brake when power set to Zero
        lf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    /**
     * We tend to set all the motor modes at once, so break it out using "extract Method" under the
     * refactor menu
     */
    private void resetRunMode()
    {
        lf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    //make the robot stop
    public void brake()
    {
        lf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    /**
     * Standard controls from a gamepad
     *
     * @param gamepad - the gamepad you want to control the drive base
     */
    public void gamepadController(Gamepad gamepad)
    {
        double drive = -gamepad.left_stick_y;
        double turn = gamepad.right_stick_x;
        double strafe = gamepad.left_stick_x;
        speed = 1 - (0.6 * gamepad.right_trigger);
        driveMotors(drive, turn, strafe, speed);
    }

    /**
     * Drive the motors according to drive, turn, strafe inputs.
     *
     * @param drive  forward / backward (-1 to 1)
     * @param turn   how much to turn left or right (heading) (-1 to 1)
     * @param strafe strafe (left or right = -1 to 1)
     * @param speed  scale factor that is applied to all motor powers (0 to 1)
     */
    public void driveMotors(double drive, double turn, double strafe, double speed)
    {
        leftPowerFront = (drive - turn + strafe);
        rightPowerFront = (drive + turn - strafe);
        leftPowerBack = (drive - turn - strafe);
        rightPowerBack = (drive + turn + strafe);

        // This code is awful.
        double maxAbsVal = maxAbsVal(leftPowerFront, leftPowerBack, rightPowerFront, rightPowerBack);

        maxAbsVal = Math.max(1.0, maxAbsVal);

        lf.setPower(leftPowerFront / maxAbsVal * speed);
        rf.setPower(rightPowerFront / maxAbsVal * speed);
        lb.setPower(leftPowerBack / maxAbsVal * speed);
        rb.setPower(rightPowerBack / maxAbsVal * speed);

    }

    /**
     * Returns the absolute maximum power on any drive motor.
     *
     * @return max abs power [0,1]
     */
    public double maxMotorPower()
    {
        return maxAbsVal(lf.getPower(), rf.getPower(), lb.getPower(), rb.getPower());
    }

    /**
     * maxAbsVal returns the maximum absolute value among an arbitrary number of arguments.
     *
     * @param values an arbitrary number of values.
     * @return the maximum absolute value among the numbers.
     */
    public static double maxAbsVal(double... values)
    {
        double mav = Double.NEGATIVE_INFINITY;
        for (double val : values)
        {
            mav = Math.max(mav, Math.abs(val));
        }
        return mav;
    }

    /**
     * report drivebase telemetry
     *
     * @param telemetry the telemetry object we're reporting to.
     */
    public void driveBaseTelemetry(Telemetry telemetry)
    {
        telemetry.addData("Motors", "lf(%.2f), rf(%.2f), lb(%.2f), rb(%.2f)", leftPowerFront, rightPowerFront, leftPowerBack, rightPowerBack);
        telemetry.addData("Speed control", speed);
    }

    /**
     * ======================================== Autonomous Code ===============================================
     **/
    //lf encoder for left side
    //rf encoder for right side
    //rb for turning and strafing
    //Bore encoders ticks per rotation 8192

      /*
        static final double  COUNTS_PER_INCH =
          (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
          (WHEEL_DIAMETER_INCHES * 3.1415);
          For our drive base 1,303.835747254496 drives an inch
      */

    //                                 How far do you want which way do you  How fast do
    //                                 to drive in inches   want to drive   you want to go
    //                                   positives only   true is forward   positive only
    public void driveForwardWithEncoders(double distance, boolean direction, double speed)
    {
        if (direction)
        {
            distance += Math.abs(lf.getCurrentPosition());
            driveMotors(-1, 0, 0, speed);
            while (Math.abs(lf.getCurrentPosition()) < distance)
            {
            }
            driveMotors(0, 0, 0, 0);
        }
        else
        {
            distance -= Math.abs(lf.getCurrentPosition());
            driveMotors(1, 0, 0, speed);
            while (Math.abs(lf.getCurrentPosition()) > distance)
            {
            }
            driveMotors(0, 0, 0, 0);
        }
    }

    //                               How far do you want  which way do you  How fast do
    //                               to drive in inches    want to drive   you want to go
    //                                  positives only     true is right    positive only
    public void strafeWithEncoders(double distance, boolean direction, double speed, IMUControl imuControl, Telemetry telemetry)
    {
        imuControl.resetAngle();
        double forwardError;
        double strafeError;
        double forward = lf.getCurrentPosition();
        heading = imuControl.getAngle();
        rb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        if (direction)
        {
            distance *= -1;
            while (rb.getCurrentPosition() > distance)
            {
                forwardError = (forward - lf.getCurrentPosition()) * 0.00001;
                strafeError = (heading - imuControl.getAngle()) * 0.025;
                strafe(1, strafeError, forwardError, true, speed);
                telemetry.addData("Strafe Correction", strafeError + "\nEncoder: " + rb.getCurrentPosition());
                telemetry.addData("Forward Correction", forwardError + "\nEncoder: " + lf.getCurrentPosition());
                driveBaseTelemetry(telemetry);
                telemetry.update();
            }
            driveMotors(0, 0, 0, 0);
        }
        else
        {
            while (rb.getCurrentPosition() < distance)
            {
                forwardError = (forward - lf.getCurrentPosition()) * 0.00001;
                strafeError = (heading + imuControl.getAngle()) * 0.025;
                strafe(1, strafeError, forwardError, false, speed);
                telemetry.addData("Forward Correction", forwardError + "\nEncoder: " + lf.getCurrentPosition());
                driveBaseTelemetry(telemetry);
                telemetry.update();
            }
            driveMotors(0, 0, 0, 0);
        }

        try {Thread.sleep(500);}
        catch (InterruptedException e) {e.printStackTrace();}

        resetHeading(heading, imuControl);
        driveMotors(0, 0, 0, 0);

        try {Thread.sleep(500);}
        catch (InterruptedException e) {e.printStackTrace();}

        resetForward(forward);
        driveMotors(0, 0, 0, 0);
    }


    /**
     * Drive the motors according to drive, turn, strafe inputs.
     *
     * @param strafe strafe (left or right = -1 to 1)
     * @param speed  scale factor that is applied to all motor powers (0 to 1)
     */
    private void strafe(double strafe, double strafeError, double forwardError, boolean direction, double speed)
    {
        //TODO values in direction may be wrong (alligators? negatives?)
        if (strafeError > 0 && direction)
        {
            leftPowerFront = strafe;
            rightPowerFront = -strafe;
            leftPowerBack = -(strafe + strafeError);
            rightPowerBack = (strafe + strafeError);
        }
        else if (strafeError < 0 && direction)
        {
            leftPowerFront = (strafe - strafeError);
            rightPowerFront = -(strafe - strafeError);
            leftPowerBack = -strafe;
            rightPowerBack = strafe;
        }
        else if (strafeError > 0 && !direction)
        {
            leftPowerFront = -strafe;
            rightPowerFront = strafe;
            leftPowerBack = (strafe + strafeError);
            rightPowerBack = -(strafe + strafeError);
        }
        else if (strafeError < 0 && !direction)
        {
            leftPowerFront = -(strafe - strafeError);
            rightPowerFront = (strafe - strafeError);
            leftPowerBack = strafe;
            rightPowerBack = -strafe;
        }
        else if (direction)
        {
            leftPowerFront = strafe;
            rightPowerFront = -strafe;
            leftPowerBack = -strafe;
            rightPowerBack = strafe;
        }
        else
        {
            leftPowerFront = -strafe;
            rightPowerFront = strafe;
            leftPowerBack = strafe;
            rightPowerBack = -strafe;
        }

        leftPowerFront += forwardError;
        rightPowerFront += forwardError;
        leftPowerBack += forwardError;
        rightPowerBack += forwardError;


        // This code is awful.
        double maxAbsVal = maxAbsVal(leftPowerFront, leftPowerBack, rightPowerFront, rightPowerBack);

        maxAbsVal = Math.max(1.0, maxAbsVal);

        lf.setPower(leftPowerFront / maxAbsVal * speed);
        rf.setPower(rightPowerFront / maxAbsVal * speed);
        lb.setPower(leftPowerBack / maxAbsVal * speed);
        rb.setPower(rightPowerBack / maxAbsVal * speed);

    }

    private void resetHeading(double start, IMUControl imuControl)
    {
        if (imuControl.getAngle() > start)
        {
            while (imuControl.getAngle() > start)
            {
                driveMotors(0, -1, 0, 0.2);
            }
        }
        else if (imuControl.getAngle() < start)
        {
            while (imuControl.getAngle() < start)
            {
                driveMotors(0, 1, 0, 0.2);
            }
        }
    }

    private void resetForward(double start)
    {
        if (lf.getCurrentPosition() > start)
        {
            while (lf.getCurrentPosition() > start)
            {
                driveMotors(-1, 0, 0, 0.3);
            }
        }
        else if (lf.getCurrentPosition() < start)
        {
            while (lf.getCurrentPosition() < start)
            {
                driveMotors(1, 0, 0, 0.3);
            }
        }

    }

}
