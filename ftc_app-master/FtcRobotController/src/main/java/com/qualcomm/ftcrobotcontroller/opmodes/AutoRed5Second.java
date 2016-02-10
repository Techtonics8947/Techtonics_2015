package com.qualcomm.ftcrobotcontroller.opmodes;

/**
 * Created by Colin Santee on 10/14/15.
 */

public class AutoRed5Second extends autoBase {

    @Override
    public void runOpMode() throws InterruptedException {
        autonomous5(Red, Sleep);
    }
}