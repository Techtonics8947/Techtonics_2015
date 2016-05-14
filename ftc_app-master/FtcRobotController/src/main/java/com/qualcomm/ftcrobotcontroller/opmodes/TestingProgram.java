package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Thaomas on 4/16/16.
 */

public class TestingProgram extends TechtonicsTele{

    DcMotor leftMotor;
    DcMotor rightMotor;

    Servo dumper;

    Servo left;
    Servo right;

    public void init(){
        locateMotors();

        dumper = hardwareMap.servo.get("servo_bucket");

        left = hardwareMap.servo.get("servo_left");
        right = hardwareMap.servo.get("servo_right");
    }

    public void loop(){
        updateMotors();

        if(gamepad1.a) {
            dumper.setPosition(1.0);
        }else{
            dumper.setPosition(0);
        }

        if(gamepad1.dpad_left) {
            left.setPosition(1.0);
        }else{
            left.setPosition(0);
        }

        if(gamepad1.dpad_right){
            right.setPosition(0);
        }else{
            right.setPosition(1);
        }
    }
}
