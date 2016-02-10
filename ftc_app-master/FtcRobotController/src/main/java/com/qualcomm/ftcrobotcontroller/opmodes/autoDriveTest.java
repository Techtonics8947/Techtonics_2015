package com.qualcomm.ftcrobotcontroller.opmodes;


/**
 * Created by colin on 1/19/16.
 */
public class autoDriveTest extends autoBase {

    @Override
    public void runOpMode() throws InterruptedException {
        InitializeHardware();
        double baselineColor;
        double drivingColor;
        baselineColor = sensorLine.getLightDetected();
        LogMsg("Initial Line Color: " + Double.toString(baselineColor));
        boolean stopYet = false;
        setDriveMotors(FULL_POWER);
        while (!stopYet){
            drivingColor = sensorLine.getLightDetected();
            LogMsg("Initial Line Color: " + Double.toString(drivingColor));
            if (drivingColor > 0.8)
                stopYet = true;
            sleep(50);
        }
        setDriveMotors(POWER_OFF);

        Turn(315);

    }


}
