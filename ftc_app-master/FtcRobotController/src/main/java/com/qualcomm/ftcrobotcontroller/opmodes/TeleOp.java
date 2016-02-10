package com.qualcomm.ftcrobotcontroller.opmodes;

import android.util.Log;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Jonathan on 11/18/15.
 */

public class TeleOp extends TechtonicsTele {

    DcMotor leftMotor;
    DcMotor rightMotor;

    DcMotor arm;

   // Servo gripper;
    Servo bucket;
    Servo climber_left;
    Servo climber_right;
    Servo climber_drop;

    DcMotor mountainAssist;

    ModernRoboticsI2cGyro sensorGyro;

    DcMotor extAngleLeft;
    DcMotor extAngleRight;

    DcMotor extensionLeft;
    DcMotor extensionRight;
    boolean FirstTimeRun;

    @Override
    public void init() {
        locateMotors();

        arm = hardwareMap.dcMotor.get("arm");

        //gripper = hardwareMap.servo.get("servo_gripper");

        bucket = hardwareMap.servo.get("servo_bucket");

        climber_left = hardwareMap.servo.get("climber_left");
        climber_right = hardwareMap.servo.get("climber_right");

        sensorGyro = (ModernRoboticsI2cGyro) hardwareMap.gyroSensor.get("gyro");

        climber_drop = hardwareMap.servo.get("climber_drop");

        extAngleLeft = hardwareMap.dcMotor.get("angle_left");
        extAngleRight = hardwareMap.dcMotor.get("angle_right");

        extensionLeft = hardwareMap.dcMotor.get("extension_left");
        extensionRight = hardwareMap.dcMotor.get("extension_right");

        mountainAssist = hardwareMap.dcMotor.get("mountain_assist");

        arm.setDirection(DcMotor.Direction.REVERSE);
        extAngleLeft.setDirection(DcMotor.Direction.REVERSE);
        FirstTimeRun = true;
    }

