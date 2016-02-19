package com.qualcomm.ftcrobotcontroller.opmodes;

/**
 * Created by colin on 2/11/16.
 */
public class AutoBlueLineFirst extends autoBase {

    @Override
    public void runOpMode() throws InterruptedException{
        LogMsg("***************** LAUNCHED OPMODE ************************");
        autonomousLine(Blue, NoSleep);
    }

}
