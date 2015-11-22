package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by colin on 11/18/15.
 */

public class ScrimmageTele extends TechtonicsTele {

    DcMotor leftMotor;
    DcMotor rightMotor;

    DcMotor armLeft;
    DcMotor armRight;

    Servo gripper;

    @Override
    public void init(){

        locateMotors();

        armLeft = hardwareMap.dcMotor.get("arm_left");
        armRight = hardwareMap.dcMotor.get("arm_right");

        gripper = hardwareMap.servo.get("servo_gripper");

        armLeft.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop(){

        updateMotors();

        if (gamepad1.right_trigger > 0){
            armRight.setPower(0.25);
            armLeft.setPower(0.25);
        }

        else if (gamepad1.left_trigger > 0){
            armRight.setPower(-0.25);
            armLeft.setPower(-0.25);
        }

        else{
            armRight.setPower(0);
            armLeft.setPower(0);
        }

        if (gamepad1.right_bumper){
            gripper.setPosition(.9);   //Set to open position
        }

        else if(gamepad1.left_bumper){
            gripper.setPosition(0);     //Set to closed position
        }

    }

}