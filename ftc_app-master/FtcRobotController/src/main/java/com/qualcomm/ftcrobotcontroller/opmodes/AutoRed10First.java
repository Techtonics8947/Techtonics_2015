package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Colin Santee on 10/14/15.
 */

public class AutoRed10First extends autoBase{

    @Override
    public void runOpMode() throws InterruptedException {

        autonomous10(Red, NoSleep);

    }

}