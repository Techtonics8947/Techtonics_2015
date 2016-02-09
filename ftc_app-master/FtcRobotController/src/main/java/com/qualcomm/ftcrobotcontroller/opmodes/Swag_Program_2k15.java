package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by student on 11/4/15.
 */
public class Swag_Program_2k15 extends TechtonicsTele {
    DcMotor leftMotor;
    DcMotor rightMotor;
    Servo rightDoor;
    Servo leftDoor;

    boolean rightDoorOpen;
    boolean leftDoorOpen;
    @Override
    public void init () {
        locateMotors();
        rightDoor = hardwareMap.servo.get("servo_right");
        leftDoor = hardwareMap.servo.get("servo_left");

    }
    @Override
    public void loop(){
        updateMotors();
        if (gamepad1.b) {
            rightDoorOpen = !rightDoorOpen;
        }

        if (gamepad1.b) {
            leftDoorOpen = !leftDoorOpen;
        }

        if (rightDoorOpen){
            rightDoor.setPosition(50);
        }
        else {
            rightDoor.setPosition(100);
        }

        if (leftDoorOpen){
            leftDoor.setPosition(50);
        }
        else{
            leftDoor.setPosition(100);
        }
        rightDoor.setPosition(50);

        leftDoor.setPosition(50);

    }


}
//This program is not intended for 15-year olds