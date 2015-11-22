package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by colin on 11/21/15.
 */

public class ScrimmageAuto extends LinearOpMode {

    DcMotor rightMotor;
    DcMotor leftMotor;

    @Override
    public void runOpMode() throws InterruptedException {

        //Init (Locates Devices)
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");

        //Reverses the direction of the left motor
        leftMotor.setDirection(DcMotor.Direction.REVERSE);

        //Wait for start of round
        waitForStart();

        //Move forward 4 feet, turn 45 degrees

        rightMotor.setPower(1.0);
        leftMotor.setPower(1.0);
        sleep(2200);                        //4 Feet

        leftMotor.setPower(0);
        rightMotor.setPower(0);

        rightMotor.setPower(1.0);
        leftMotor.setPower(0);              //45 Degree Turn
        sleep(500);

        leftMotor.setPower(0);
        rightMotor.setPower(0);

        rightMotor.setPower(1.0);
        leftMotor.setPower(0);              //45 Degree Turn
        sleep(500);

        leftMotor.setPower(0);
        rightMotor.setPower(0);

        rightMotor.setPower(1.0);
        leftMotor.setPower(0);              //45 Degree Turn
        sleep(500);

        leftMotor.setPower(0);
        rightMotor.setPower(0);

        rightMotor.setPower(1.0);
        leftMotor.setPower(1.0);
        sleep(1100);

        leftMotor.setPower(0);
        rightMotor.setPower(0);

        rightMotor.setPower(1.0);
        leftMotor.setPower(1.0);
        sleep(2200);                        //4 Feet

        leftMotor.setPower(0);
        rightMotor.setPower(0);

        leftMotor.setPowerFloat();
        rightMotor.setPowerFloat();

    }
}
