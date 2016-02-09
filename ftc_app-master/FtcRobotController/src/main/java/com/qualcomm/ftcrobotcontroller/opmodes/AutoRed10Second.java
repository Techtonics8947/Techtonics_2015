package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.hardware.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by colin on 1/14/16.
 */
public class AutoRed10Second extends autoBase {

    @Override
    public void runOpMode() throws InterruptedException {

        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");

        armLeft = hardwareMap.dcMotor.get("arm_left");
        armRight = hardwareMap.dcMotor.get("arm_right");

        sensorGyro = (ModernRoboticsI2cGyro) hardwareMap.gyroSensor.get("gyro");

        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        armLeft.setDirection(DcMotor.Direction.REVERSE);

        autonomous10(Red, Sleep);

    }
}
