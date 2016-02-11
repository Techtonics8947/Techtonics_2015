package com.qualcomm.ftcrobotcontroller.opmodes;

import android.util.Log;

import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsAnalogOpticalDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.robocol.Telemetry;

import java.util.InputMismatchException;

import static com.qualcomm.ftccommon.DbgLog.*;

/**
 * Created by colin on 1/14/16.
 */
public class autoBase extends LinearOpMode {

    DcMotor rightMotor;
    DcMotor leftMotor;

    DcMotor armLeft;
    DcMotor armRight;

    Servo climber;
    //  Need to add Angle, and Assist
    DcMotor extensionLeft;
    DcMotor extensionRight;

    // Sensor is updside down so degrees go clockwise
    // normal protractor would go counter-clockwise which makes this confusing.
    ModernRoboticsI2cGyro sensorGyro;
    ModernRoboticsAnalogOpticalDistanceSensor sensorLine;

    final static int Red = 0;
    final static int Blue = 360;
    final static int Sleep = 10000;
    final static int NoSleep = 0;

    final static double POWER_OFF = 0;
    final static double FULL_POWER = 1;
    final static double MIN_POWER = .5;

    double baselineColor;
    double TurnTime = 9.5;
    private double startTime = 0;

    @Override
    public void runOpMode() throws InterruptedException {
    }

    // Assumes FULL POWER BATTER - 13.8 Volts (>13V) Output!!!!    There is no current
    // adjustment for using weaker batteries.   It drastically changes the distance
    // travelled since it is based on sleep.    We need to change to using Encoders.

    public void autonomous5(int direction, int sleepTime) throws InterruptedException {
        StartUp(sleepTime);
        DriveStraight(1.5, 0, false);   //4 Feet (Not accurate)
        double DoubleBlueStep2 = 3.2;
        double DoubleBlueStep3 = 1.6;
        double DoubleRedStep2 = 3.2;
        double DoubleRedStep3 = 1.4;

        if(direction == Blue) {
            DriveStraight(DoubleBlueStep2, Math.abs(direction - 45), false);   //4 Feet (Not accurate)
            // Would be nice to add an Optical Distance sensor so we know how far to drive.
            DriveStraight(DoubleBlueStep3, Math.abs(direction - 90), false);   //4 Feet (Not accurate)
        }
        if(direction == Red){
            DriveStraight(DoubleRedStep2, Math.abs(direction - 45), false);   //4 Feet (Not accurate)
            // Would be nice to add an Optical Distance sensor so we know how far to drive.
            DriveStraight(DoubleRedStep3, Math.abs(direction - 90), false);   //4 Feet (Not accurate)
        }

        DropClimbers();

        //MoveArm(false);

        // "Return To Witch Mountain"
        // Monitor Y AXIS for tilting so that we know we are on Mountain.
        // Record Y Axis so we can find figure out which angles to look for.
        DriveBackwards(1.1, Math.abs(direction - 90));
        DriveBackwards(1.4, Math.abs(direction - 45));
        DriveBackwards(2.4, Math.abs(direction - 315));

        /*&extensionLeft.setPower(1);
        extensionRight.setPower(1);
        sleep(500);
        extensionLeft.setPower(0);
        extensionRight.setPower(0);
        */

        // For States
        // Color Detection and Button Press

        // For the super ambitious...
        // "To The Top!"
        // Up Angle - Drop Mountain Assist - Drop Angle - Pull In Extension and Drive Up

        EndAutonomous();
    }

    public void autonomous10(int direction, int sleepTime) throws InterruptedException {
        InitializeHardware();
        LogMsg("FTC-TT - waiting for start.");
        waitForStart();
        startTime = getRuntime();
        LogMsg("Starting!" + Double.toString(startTime));
        Thread.sleep(10000);

        MoveArm(false);

        //Move forward 4 feet, turn 45 degree
        setDriveMotors(1);
        sleep(6000);                        //4 Feet (Not accurate)
        setDriveMotors(0);

        Turn(Math.abs(direction - 45));

        telemetry.addData("Heading ", sensorGyro.getHeading());
        telemetry.addData("Integrated ", sensorGyro.getIntegratedZValue());

        setDriveMotors(1);
        sleep(500);
        setDriveMotors(0);

        leftMotor.setPowerFloat();
        rightMotor.setPowerFloat();
    }

