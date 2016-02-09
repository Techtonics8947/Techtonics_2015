package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by colin on 11/7/15.
 */
public class EncoderAutonomous extends LinearOpMode {

    DcMotor rightMotor;
    DcMotor leftMotor;

    final static int ENCODER_CPR = 1440; //Encoder counts per rotation

    double COUNTS;

    //final static int TRACK_DISTANCE = 36"

    //ColorSensor colorSensor;
    //Servo buttonServo;
    //Servo climberServo;

    @Override
    public void runOpMode() throws InterruptedException {

        //Init (Locates Devices)
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");

        //colorSensor = hardwareMap.colorSensor.get("sensor_color");
        //buttonServo = hardwareMap.servo.get("servo_button");
        //climberServo = hardwareMap.servo.get("servo_climber");

        //Reverses the direction of the left motor
        leftMotor.setDirection(DcMotor.Direction.REVERSE);

        leftMotor.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        rightMotor.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);

        //Wait for start of round
        telemetry.addData("waiting for start...", "");
        waitForStart();

        COUNTS = ENCODER_CPR * 1; //Encouder CPR * rotations

        telemetry.addData("check 1", "");
        leftMotor.setTargetPosition((int) COUNTS);
        rightMotor.setTargetPosition((int) COUNTS);

        telemetry.addData("check 2", "");
        runToPosition();
        setSpeed(0.75);
        while(leftMotor.getCurrentPosition() != COUNTS){
            wait(1);
        }

        telemetry.addData("check 3", "");
        COUNTS = ENCODER_CPR * 1; //Encouder CPR * rotations

        telemetry.addData("check 4", "");
        leftMotor.setTargetPosition((int) COUNTS);
        rightMotor.setTargetPosition((int) COUNTS * 2);

        telemetry.addData("check 5", "");
        runToPosition();
        setSpeed(0.75);
        while(rightMotor.getCurrentPosition() != COUNTS){
            wait(1);
        }


        /*
        //Dumps the climber into the bucket
        climberServo.setPosition(50);

        //Detects the color of the Beacon and then determins which button to press
        if(colorSensor.red() > 100){
            buttonServo.setPosition(100);
        }
        else{
            buttonServo.setPosition(50);
        } */

    }

    public void runToPosition(){
        telemetry.addData("setTargetPosition", "");
        leftMotor.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        rightMotor.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
    }

    public void setSpeed(double speed){
        telemetry.addData("setSpeed", "");
        leftMotor.setPower(speed);
        rightMotor.setPower(speed);
    }


}
