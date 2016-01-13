package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Colin Santee on 10/14/15.
 */

public class AutoRed5First extends LinearOpMode{

    DcMotor rightMotor;
    DcMotor leftMotor;

    DcMotor armLeft;
    DcMotor armRight;

    @Override
    public void runOpMode() throws InterruptedException {

        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");

        armLeft = hardwareMap.dcMotor.get("arm_left");
        armRight = hardwareMap.dcMotor.get("arm_right");

        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        armLeft.setDirection(DcMotor.Direction.REVERSE);

        //Wait for start of round
        waitForStart();

        armLeft.setPower(0.25);  //Rotate out the arm to be used as a "plow"
        armRight.setPower(0.25);
        sleep(800);
        armLeft.setPower(0);
        armRight.setPower(0);

        //Move forward 4 feet, turn 45 degree
        setMotors(1);
        sleep(2200);                        //4 Feet (Not accurate)
        setMotors(0);

        rightMotor.setPower(1.0);
        leftMotor.setPower(0);              //45 Degree Turn
        sleep(500);
        setMotors(0);

        setMotors(1);
        sleep(2100);
        setMotors(0);

        rightMotor.setPower(1.0);
        leftMotor.setPower(0);
        sleep(800);
        setMotors(0);

        setMotors(1);
        sleep(1300);
        setMotors(0);

        leftMotor.setPowerFloat();
        rightMotor.setPowerFloat();

    }

    public void setMotors(double power){
        leftMotor.setPower(power);
        rightMotor.setPower(power);
    }

}
