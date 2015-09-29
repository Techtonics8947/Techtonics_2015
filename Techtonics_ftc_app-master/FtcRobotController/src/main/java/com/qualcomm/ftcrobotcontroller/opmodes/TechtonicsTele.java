package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by colin on 9/28/15.
 */
public class TechtonicsTele extends OpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;

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

        //Gets the joystick values from the gamepads
        //Note: Pushing the all the way up returns -1,
        //So we reversed the values with the "-" before "gamepad1"
        float leftY = -gamepad1.left_stick_y;
        float rightY = -gamepad1.right_stick_y;

        //Sets the power of the motors with the joystick values
        leftMotor.setPower(leftY);
        rightMotor.setPower(rightY);

    }


}
