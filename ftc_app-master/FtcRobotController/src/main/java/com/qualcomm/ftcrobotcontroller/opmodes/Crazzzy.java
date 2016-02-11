package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by colin on 2/10/16.
 */
public class Crazzzy extends TechtonicsTele {

    DcMotor leftMotor;
    DcMotor rightMotor;

    boolean turnRight;

    DcMotor arm;

    // Servo gripper;
    Servo bucket;
    boolean bucketRight;
    Servo climber_left;
    Servo climber_right;
    Servo climber_drop;

    DcMotor mountainAssist;
    boolean mountainAssistOn;

    ModernRoboticsI2cGyro sensorGyro;

    DcMotor extAngleLeft;
    DcMotor extAngleRight;

    DcMotor extensionLeft;
    DcMotor extensionRight;

    long count = 0;

    @Override
    public void init(){
        locateMotors();

        arm = hardwareMap.dcMotor.get("arm");

        //gripper = hardwareMap.servo.get("servo_gripper");

        bucket = hardwareMap.servo.get("servo_bucket");

        climber_left = hardwareMap.servo.get("climber_left");
        climber_right = hardwareMap.servo.get("climber_right");

        sensorGyro = (ModernRoboticsI2cGyro) hardwareMap.gyroSensor.get("gyro");

        climber_drop = hardwareMap.servo.get("climber_drop");

        extAngleLeft = hardwareMap.dcMotor.get("angle_left");
        extAngleRight = hardwareMap.dcMotor.get("angle_right");

        extensionLeft = hardwareMap.dcMotor.get("extension_left");
        extensionRight = hardwareMap.dcMotor.get("extension_right");

        mountainAssist = hardwareMap.dcMotor.get("mountain_assist");

        arm.setDirection(DcMotor.Direction.REVERSE);
        extAngleLeft.setDirection(DcMotor.Direction.REVERSE);

    }

    @Override
    public void loop(){


        if(climber_drop.getPosition() == 1.0){
            climber_drop.setPosition(0.0);
        }
        else{
            climber_drop.setPosition(climber_drop.getPosition() + 0.1);
        }
        if(climber_left.getPosition() == 1.0){
            climber_left.setPosition(0.0);
        }
        else{
            climber_left.setPosition(1);
        }
        if(climber_right.getPosition() == 0.0){
            climber_right.setPosition(1.0);
        }
        else{
            climber_right.setPosition(0.0);
        }

        if(count%50 == 0){
            mountainAssistOn = !mountainAssistOn;
        }
        if(mountainAssistOn){
            mountainAssist.setPower(0.25);
        }
        else{
            mountainAssist.setPower(-0.25);
        }

        if(count%300 == 0){
            turnRight = !turnRight;
        }
        if(turnRight){
            leftMotor.setPower(0.6);
            rightMotor.setPower(-0.6);
        }
        else{
            leftMotor.setPower(-0.6);
            rightMotor.setPower(0.6);
        }

        if(count%100 == 0){
            bucketRight = !bucketRight;
        }
        if(bucketRight){
            bucket.setPosition(1.0);
        }
        else{
            bucket.setPosition(0.0);
        }

        count++;

    }


}
