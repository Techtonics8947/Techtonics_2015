package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by student on 5/11/16.
 */
public class TechtonicsAuto extends LinearOpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;

    Servo dumper;

    @Override
    public void runOpMode() throws InterruptedException {

        dumper = hardwareMap.servo.get("servo_bucket");

        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");

        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        rightMotor.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();

        leftMotor.setPower(1);
        rightMotor.setPower(1);

        sleep(4000);

        leftMotor.setPower(0);
        rightMotor.setPower(0);

    }



}
