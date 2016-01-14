package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by colin on 1/14/16.
 */
public class auto5Base extends LinearOpMode {

    DcMotor rightMotor;
    DcMotor leftMotor;

    DcMotor armLeft;
    DcMotor armRight;

    final static double Red = 1.0;
    final static double Blue = -1.0;
    final static int Sleep = 10000;
    final static int NoSleep = 0;

    @Override
    public void runOpMode() throws InterruptedException {}

    public void setMotors(double power){
        leftMotor.setPower(power);
        rightMotor.setPower(power);
    }

    public void autonomous(double direction, int sleepTime) throws InterruptedException
    {

//      armLeft.setPower(0.25);  //Rotate out the arm to be used as a "plow"
//      armRight.setPower(0.25);
//      sleep(800);
//      armLeft.setPower(0);
//      armRight.setPower(0);

        sleep(sleepTime);

        waitForStart();
        //Move forward 4 feet, turn 45 degree
        setMotors(1);
        sleep(2200);                        //4 Feet (Not accurate)
        setMotors(0);

        rightMotor.setPower(1.0 * direction);
        leftMotor.setPower(-1.0 * direction);              //45 Degree Turn
        sleep(500);
        setMotors(0);

        setMotors(1);
        sleep(2100);
        setMotors(0);

        rightMotor.setPower(1.0 * direction);
        leftMotor.setPower(-1.0 * direction);
        sleep(400);
        setMotors(0);

        setMotors(1);
        sleep(1500);
        setMotors(0);

        leftMotor.setPowerFloat();
        rightMotor.setPowerFloat();
    }
}
