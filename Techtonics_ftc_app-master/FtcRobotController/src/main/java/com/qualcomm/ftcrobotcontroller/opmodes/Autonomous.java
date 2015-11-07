package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Colin Santee on 10/14/15.
 */
public class Autonomous extends LinearOpMode{

    DcMotor rightMotor;
    DcMotor leftMotor;
    ColorSensor colorSensor;
    Servo buttonServo;
    Servo climberServo;

    @Override
    public void runOpMode() throws InterruptedException {

        //Init (Locates Devices)
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");
        colorSensor = hardwareMap.colorSensor.get("sensor_color");
        buttonServo = hardwareMap.servo.get("servo_button");
        climberServo = hardwareMap.servo.get("servo_climber");

        //Reverses the direction of the left motor
        leftMotor.setDirection(DcMotor.Direction.REVERSE);

        //Wait for start of round
        waitForStart();

        //Move forward 4 feet, turn 45 degrees
        for (int i = 0; i < 2; i++){
            rightMotor.setPower(1.0);
            leftMotor.setPower(1.0);
            sleep(2200);                        //4 Feet

            leftMotor.setPower(0);
            rightMotor.setPower(0);

            rightMotor.setPower(1.0);
            leftMotor.setPower(0);              //45 Degree Turn
            sleep(500);

            leftMotor.setPower(0);
            rightMotor.setPower(0);
        }

        rightMotor.setPower(1.0);
        leftMotor.setPower(1.0);
        sleep(1100);

        leftMotor.setPower(0);
        rightMotor.setPower(0);

        leftMotor.setPowerFloat();
        rightMotor.setPowerFloat();

        //Dumps the climber into the bucket
        climberServo.setPosition(50);

        //Detects the color of the Beacon and then determins which button to press
        if(colorSensor.red() > 100){
            buttonServo.setPosition(100);
        }
        else{
            buttonServo.setPosition(50);
        }

    }
}
