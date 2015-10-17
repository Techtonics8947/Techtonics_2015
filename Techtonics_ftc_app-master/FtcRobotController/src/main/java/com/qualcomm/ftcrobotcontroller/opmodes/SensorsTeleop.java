package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.TouchSensor;


 /**
 * Created by colin on 10/14/15.
 */
public class SensorsTeleop extends LinearOpMode {

    OpticalDistanceSensor opticalDistanceSensor;

    TouchSensor touchSensor;
    DcMotor leftMotor;
    DcMotor rightMotor;

    @Override
    public void runOpMode() throws InterruptedException {

        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");

        leftMotor.setDirection(DcMotor.Direction.REVERSE);

        opticalDistanceSensor = hardwareMap.opticalDistanceSensor.get("sensor_ODS");
        touchSensor = hardwareMap.touchSensor.get("sensor_touch");

/*        boolean wait = true;
        while (wait) {
            wait = !touchSensor.isPressed();
            sleep(100);
        }
        */

        waitForStart();

        rightMotor.setPower(1.0);
        leftMotor.setPower(1.0);
        sleep(2200);

        leftMotor.setPower(0);
        rightMotor.setPower(0);

        }

}