    public void autonomousLine(int direction, int sleepTime) throws InterruptedException {
        StartUp(sleepTime);

        //Drive along side of mountain until first red/blue line
        DriveStraight(8000, Math.abs(direction - 315), true);
        //Drive within floor goal until line centered on beacon
        DriveStraight(4000, Math.abs(direction - 45), true);
        //Drive up to the beacon
        DriveStraight(2000, Math.abs(direction - 45), false);

        DropClimbers();

    }

    private void StartUp(int SleepTime) throws InterruptedException {
        InitializeHardware();
        LogMsg("Waiting for start...");
        waitForStart();
        startTime = getRuntime();
        LogMsg("Starting!   Start Time: " + Double.toString(startTime));

        // put arm down
        MoveArm(true);

        // pause for other driver
        sleep(SleepTime);
    }

    public void InitializeHardware() throws InterruptedException {

        LogMsg("Initializing hardware...");

        telemetry.addData("Inializing hardware...", 0);

        try {
            leftMotor = hardwareMap.dcMotor.get("left_drive");
            rightMotor = hardwareMap.dcMotor.get("right_drive");
        } catch (Exception ex) {
            DbgLog.error("FTC-TT - Drive Motors initializion error.");
        }

        armLeft = hardwareMap.dcMotor.get("arm_left");
        armRight = hardwareMap.dcMotor.get("arm_right");

        sensorGyro = (ModernRoboticsI2cGyro) hardwareMap.gyroSensor.get("gyro");

        sensorLine = (ModernRoboticsAnalogOpticalDistanceSensor) hardwareMap.opticalDistanceSensor.get("line_sensor");
        baselineColor = sensorLine.getLightDetected();
        LogMsg("Initial Line Color: " + Double.toString(baselineColor));

        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        armLeft.setDirection(DcMotor.Direction.REVERSE);

        extensionLeft = hardwareMap.dcMotor.get("extension_left");
        extensionRight = hardwareMap.dcMotor.get("extension_right");

        climber = hardwareMap.servo.get("climber_drop");
        sensorGyro.calibrate();

        while (sensorGyro.isCalibrating()) {
            sleep(50);
        }
        sensorGyro.resetZAxisIntegrator();
        sleep(4000);
        LogMsg("Heading After reset - 4 second wait: " + sensorGyro.getHeading());
        LogMsg("Initializing hardware... done.");
        telemetry.addData("Initialize hardware complete. READY For Start... ", sensorGyro.getHeading());
    }

    public void Turn(int target) throws InterruptedException {

        int heading = sensorGyro.getHeading();
        LogMsg("TURN Starting: - Target: " + Integer.toString(target) + " Current: " + Integer.toString(heading));
        telemetry.addData("TURN Target Target: ", Integer.toString(target));
        telemetry.addData("TURN Target Heading: ", Integer.toString(heading));
        boolean OnTarget = false;
        double power = .6;
        while (!OnTarget) {
            heading = sensorGyro.getHeading();
            LogMsg("Turning - Heading: " + Integer.toString(heading));
            int DegreesOff = CalcDegreesOff(target, heading);
            if (DegreesOff < 3) {
                LogMsg("Found Target: " + Integer.toString(heading) + " DegreesOff: " + DegreesOff);
                OnTarget = true;
            } else {
                leftMotor.setPower(power * DirectionAdjustment(target, heading));
                rightMotor.setPower(-power * DirectionAdjustment(target, heading));
                if (DegreesOff < 30)
                    power = 0.6;
                sleep(50);
            }
        }
        setDriveMotors(0);
        sleep(250);
    }

