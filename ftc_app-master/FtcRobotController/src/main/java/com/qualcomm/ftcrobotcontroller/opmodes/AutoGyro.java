package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.hardware.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by colin on 1/14/16.
 */
public class AutoGyro extends autoBase {

    ModernRoboticsI2cGyro sensorGyro;

    @Override
    public void runOpMode() throws InterruptedException {

        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");

        armLeft = hardwareMap.dcMotor.get("arm_left");
        armRight = hardwareMap.dcMotor.get("arm_right");

        sensorGyro = (ModernRoboticsI2cGyro) hardwareMap.gyroSensor.get("gyro");

        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        armLeft.setDirection(DcMotor.Direction.REVERSE);

        //autonomous(Red, NoSleep);

        sensorGyro.calibrate();

        waitForStart();

        while (sensorGyro.isCalibrating()) {
            Thread.sleep(50);
        }

        telemetry.addData("Heading ", sensorGyro.getHeading());
        telemetry.addData("Integrated ", sensorGyro.getIntegratedZValue());

        setMotors(1);
        sleep(250);
        setMotors(0);

        telemetry.addData("Heading ", sensorGyro.getHeading());
        telemetry.addData("Integrated ", sensorGyro.getIntegratedZValue());

        turn(90);

        telemetry.addData("Heading ", sensorGyro.getHeading());
        telemetry.addData("Integrated ", sensorGyro.getIntegratedZValue());

        sleep(10000);

        turn(287);

        telemetry.addData("Heading ", sensorGyro.getHeading());
        telemetry.addData("Integrated ", sensorGyro.getIntegratedZValue());

        sleep(10000);

        turn(0);

        telemetry.addData("Heading ", sensorGyro.getHeading());
        telemetry.addData("Integrated ", sensorGyro.getIntegratedZValue());

        sleep(10000);

    }

    private void turn(int target) throws InterruptedException{

        for (int count = 0; count < 2; count++)
        {
            int reverse = 1;

            int heading = sensorGyro.getHeading();
            int timeToTurn = 0;
            int degreesToTurn = Math.abs(heading - target); //|0 - 90| = 90

            if (heading < target) //0 < 90 = True
            {
                reverse = -1;
            }

            if (degreesToTurn > 180) { // 90 > 180 = False
                degreesToTurn = 360 - degreesToTurn;
                reverse = reverse * -1;
            }

            timeToTurn = degreesToTurn * 10; // 90 * 10 = 900
            leftMotor.setPower(1.0 * reverse); //1.0
            rightMotor.setPower(-1.0 * reverse); //-1.0
            sleep(timeToTurn); //900
            setMotors(0); //0

            sleep(1000);
        }
    }


}
