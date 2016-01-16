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
}
