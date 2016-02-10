package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;


/**
 * Created by colin on 10/21/15.
 */
public class colorSensor extends OpMode {

    ModernRoboticsI2cColorSensor color;

    @Override
    public void init(){

        color = (ModernRoboticsI2cColorSensor) hardwareMap.colorSensor.get("sensor_color");
        color.enableLed(false);

    }

    @Override
    public void loop(){

        double red = color.red();
        telemetry.addData("Red = ", red);

        double blue = color.blue();
        telemetry.addData("Blue = ", blue);

    }

}