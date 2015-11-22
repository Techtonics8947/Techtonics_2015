package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by colin on 11/11/15.
 */

public class AdvancedTele extends TechtonicsTele {

    DcMotor leftMotor;
    DcMotor rightMotor;

    DcMotor armLeft;
    DcMotor armRight;

    DcMotor liftLeft;
    DcMotor liftRight;

    //Woah! A lot of servos :D
    Servo buttonServo; //1
    Servo doorLeft; //2
    Servo doorRight; //3
    Servo bucketRack; //4
    Servo lift1; //5
    Servo lift2; //6
    Servo gripper; //7

    @Override
    public void init(){

        //Locates the drive motors
        locateMotors();

        //Locating Arm Motors from the hardware map
        armLeft = hardwareMap.dcMotor.get("arm_left");
        armRight = hardwareMap.dcMotor.get("arm_right");

        //Locating Lift Motors from the Hardware map
        liftLeft = hardwareMap.dcMotor.get("lift_left");
        liftRight = hardwareMap.dcMotor.get("lift_right");

        //Locating Servos from the hardware map
        buttonServo = hardwareMap.servo.get("servo_button");
        doorLeft = hardwareMap.servo.get("door_left");
        doorRight = hardwareMap.servo.get("door_right");
        bucketRack = hardwareMap.servo.get("bucket_right");
        lift1 = hardwareMap.servo.get("lift1");
        lift2 = hardwareMap.servo.get("lift2");
        gripper = hardwareMap.servo.get("gripper");

    }

    @Override
    public void loop(){
        //Updates the drive motors for moving
        updateMotors();

        double armY = gamepad2.left_stick_y;
        armLeft.setPower(armY);
        armRight.setPower(armY);

        double liftY = gamepad2.right_stick_y;
        liftLeft.setPower(liftY);
        liftRight.setPower(liftY);



    }

}
