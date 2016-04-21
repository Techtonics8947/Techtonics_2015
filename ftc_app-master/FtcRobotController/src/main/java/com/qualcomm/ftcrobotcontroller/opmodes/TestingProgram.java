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

    public void init(){
        locateMotors();

        dumper = hardwareMap.servo.get("servo_bucket");
    }

    public void loop(){
        updateMotors();



        if(gamepad1.a) {
            dumper.setPosition(1.0);
        }else{
            dumper.setPosition(0);
        }
    }
}
