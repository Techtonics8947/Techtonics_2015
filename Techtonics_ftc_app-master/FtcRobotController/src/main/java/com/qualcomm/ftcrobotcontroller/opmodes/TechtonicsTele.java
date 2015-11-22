package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Colin on 9/28/15.
 */

public class TechtonicsTele extends OpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;

    @Override
    public void init() {
        locateMotors();
    }

    @Override
    public void loop() {
        updateMotors();
    }

    public void locateMotors(){

        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");

        leftMotor.setDirection(DcMotor.Direction.REVERSE);

    }

    public void updateMotors(){
        //Gets the joystick values from the gamepads
        //Note: Pushing the all the way up returns -1,
        //So we reversed the values with the "-" before "gamepad1"

        double leftY = -gamepad1.left_stick_y;
        double rightY = -gamepad1.right_stick_y;

        leftY = -gamepad1.left_stick_y;
        rightY = -gamepad1.right_stick_y;

        //Sets the power of the motors with the joystick values
        leftMotor.setPower(leftY);
        rightMotor.setPower(rightY);
    }

}