package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/*
 * Created by Jonathan and Kinsey on 9/30/15
 */

public class KinseySwagTele extends OpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;

    //Don't judge the name

    @Override
    public void init() {

        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");

        leftMotor.setDirection(DcMotor.Direction.REVERSE);

    }
    @Override
    public void loop() {

        float leftY = -gamepad1.left_stick_y;
        float rightY = -gamepad1.right_stick_y;

        //This program is only to be used by cool people who are SWAG

        leftMotor.setPower(leftY);
        rightMotor.setPower(rightY);

    }
}
