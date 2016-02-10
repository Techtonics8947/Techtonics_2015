package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by colin on 1/14/16.
 */
public class AutoBlue10First extends autoBase {

    @Override
    public void runOpMode() throws InterruptedException {


        autonomous10(Blue, NoSleep);

    }

}
