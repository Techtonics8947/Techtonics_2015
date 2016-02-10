package com.qualcomm.ftcrobotcontroller.opmodes;

/**
 * Created by Colin Santee on 10/14/15.
 */

public class AutoBlue5First extends autoBase {

    @Override
    public void runOpMode() throws InterruptedException {
        autonomous5(Blue, NoSleep);
    }
}