package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.robotcore.hardware.Servo;


/**
 * Created by colin on 10/21/15.
 */
public class colorSensor extends OpMode {

    ModernRoboticsI2cColorSensor color;
    Servo buttonServo;

    final static int Red = 0;
    final static int Blue = 360;

    @Override
    public void init(){

        color = (ModernRoboticsI2cColorSensor) hardwareMap.colorSensor.get("sensor_color");
        buttonServo = hardwareMap.servo.get("climber_drop");
        color.enableLed(false);

    }

    @Override
    public void loop(){

        colorSensor(Blue);

    }

    public void colorSensor(int colorVal){
        color.enableLed(false);
        double redColor = color.red();
        telemetry.addData("Red = ", redColor);

        double blueColor = color.blue();
        telemetry.addData("Blue = ", blueColor);

        if(colorVal == Blue){
            if(blueColor > redColor){
                buttonServo.setPosition(1.0);
            }
            else if(redColor > blueColor){
                buttonServo.setPosition(0.0);
            }
            else{
                buttonServo.setPosition(0.5);
            }
        }
        if(colorVal == Red){
            if(redColor > blueColor){
                buttonServo.setPosition(1.0);
            }
            else if(blueColor > redColor){
                buttonServo.setPosition(0.0);
            }
            else{
                buttonServo.setPosition(0.5);
            }
        }
    }

}