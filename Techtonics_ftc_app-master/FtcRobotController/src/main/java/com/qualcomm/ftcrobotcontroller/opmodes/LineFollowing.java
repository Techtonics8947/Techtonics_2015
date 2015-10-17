package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

/**
 * Created by Colin Santee on 10/17/15.
 */

public class LineFollowing extends OpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;
    OpticalDistanceSensor opticalDistanceSensor;

    @Override
    public void init(){

        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");

        leftMotor.setDirection(DcMotor.Direction.REVERSE);

        opticalDistanceSensor = hardwareMap.opticalDistanceSensor.get("sensor_ODS");

    }

    @Override
    public void loop(){

        double reflectance = opticalDistanceSensor.getLightDetected();

        if (reflectance >= 0.25){
            leftMotor.setPower(-0.2);
            rightMotor.setPower(0);
        }

        else {
            leftMotor.setPower(0);
            rightMotor.setPower(-0.2);
            telemetry.addData("Reflectance", reflectance);
        }

    }


}
