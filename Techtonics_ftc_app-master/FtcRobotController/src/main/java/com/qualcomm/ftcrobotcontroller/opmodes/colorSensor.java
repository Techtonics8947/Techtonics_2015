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

    }

    @Override
    public void loop(){

        if (touchMode == 0){

            telemetry.addData("Set the floor color", null);

        }
        if (touchMode == 1){

            telemetry.addData("Set the line reflectance", null);

        }

        if (touchSensor.isPressed() && touchMode == 0){

            touchMode = 1;

            floorColor = colorSensor.red();

            telemetry.addData("Floor Color", floorColor);

        }



        if (touchSensor.isPressed() && touchMode == 1){

            touchMode = 2;

            lineColor = colorSensor.red();

            telemetry.addData("Line Color", lineColor);

            drive = true;

        }

        while (drive = true){

            double color = colorSensor.red();

            if (color == lineColor){

                leftMotor.setPower(0.5);
                rightMotor.setPower(0.5);

            }
            if (color == floorColor){

                leftMotor.setPower(0.0);
                rightMotor.setPower(0.25);

            }

        }

    }

}