    @Override
    public void loop() {

        telemetry.addData("Time = ", getRuntime());

        //  This routine is ensure we don't have errors coming from controller before we start.
        if (FirstTimeRun) {
            if (CheckForButtonStick()) {
                this.stop();
            }
            FirstTimeRun = false;
        }

        updateMotors();

        //Flip the arm clockwise or counterclockwise
        if (gamepad1.right_bumper) {
            LogMsg("gp1: right trigger - Arm Down");
            arm.setPower(0.25);
        } else if (gamepad1.right_trigger > 0.25) {
            LogMsg("gp1: right bumper - Arm Up");
            arm.setPower(-0.25);
        } else {
            arm.setPower(0);
        }

        /*Sets the gripper Position
        if (gamepad1.left_trigger > 0.25) {
            LogMsg("gp1: left trigger - Gripper Close");
            gripper.setPosition(1);      //Set to open position
        } else if (gamepad1.left_bumper) {
            LogMsg("gp1: left bumper - Gripper Open");
            gripper.setPosition(0);     //Set to closed position
        } else {
            gripper.setPosition(0.50);
        } */


        //controls the position of the mountain assist
        double timeToRun = 1.0;
        double startTime;
        double endTime;
        if (gamepad2.right_bumper) {
            LogMsg("gp2: right_bumper - Lower mountain_assist");
            startTime = getRuntime();
            endTime = startTime + timeToRun;
            if(getRuntime() < endTime){
                mountainAssist.setPower(0.15);
            }
            else{
                mountainAssist.setPower(0);
                mountainAssist.setPowerFloat();
            }
        }
        else {
            startTime = getRuntime();
            endTime = startTime + timeToRun;
            if(getRuntime() < endTime){
                mountainAssist.setPower(-0.15);
            }
            else{
                mountainAssist.setPower(0);
                mountainAssist.setPowerFloat();
            }
        }

        if (gamepad1.y) {
            LogMsg("gp1: Y Button - climber dropper");
            climber_drop.setPosition(0);
        } else {
            climber_drop.setPosition(1.0);
        }

        if(gamepad1.x) {
            climber_left.setPosition(0.6);
        }
        if(gamepad1.dpad_left){
            climber_left.setPosition(0);
        }

        if(gamepad1.dpad_right){
            climber_right.setPosition(1);
        }
        if (gamepad1.b) {
            climber_right.setPosition(0.5);
        }

        //Sets extension motor with gamepad2 left stick
        double extensionPress = -gamepad2.left_stick_y;
        if (extensionPress > 0.25) {
            LogMsg("gp2: left joysick - Extension Out");
            extensionLeft.setPower(extensionPress);
            extensionRight.setPower(extensionPress);
        } else if (extensionPress < -0.25) {
            LogMsg("gp2: left joysick - Extension In");
            extensionLeft.setPower(extensionPress);
            extensionRight.setPower(extensionPress);
        } else {
            extensionLeft.setPower(0.0);
            extensionRight.setPower(0.0);
        }

        //Sets the angle servos
        double anglePress = gamepad2.right_stick_y;
        if (anglePress > .25) {
            LogMsg("gp2: right joysick - Angle Up");
            if (anglePress > 0.6) anglePress = 0.6;
            extAngleLeft.setPower(anglePress);
            extAngleRight.setPower(anglePress);
        } else if (anglePress < -0.25) {
            LogMsg("gp2: right joysick - Angle Down");
            if (anglePress < -0.6) anglePress = -0.6;
            extAngleLeft.setPower(anglePress);
            extAngleRight.setPower(anglePress);
        } else {
            extAngleLeft.setPower(0.00);
            extAngleRight.setPower(0.00);
        }

        //Operates the bucket with the DPad on the second gamepad
        if (gamepad2.dpad_left) {
            LogMsg("gp2: DPad - Bucket Left");
            bucket.setPosition(1.0);
        } else if (gamepad2.dpad_right) {
            LogMsg("gp2: DPad - Bucket Right");
            bucket.setPosition(0.0);
        } else {
            bucket.setPosition(0.5);
        }
    }
    private boolean CheckForButtonStick()
    {
        boolean ErrorFound = false;
        //Flip the arm clockwise or counterclockwise
        if (gamepad1.right_bumper) {
            telemetry.addData("ERROR:  BUTTON PRESSED:", " gp1: right trigger - Arm Down");
            LogMsg("gp1: right trigger - Arm Down");
            ErrorFound = true;
        } else if (gamepad1.right_trigger > 0.25) {
            telemetry.addData("ERROR:  BUTTON PRESSED:", " gp1: right bumper - Arm Up");
            LogMsg("gp1: right bumper - Arm Up");
            ErrorFound = true;
        }

        //Sets the gripper Position
        if (gamepad1.left_trigger > 0.25) {
            telemetry.addData("ERROR:  BUTTON PRESSED:", " gp1: left trigger - Gripper Close");
            LogMsg("gp1: left trigger - Gripper Close");
            ErrorFound = true;
        } else if (gamepad1.left_bumper) {
            telemetry.addData("ERROR:  BUTTON PRESSED:", " gp1: left bumper - Gripper Open");
            LogMsg("gp1: left bumper - Gripper Open");
            ErrorFound = true;
        }

        //controls the position of the mountain assist
        if (gamepad2.left_bumper) {
            telemetry.addData("ERROR:  BUTTON PRESSED:", " gp2: left bumper - Lower mountain_assist");
            LogMsg("gp2: left bumper - Lower mountain_assist");
            ErrorFound = true;
        }

        //Sets extension motor with gamepad2 left stick
        double extensionPress = -gamepad2.left_stick_y;
        if (extensionPress > 0.25) {
            telemetry.addData("ERROR:  BUTTON PRESSED:", " gp2: left joysick - Extension Out");
            LogMsg("gp2: left joysick - Extension Out");
            ErrorFound = true;
        } else if (extensionPress < -0.25) {
            telemetry.addData("ERROR:  BUTTON PRESSED:", " gp2: left joysick - Extension In");
            LogMsg("gp2: left joysick - Extension In");
            ErrorFound = true;
        }

        //Sets the angle servos
        double anglePress = gamepad2.right_stick_y;
        if (anglePress > .25) {
            telemetry.addData("ERROR:  BUTTON PRESSED:", " gp2: right joysick - Angle Up");
            LogMsg("gp2: right joysick - Angle Up");
            ErrorFound = true;
        } else if (anglePress < -0.25) {
            telemetry.addData("ERROR:  BUTTON PRESSED:", " gp2: right joysick - Angle Down");
            LogMsg("gp2: right joysick - Angle Down");
            ErrorFound = true;
        }

        //Operates the bucket with the DPad on the second gamepad
        if (gamepad2.dpad_left) {
            telemetry.addData("ERROR:  BUTTON PRESSED:", " gp2: DPad - Bucket Left");
            LogMsg("gp2: DPad - Bucket Left");
            ErrorFound = true;
        } else if (gamepad2.dpad_right) {
            telemetry.addData("ERROR:  BUTTON PRESSED:", " gp2: DPad - Bucket Right");
            LogMsg("gp2: DPad - Bucket Right");
            ErrorFound = true;
        }

        if (gamepad2.left_trigger > 0.25) {
            telemetry.addData("ERROR:  BUTTON PRESSED:", " gp2: left trigger - climbers");
            LogMsg("gp2: left trigger - climbers");
            ErrorFound = true;
        }
        return ErrorFound;
    }
}