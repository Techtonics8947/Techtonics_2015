package com.qualcomm.ftcrobotcontroller.opmodes;

import android.graphics.Color;
import android.text.method.Touch;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 * Created by colin on 10/21/15.
 */
public class colorSensor extends OpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;
    TouchSensor touchSensor;
    ColorSensor colorSensor;
    int touchMode = 0;
    double lineColor;
    double floorColor;
    boolean drive;

    @Override
    public void init(){

        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");

        colorSensor = hardwareMap.colorSensor.get("sensor_color");
        touchSensor = hardwareMap.touchSensor.get("sensor_touch");

        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        colorSensor.enableLed(true);

    }

    @Override
    public void loop(){
        colorSensor.enableLed(true);
        int red = colorSensor.red();
        telemetry.addData("The red is", red);


        int blue = colorSensor.blue();
        telemetry.addData("The blue is", blue);

    }

}