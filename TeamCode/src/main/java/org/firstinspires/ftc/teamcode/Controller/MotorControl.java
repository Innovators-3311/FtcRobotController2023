package org.firstinspires.ftc.teamcode.Controller;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.Logging;

public class MotorControl
{
    protected DcMotor motor;
    private String motorName;
    private boolean hasEncoder;

    ElapsedTime time;
    private int numberOfPosition = 1;

    private HardwareMap hardwareMap;
    private Telemetry telemetry;
    protected Gamepad gamepad1;
    protected Gamepad gamepad2;

    //Will be used to get the parameters below from the masterclass
    private MotorControl(OpMode opMode, ElapsedTime time)
    {
        this.time = time;
        this.hardwareMap = opMode.hardwareMap;
        this.telemetry = opMode.telemetry;
        this.gamepad1 = opMode.gamepad1;
        this.gamepad2 = opMode.gamepad2;
    }

    /**
     * @param motorName Name that you well enter for configuration
     * @param direction Direction you want the motor to spin: true = FORWARD, false = REVERSE
     * @param hasEncoder Does it have an encoder?
     */
    protected MotorControl(String motorName, Boolean direction, Boolean hasEncoder, OpMode opMode, ElapsedTime time)
    {
        this(opMode, time);

        this.motorName = motorName;
        this.hasEncoder = hasEncoder;
        try
        {
            motor = this.hardwareMap.get(DcMotor.class, motorName);

            if (direction) {motor.setDirection(DcMotorSimple.Direction.FORWARD);}
            else {motor.setDirection(DcMotorSimple.Direction.REVERSE);}

            if (hasEncoder) {motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);}

            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        }
        catch (IllegalArgumentException e)
        {
            Logging.log("%s not found in Hardware Map",motorName);
            telemetry.addData("Exception:", "%s not found in Hardware Map",motorName);
            telemetry.update();

            //sleep(1000);

        }

    }

    /**
     * Analog control method without bounds
     *
     * @param speedLimit Put's restriction on how fast the motor can spin
     * @param input which gamepad float value that will mak this spin
     */
    protected void analogControl(double speedLimit, double input, boolean advanceBreak)
    {
        double motorPower = input;
        motorPower = Range.clip(motorPower, -speedLimit, speedLimit);

        if (Math.abs(motorPower) > 0)
        {
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            motor.setPower(motorPower);
        }
        else if (advanceBreak && motor.getMode() == DcMotor.RunMode.RUN_WITHOUT_ENCODER)
        {
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motor.setTargetPosition(motor.getCurrentPosition());
            motor.setPower(0.3);
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        else if (motor.getMode() == DcMotor.RunMode.RUN_TO_POSITION) {}
        else {motorBreak();}

    }

    /**
     * Analog control method with bounds
     *
     * @param speedLimit Put's restriction on how fast the motor can spin
     * @param input which gamepad float value that will mak this spin
     * @param limit1 Made with limit switches in mind
     * @param limit2 Made with limit switches in mind
     */
    protected void analogControl(double speedLimit, double input, boolean advanceBreak, boolean limit1, boolean limit2)
    {
        double motorPower = input;
        motorPower = Range.clip(motorPower, -speedLimit, speedLimit);

        if (Math.abs(motorPower) > 0)
        {
            if (limit1 && motorPower > 0) {telemetry.addData("Upper bound break", "");motorBreak();}
            else if (limit2 && motorPower < 0) {telemetry.addData("Lower bound break", "");motorBreak();}
            else
            {
                motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                motor.setPower(motorPower);
            }
        }
        else if (advanceBreak && motor.getMode() == DcMotor.RunMode.RUN_WITHOUT_ENCODER)
        {
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motor.setTargetPosition(motor.getCurrentPosition());
            motor.setPower(0.3);
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        else if (motor.getMode() == DcMotor.RunMode.RUN_TO_POSITION) {}
        else {motorBreak();}

    }

    /**
     * Analog control method with bounds
     *
     * @param speedLimit Put's restriction on how fast the motor can spin
     * @param input which gamepad float value that will mak this spin
     * @param limit1 Made with limit switches in mind
     * @param limit2 Made with limit switches in mind
     */
    protected void analogControl(double speedLimit, double input, boolean advanceBreak, boolean limit1, double limit2)
    {
        double motorPower = input;
        motorPower = Range.clip(motorPower, -speedLimit, speedLimit);

        if (Math.abs(motorPower) > 0)
        {
            if (limit1 && motorPower > 0) {telemetry.addData("Upper bound break", "");motorBreak();}
            else if (100 + Math.abs(motor.getCurrentPosition()) < 100 + Math.abs(limit2) && motorPower < 0) {telemetry.addData("Lower bound break", "");motorBreak();}
            else
            {
                motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                motor.setPower(motorPower);
            }
        }
        else if (advanceBreak && motor.getMode() == DcMotor.RunMode.RUN_WITHOUT_ENCODER)
        {
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motor.setTargetPosition(motor.getCurrentPosition());
            motor.setPower(0.3);
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        else if (motor.getMode() == DcMotor.RunMode.RUN_TO_POSITION) {}
        else {motorBreak();}

    }

    /**
     * Analog control method with bounds
     *
     * @param speedLimit Put's restriction on how fast the motor can spin
     * @param input which gamepad float value that will mak this spin
     * @param lowerBound Motor will not spin past this bound at negative power (must have encoder to use this feature)
     * @param upperBound Motor will not spin past this bound at positive power(must have encoder to use this feature)
     */
    protected void analogControl(double speedLimit, double input, boolean advanceBreak, int lowerBound, int upperBound)
    {
        double motorPower = input;
        motorPower = Range.clip(motorPower, -speedLimit, speedLimit);

        if (Math.abs(motorPower) > 0)
        {
            if (100 + Math.abs(motor.getCurrentPosition()) > 100 + Math.abs(upperBound) && motorPower > 0) {telemetry.addData("Upper bound break", "");motorBreak();}
            else if (100 + Math.abs(motor.getCurrentPosition()) < 100 + Math.abs(lowerBound) && motorPower < 0) {telemetry.addData("Lower bound break", "");motorBreak();}
            else
            {
                motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                motor.setPower(motorPower);
            }
        }
        else if (advanceBreak && motor.getMode() == DcMotor.RunMode.RUN_WITHOUT_ENCODER)
        {
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motor.setTargetPosition(motor.getCurrentPosition());
            motor.setPower(0.3);
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        else if (motor.getMode() == DcMotor.RunMode.RUN_TO_POSITION) {}
        else {motorBreak();}

    }

    protected void toggleDrive(boolean argument, int upperPosition, int lowerPosition)
    {
        if (time.seconds() == 0)
        {
            time.startTime();
        }
        double lastChanged = 0;

        if (argument && numberOfPosition == 1 && (lastChanged + 0.25) < time.seconds())
        {
            lastChanged = time.seconds();
            numberOfPosition = 2;
            encoderControl(upperPosition, 1);
        }
        else if (argument && numberOfPosition == 2 && (lastChanged + 0.25) < time.seconds())
        {
            lastChanged = time.seconds();
            numberOfPosition = 1;
            encoderControl(lowerPosition, 1);
        }
        else
        {
            telemetry.addData("ToggleDrive", "Something went wrong in toggleDrive");
        }
    }

    /**
     * @param speed The speed at which the motor will spin
     * @param argument1 The Gamepad bool input that will make it spin forward
     * @param argument2 The Gamepad bool input that will make it spin backwards
     */
    protected void simpleDrive(double speed, boolean argument1, boolean argument2)
    {
        if (argument1)
        {
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            run(speed);
        }
        else if (argument2)
        {
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            run(-speed);
        }
        else {motorBreak();}
    }

    /**
     * @param speed The speed at which the motor will spin
     * @param argument1 The Gamepad bool input that will make it spin forward
     */
    protected void simpleDrive(double speed, boolean argument1)
    {
        if (argument1)
        {
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            run(speed);
        }
        else {motorBreak();}
    }


    /**
     * @param target Target location that the motor will move to
     * @param speed The speed at which the motor will spin
     * @param argument The Gamepad bool input that will make it move
     */
    public void encoderControl(int target, double speed, boolean argument)
    {
        if (argument)
        {
            encoderControl(target, speed);
        }
    }

    /**
     * @param target Target location that the motor will move to
     * @param speed The speed at which the motor will spin
     * @param argument The Gamepad analog input that will make it move if its value is greater than 0.2
     */
    public void encoderControl(int target, double speed, double argument)
    {
        if (Math.abs(argument) > 0.2)
        {
            encoderControl(target, speed);
        }
    }

    /**
     * @param target Target location that the motor will move to
     * @param speed The speed at which the motor will spin
     */
    public void encoderControl(int target, double speed)
    {
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setTargetPosition(target);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.setPower(speed);
    }

    /**
     * for motors that just need to spin call break to stop
     * @param speed speed you want the motor to spin
     */
    protected void run(double speed)
    {
        motor.setPower(speed);
    }

    /**
     * for motors that just need to spin call break to stop
     * @param speed speed you want the motor to spin
     */
    protected void run(double speed, double runLength)
    {
        if (time.seconds() == 0)
        {
            time.startTime();
        }
        double start = time.seconds();
        while (runLength < (time.seconds() - start))
        {
            motor.setPower(speed);
        }
        motor.setPower(0);
    }


    /**
     *     Breaking method also sets power to zero
     */
    protected void motorBreak()
    {
        motor.setPower(0);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        telemetry.addData(motorName, "Breaking");
    }

    /**
     *     Prints motor telemetry
     */
    protected void telemetry()
    {
        if (hasEncoder) {telemetry.addData(motorName, "Speed: %.2f\n\tEncoder Position: %d", motor.getPower(), motor.getCurrentPosition());}
        else {telemetry.addData(motorName, "Speed: %.2f", motor.getPower());}
    }

    protected int getMotorPosition()
    {
        return motor.getCurrentPosition();
    }

    protected boolean isBusy()
    {
        return motor.isBusy();
    }
}
