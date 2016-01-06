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

    Servo bucket;

    DcMotor extAngleLeft;
    DcMotor extAngleRight;

    DcMotor extensionLeft;
    DcMotor extensionRight;

    @Override
    public void init(){
        locateMotors();

        armLeft = hardwareMap.dcMotor.get("arm_left");
        armRight = hardwareMap.dcMotor.get("arm_right");

        gripper = hardwareMap.servo.get("servo_gripper");

        bucket = hardwareMap.servo.get("servo_bucket");

        extAngleLeft = hardwareMap.dcMotor.get("angle_left");
        extAngleRight = hardwareMap.dcMotor.get("angle_right");

        extensionLeft = hardwareMap.dcMotor.get("extension_left");
        extensionRight = hardwareMap.dcMotor.get("extension_right");

        armLeft.setDirection(DcMotor.Direction.REVERSE);
        extAngleLeft.setDirection(DcMotor.Direction.REVERSE);
        bucket.setDirection(Servo.Direction.REVERSE);

    }

    @Override
    public void loop(){

        telemetry.addData("Time = ", getRuntime());

        updateMotors();

        //Flip the arm clockwise or counterclockwise
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

        //Sets extension motor with gamepad2 left stick
        if (gamepad2.left_stick_y > 0.25){
            extensionLeft.setPower(0.75);
            extensionRight.setPower(0.75);
        }
        else if (gamepad2.left_stick_y < -0.25){
            extensionLeft.setPower(-0.75);
            extensionRight.setPower(-0.75);
        }
        else{
            extensionLeft.setPower(0.0);
            extensionRight.setPower(0.0);
        }

        //Sets the gripper Position
        if (gamepad1.right_bumper){
            gripper.setPosition(0.25);      //Set to open position
        }

        else if(gamepad1.left_bumper){
            gripper.setPosition(0.75);     //Set to closed position
        }
        else{
            gripper.setPosition(0.50);
        }

        //Sets the angle servos
        if (gamepad2.right_trigger > 0.1){
            extAngleLeft.setPower(0.50);
            extAngleRight.setPower(0.50);
        }
        else if (gamepad2.right_bumper){
            extAngleLeft.setPower(-0.50);
            extAngleRight.setPower(-0.50);
        }
        else{
            extAngleLeft.setPower(0.00);
            extAngleRight.setPower(0.00);
        }

        //Operates the bucket with the DPad on the second gamepad
        if(gamepad2.dpad_right){
            bucket.setPosition(1.0);
        }
        else if(gamepad2.dpad_left){
            bucket.setPosition(0.0);
        }
        else{
            bucket.setPosition(0.5);
        }

    }

}