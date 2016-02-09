package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.hardware.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Colin Santee on 10/14/15.
 */

public class AutoBlue5Second extends autoBase {

    @Override
    public void runOpMode() throws InterruptedException {
        autonomous5(Blue, Sleep);
    }
}