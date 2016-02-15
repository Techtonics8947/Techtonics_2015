package com.qualcomm.ftcrobotcontroller.opmodes;

/**
 * Created by colin on 2/11/16.
 */
public class AutoRedLineFirst extends autoBase {

    @Override
    public void runOpMode() throws InterruptedException{
        autonomousLine(Red, NoSleep);
    }


}
