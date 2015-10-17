package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Colin Santee on 10/14/15.
 */
public class Autonomous extends LinearOpMode{

    DcMotor rightMotor;
    DcMotor leftMotor;

    @Override
    public void runOpMode() throws InterruptedException {

        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");

        leftMotor.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        for (int i = 0; i<4; i++){
            rightMotor.setPower(1.0);
            leftMotor.setPower(1.0);
            sleep(1000);
            rightMotor.setPower(0.25);
            leftMotor.setPower(-0.25);
            sleep(1000);
        }

        leftMotor.setPowerFloat();
        rightMotor.setPowerFloat();

    }
}
