package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by colin on 9/28/15.
 */

public class TechtonicsTele_Halfspeed extends OpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;

    boolean halfSpeed = true;

    @Override
    public void init() {

        //Get refrences to the motors from the hardware map
        leftMotor = hardwareMap.dcMotor.get("left_drive"); //Left motor
        rightMotor = hardwareMap.dcMotor.get("right_drive"); //Right Motor

        //Reverses the right motor
        leftMotor.setDirection(DcMotor.Direction.REVERSE);

    }

    @Override
    public void loop() {

        double leftY = 0;
        double rightY = 0;

        if (gamepad1.left_stick_button){
            halfSpeed = true;
        }

        if (gamepad1.right_stick_button){
            halfSpeed = false;
        }

        if (halfSpeed) {
            leftY = -(gamepad1.left_stick_y / 2);
            rightY = -(gamepad1.right_stick_y / 2);
        }
        else {
            leftY = -(gamepad1.left_stick_y);
            rightY = -(gamepad1.right_stick_y);
        }


    }

}