    public void DriveStraight(double DriveDuration, int TargetHeading, boolean driveToLine) throws InterruptedException {

        telemetry.addData("Drive Straight... Heading: ", Integer.toString(TargetHeading));
        telemetry.addData("Drive Straight... Duration: ", Double.toString(DriveDuration));

        LogMsg("Drive Straight... Turning " + Integer.toString(TargetHeading));
        Turn(TargetHeading);
        LogMsg("Drive Straight... Forward " + Integer.toString(TargetHeading));

        double driveStart = getRuntime();
        double runTime = getRuntime() - driveStart;

        double rightPower = FULL_POWER;
        double leftPower = FULL_POWER;

        int degreesOff;

        setDriveMotors(FULL_POWER);

        int currentHeading;
        boolean stopYet = false;
        while ((runTime < DriveDuration) && stopYet == false) {
            currentHeading = sensorGyro.getHeading();

            LogMsg("Drive Straight-Heading: " + Integer.toString(currentHeading));

            degreesOff = CalcDegreesOff(TargetHeading, currentHeading);

            if (degreesOff > 0) {

                double powerAdjustment = (degreesOff * 0.05);
                double MeterChange = 0.3;
                // ASSUMPTION:  we should never need to decrease power more than 30% on either side
                if (powerAdjustment > MeterChange) powerAdjustment = MeterChange;

                // Heading Change
                if (DirectionAdjustment(TargetHeading, currentHeading) == -1) {
                    if (rightPower < 1)
                        rightPower = PowerMeter(rightPower + (rightPower * powerAdjustment));
                    else
                        leftPower = PowerMeter(leftPower - (leftPower * powerAdjustment));
                } else {
                    if (leftPower < 1)
                        leftPower = PowerMeter(leftPower + (leftPower * powerAdjustment));
                    else
                        rightPower = PowerMeter(rightPower - (rightPower * powerAdjustment));
                }

                LogMsg("Left Power: " + Double.toString(leftPower) + "  Right Power: " + Double.toString(rightPower));
                leftMotor.setPower(leftPower);
                rightMotor.setPower(rightPower);
            }
            waitOneFullHardwareCycle();
            runTime = getRuntime() - driveStart;

            // checking for line here
            if(driveToLine) {
                stopYet = checkForNewColor(stopYet);
            }
        }

        setDriveMotors(POWER_OFF);
        LogMsg("Drive Straight Forward-Runtime: " + Double.toString(runTime));
    }

    public boolean checkForNewColor(boolean stopYet){
        double drivingColor = sensorLine.getLightDetected();
        LogMsg("Line Color Detected: " + Double.toString(drivingColor));
        if (drivingColor > 0.05) {
            stopYet = true;
        }
        return stopYet;
    }

    public void DriveBackwards(double DriveDuration, int TargetHeading) throws InterruptedException {

        telemetry.addData("Drive Backwards... Heading: ", Integer.toString(TargetHeading));
        telemetry.addData("Drive Backwards... Duration: ", Double.toString(DriveDuration));

        LogMsg("Drive Backwards... Turning " + Integer.toString(TargetHeading));
        Turn(TargetHeading);
        LogMsg("Drive Backwards... Forward " + Integer.toString(TargetHeading));

        double driveStart = getRuntime();
        double runTime = getRuntime() - driveStart;

        double rightPower = -FULL_POWER;
        double leftPower = -FULL_POWER;

        int degreesOff;

        setDriveMotors(-FULL_POWER);

        int currentHeading;
        while (runTime < DriveDuration) {
            currentHeading = sensorGyro.getHeading();

            LogMsg("Drive Backwards-Heading: " + Integer.toString(currentHeading));
            LogMsg("Drive Backwards... Y-Axis " + Integer.toString(sensorGyro.rawY()));
            LogMsg("Drive Backwards... X-Axis " + Integer.toString(sensorGyro.rawX()));

            degreesOff = CalcDegreesOff(TargetHeading, currentHeading);

            if (degreesOff > 0) {

                double powerAdjustment = (degreesOff * 0.05);
                double MeterChange = 0.3;
                // ASSUMPTION:  we should never need to decrease power more than 30% on either side
                if (powerAdjustment > MeterChange) powerAdjustment = MeterChange;

                // Heading Change
                if (DirectionAdjustment(TargetHeading, currentHeading) == 1) {
                    if (rightPower < -1)
                        rightPower = PowerMeter(rightPower + (rightPower * powerAdjustment));
                    else
                        leftPower = PowerMeter(leftPower - (leftPower * powerAdjustment));
                } else {
                    if (leftPower < -1)
                        leftPower = PowerMeter(leftPower + (leftPower * powerAdjustment));
                    else
                        rightPower = PowerMeter(rightPower - (rightPower * powerAdjustment));
                }

                LogMsg("Left Power: " + Double.toString(leftPower) + "  Right Power: " + Double.toString(rightPower));
                leftMotor.setPower(leftPower);
                rightMotor.setPower(rightPower);
            }
            sleep(100);
            runTime = getRuntime() - driveStart;
        }
        setDriveMotors(POWER_OFF);
        LogMsg("Drive Backwards Forward-Runtime: " + Double.toString(runTime));
    }

