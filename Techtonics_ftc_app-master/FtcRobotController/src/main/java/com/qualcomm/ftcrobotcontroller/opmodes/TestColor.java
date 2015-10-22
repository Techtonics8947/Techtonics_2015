package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;

/**
 * Created by colin on 10/21/15.
 */
public class TestColor extends OpMode {

    ColorSensor colorSensor;

    @Override
    public void init(){

        colorSensor = hardwareMap.colorSensor.get("sensor_color");

    }

    @Override
    public void loop(){

        double color = colorSensor.red();
        telemetry.addData("Color", color);

    }

}
