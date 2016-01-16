package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by colin on 11/4/15.
 */
public class BucketDoor extends TechtonicsTele {

    DcMotor leftMotor;
    DcMotor rightMotor;
    Servo rightDoor;
    Servo leftDoor;

    boolean rightDoorOpen;
    boolean leftDoorOpen;

    @Override
    public void init(){
        locateMotors();

        rightDoor = hardwareMap.servo.get("servo_right");
        leftDoor = hardwareMap.servo.get("servo_left");

    }
    @Override
    public void loop(){
        updateMotors();
        if (gamepad1.right_bumper){
            rightDoorOpen = !rightDoorOpen;
        }
        if (gamepad1.left_bumper){
            leftDoorOpen = !leftDoorOpen;
        }

        if (rightDoorOpen){
            rightDoor.setPosition(50);
        }
        else {
            rightDoor.setPosition(100);
        }

        if (leftDoorOpen) {
            leftDoor.setPosition(50);
        }
        else{
            leftDoor.setPosition(100);
        }
    }
}
