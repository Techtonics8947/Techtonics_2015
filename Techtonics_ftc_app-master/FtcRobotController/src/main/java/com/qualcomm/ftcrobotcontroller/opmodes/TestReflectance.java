package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

/**
 * Created by Colin Santee on 10/10/15.
 */

public class TestReflectance extends OpMode {

    OpticalDistanceSensor opticalDistanceSensor;

    @Override
    public void init(){

        //Locates the EOPD (Electro Optical Proximity Detector) from the hardware map
        opticalDistanceSensor = hardwareMap.opticalDistanceSensor.get("sensor_ODS");

    }

    @Override
    public void loop(){

        //Measures the amount of reflectance and assignes it to a variable
        double reflectance = opticalDistanceSensor.getLightDetected();

        //Sends the variable to the phone as telemetry
        telemetry.addData("Reflectance", reflectance);

    }

}
