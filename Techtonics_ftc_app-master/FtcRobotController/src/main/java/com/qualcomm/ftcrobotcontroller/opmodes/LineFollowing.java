package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 * Created by student on 10/10/15.
 */
public class LineFollowing extends OpMode {

    DcMotor leftDrive;
    DcMotor rightDrive;
    TouchSensor touchSensor;
    OpticalDistanceSensor opticalDistanceSensor;

    @Override
    public void init(){

        leftDrive = hardwareMap.dcMotor.get("left_drive");
        rightDrive = hardwareMap.dcMotor.get("right_drive");
        touchSensor = hardwareMap.touchSensor.get("sensor_touch")
        opticalDistanceSensor = hardwareMap.opticalDistanceSensor.get("sensor_EOPD");

    }

    @Override
    public void loop(){



    }


}
