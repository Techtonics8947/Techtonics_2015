package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 * Created by Colin Santee on 10/17/15.
 */

public class LineFollowing extends OpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;
    OpticalDistanceSensor opticalDistanceSensor;
    TouchSensor touchSensor;

    double floorReflectance = 0.0;
    double lineReflectance = 0.0;
    int touchMode = 0;
    boolean drive = false;

    @Override
    public void init(){

        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");

        leftMotor.setDirection(DcMotor.Direction.REVERSE);

        opticalDistanceSensor = hardwareMap.opticalDistanceSensor.get("sensor_ODS");
        touchSensor = hardwareMap.touchSensor.get("sensor_touch");

    }

    @Override
    public void loop(){

        if (touchMode == 0){

            telemetry.addData("Set the floor reflectance", null);

        }
        if (touchMode == 1){

            telemetry.addData("Set the line reflectance", null);

        }

        if (touchSensor.isPressed() && touchMode == 0){

            touchMode = 1;

            floorReflectance = opticalDistanceSensor.getLightDetected();

        }



        if (touchSensor.isPressed() && touchMode == 1){

            touchMode = 2;

            lineReflectance = opticalDistanceSensor.getLightDetected();

            telemetry.addData("Line Reflectance", lineReflectance);

            drive = true;

        }

        while (drive = true){

            double reflectance = opticalDistanceSensor.getLightDetected();

            if (reflectance == lineReflectance){

                leftMotor.setPower(0.5);
                rightMotor.setPower(0.5);

            }
            if (reflectance == floorReflectance){

                leftMotor.setPower(0.0);
                rightMotor.setPower(0.25);

            }

        }

    }

}
