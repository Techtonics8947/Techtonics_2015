package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.hardware.ModernRoboticsI2cGyro;

/**
 * Created by colin on 1/14/16.
 */
public class autoBase extends LinearOpMode {

    DcMotor rightMotor;
    DcMotor leftMotor;

    DcMotor armLeft;
    DcMotor armRight;

    ModernRoboticsI2cGyro sensorGyro;

    final static int Red = 0;
    final static int Blue = 360;
    final static int Sleep = 10000;
    final static int NoSleep = 0;

    @Override
    public void runOpMode() throws InterruptedException {
    }

    public void setMotors(double power){
        leftMotor.setPower(power);
        rightMotor.setPower(power);
    }

    public void autonomous5(int direction, int sleepTime) throws InterruptedException
    {

//      armLeft.setPower(0.25);  //Rotate out the arm to be used as a "plow"
//      armRight.setPower(0.25);
//      sleep(800);
//      armLeft.setPower(0);
//      armRight.setPower(0);

        sensorGyro.calibrate();

        waitForStart();

        while (sensorGyro.isCalibrating()) {
            Thread.sleep(50);
        }

        telemetry.addData("Heading ", sensorGyro.getHeading());
        telemetry.addData("Integrated ", sensorGyro.getIntegratedZValue());

        sleep(sleepTime);

        //Move forward 4 feet, turn 45 degree
        setMotors(1);
        sleep(2200);                        //4 Feet (Not accurate)
        setMotors(0);

        turn(Math.abs(direction - 45));

        telemetry.addData("Heading ", sensorGyro.getHeading());
        telemetry.addData("Integrated ", sensorGyro.getIntegratedZValue());

        setMotors(1);
        sleep(2100);
        setMotors(0);

        turn(Math.abs(direction - 90));

        telemetry.addData("Heading ", sensorGyro.getHeading());
        telemetry.addData("Integrated ", sensorGyro.getIntegratedZValue());

        setMotors(1);
        sleep(1500);
        setMotors(0);

        leftMotor.setPowerFloat();
        rightMotor.setPowerFloat();
    }

    public void autonomous10(int direction, int sleepTime) throws InterruptedException
    {

        sensorGyro.calibrate();

        waitForStart();

        while (sensorGyro.isCalibrating()) {
            Thread.sleep(50);
        }

        telemetry.addData("Heading ", sensorGyro.getHeading());
        telemetry.addData("Integrated ", sensorGyro.getIntegratedZValue());

        Thread.sleep(10000);

        //Move forward 4 feet, turn 45 degree
        setMotors(1);
        sleep(6000);                        //4 Feet (Not accurate)
        setMotors(0);

        turn(Math.abs(direction - 45));

        telemetry.addData("Heading ", sensorGyro.getHeading());
        telemetry.addData("Integrated ", sensorGyro.getIntegratedZValue());

        setMotors(1);
        sleep(500);
        setMotors(0);

        leftMotor.setPowerFloat();
        rightMotor.setPowerFloat();
    }

    private void turn(int target) throws InterruptedException{

        for (int count = 0; count < 2; count++)
        {
            int reverse = 1;

            int heading = sensorGyro.getHeading();
            int timeToTurn = 0;
            int degreesToTurn = Math.abs(heading - target);

            if (heading < target)
            {
                reverse = -1;
            }

            if (degreesToTurn > 180) {
                degreesToTurn = 360 - degreesToTurn;
                reverse = reverse * -1;
            }

            timeToTurn = degreesToTurn * 10;
            leftMotor.setPower(1.0 * reverse);
            rightMotor.setPower(-1.0 * reverse);
            sleep(timeToTurn);
            setMotors(0);

        }
    }

    public void driveForwards(double driveDuration) throws InterruptedException{
        int startHeading = sensorGyro.getHeading();


        double startTime = getRuntime();
        double runTime = getRuntime() - startTime;

        //telemetry.addData("start ", startTime);
        //telemetry.addData("runtime ", runTime);

        double rightPower = 1;
        double leftPower = 1;

        int degreesOff;

        setMotors(1);

        int currentHeading;
        while(runTime < driveDuration) {
            currentHeading = sensorGyro.getHeading();

            telemetry.addData("Heading ", currentHeading);

            telemetry.addData("Target ", startHeading);

            // if difference is greater than 100
            // then if current is less than start
            // then add 360 to start
            if (currentHeading != startHeading) {
                if (((currentHeading - startHeading) > 100) || ((startHeading - currentHeading) > 100)) {
                    if (currentHeading < startHeading) {
                        startHeading += 360;
                    } else {
                        currentHeading += 360;
                    }
                }

                degreesOff = startHeading - currentHeading;

                if (degreesOff > 0) {        //Assuming positive equals turning to the right
                    leftPower = leftPower - (degreesOff * 0.1);
                    leftMotor.setPower(leftPower);
                } else {
                    rightPower = rightPower + (degreesOff * 0.1);
                    rightMotor.setPower(rightPower);
                }
            }
            runTime = getRuntime() - startTime;


        }

        setMotors(0);
    }
}
