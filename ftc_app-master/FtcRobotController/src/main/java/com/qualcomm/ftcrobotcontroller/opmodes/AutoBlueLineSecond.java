package com.qualcomm.ftcrobotcontroller.opmodes;

/**
 * Created by colin on 2/11/16.
 */

public class AutoBlueLineSecond extends autoBase{

    @Override
    public void runOpMode() throws InterruptedException{

        double light = sensorLine.getLightDetected();

        telemetry.addData("Light Detected", light);

    }

}