    void LogMsg(String msg) {
        DbgLog.msg("FTC-TT - " + msg);
    }

    /// Calculate Direction to Turn.   Returns True to turn Right / False to turn to Left
    public int DirectionAdjustment(int Target, int Heading) {
        boolean TurnRight = (Target > Heading);
        int degreesOff = Math.abs(Target - Heading);
        if (degreesOff > 180) TurnRight = !TurnRight;
        if (TurnRight) return 1;
        else return -1;
    }

    ///Calculate Degrees off in Absolute numbers
    public int CalcDegreesOff(int Target, int Heading) {
        int degreesOff = Math.abs(Target - Heading);
        if (degreesOff > 180) degreesOff = 360 - degreesOff;
        return (degreesOff);
    }

    double PowerMeter(double Power) {
        double NewPower = Power;
        // Max Power Limits
        if (Power > FULL_POWER) {
            NewPower = FULL_POWER;
        } else if (Power < -FULL_POWER) {
            NewPower = -FULL_POWER;
        }
        // Min Power Limits
        else if (Power > 0 && Power < MIN_POWER) {
            NewPower = MIN_POWER;
        }
        if (Power < 0 && Power > -MIN_POWER) {
            NewPower = -MIN_POWER;
        }

        if (NewPower != Power) {
            LogMsg("PowerMeter Adjustment from: " + Double.toString(Power) + " to " + Double.toString(NewPower));
        }
        return NewPower;
    }

    private void MoveArm(boolean Down) throws InterruptedException {
        if (Down) LogMsg("MoveArm - DOWN");
        else LogMsg("MoveArm - UP");
        int reverse = -1;
        int sleepTime = 1000;
        if (Down) {
            reverse = 1;
            sleepTime = 750;
        }
        setArmMotors(0.25 * reverse);
        sleep(sleepTime);
        armLeft.setPowerFloat();
        armRight.setPowerFloat();
    }

    private void DropClimbers() throws InterruptedException {
        LogMsg("Dropping Climbers... ");
        climber.setPosition(0);
        sleep(1000);
        climber.setPosition(1);
    }

    private void setArmMotors(double power) {
        LogMsg("SetArmMotors: " + Double.toString(power));
        armLeft.setPower(power);
        armRight.setPower(power);
    }

    public void setDriveMotors(double power) {
        LogMsg("SetDriveMotors: " + Double.toString(power));
        leftMotor.setPower(power);
        rightMotor.setPower(power);
    }

    private void EndAutonomous() {
        LogMsg("End! Final Timing: " + Double.toString(getRuntime() - startTime));
        telemetry.addData("Final Heading: ", sensorGyro.getHeading());
        telemetry.addData("Program Complete.   Runtime: ", Double.toString(getRuntime() - startTime));
    }
}