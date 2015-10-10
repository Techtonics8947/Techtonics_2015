package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 * Created by student on 10/7/15.
 */

public class TouchTele extends OpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;
    TouchSensor testTouch1;
    TouchSensor testTouch2;

    @Override
    public void init() {

        //Locates the motors from the hardware map
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");

        //Reverses the left motor
        leftMotor.setDirection(DcMotor.Direction.REVERSE);

        //Locates the touch sensor
        testTouch1 = hardwareMap.touchSensor.get("sensor_touch1");
        testTouch2 = hardwareMap.touchSensor.get("sensor_touch2");


    }

    @Override
    public void loop() {

        boolean reverse = false;

        if (testTouch1.isPressed()) {

            leftMotor.setPower(0.5);
            rightMotor.setPower(0.5);
        } else if (testTouch2.isPressed()) {

            leftMotor.setPower(-0.5);
            rightMotor.setPower(-0.5);

        } else {
            leftMotor.setPower(0);
            rightMotor.setPower(0);


        }

    }

}
