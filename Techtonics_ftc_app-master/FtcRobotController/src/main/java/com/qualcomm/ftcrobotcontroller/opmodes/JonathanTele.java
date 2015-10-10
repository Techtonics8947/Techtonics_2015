package com.qualcomm.ftcrobotcontroller.opmodes;

//Import packages
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by student on 9/30/15.
 */

public class JonathanTele extends OpMode {

    //Tells the code that there is a left and right Motor
    DcMotor leftMotor;
    DcMotor rightMotor;

    @Override
    public void init() {

        //Finds the motors from the HardwareMap
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");

        //Reverses the left motor
        leftMotor.setDirection(DcMotor.Direction.REVERSE);

    }

    @Override
    public void loop() {

        //Assigns a variable with the gamepad joystick level
        float leftY = -gamepad1.left_stick_y;
        float rightY = -gamepad1.right_stick_y;

        //Sets the motors to the variable of the gamepad joystick
        leftMotor.setPower(leftY);
        rightMotor.setPower(rightY);

    }


}
