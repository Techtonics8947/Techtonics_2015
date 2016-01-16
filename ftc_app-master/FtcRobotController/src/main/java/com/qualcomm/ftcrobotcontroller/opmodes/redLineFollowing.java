package com.qualcomm.ftcrobotcontroller.opmodes;

    import com.qualcomm.robotcore.eventloop.opmode.OpMode;
    import com.qualcomm.robotcore.hardware.ColorSensor;
    import com.qualcomm.robotcore.hardware.DcMotor;
    import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
    import com.qualcomm.robotcore.hardware.TouchSensor;
    import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by colin on 10/24/15.
 */

public class redLineFollowing extends OpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;
    ColorSensor colorSensor;
    TouchSensor touchSensor;
    OpticalDistanceSensor opticalDistanceSensor;
    ElapsedTime time;

    enum State {line, turningRight, turningLeft};
    State  state;

    int color = 0;

    @Override
    public void init() {

        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");

        colorSensor = hardwareMap.colorSensor.get("sensor_color");
        touchSensor = hardwareMap.touchSensor.get("sensor_touch");
        opticalDistanceSensor = hardwareMap.opticalDistanceSensor.get("sensor_ODS");

        time = new ElapsedTime();

        state = State.line;

    }

    @Override
    public void loop(){

        color = colorSensor.red();

        double currentTime = time.time();
        /*
        switch(state){

            case line:

                leftMotor.setPower(0.5);
                rightMotor.setPower(0.5);

                telemetry.addData("Robot is over: ", "Line");

                break;

            case turningLeft:

                leftMotor.setPower(0.5);
                rightMotor.setPower(0.25);

                telemetry.addData("Robot is over: ", "Floor");

                break;

            case turningRight:

                leftMotor.setPower(0.25);
                rightMotor.setPower(0.5);

                telemetry.addData("Robot is over: ", "Floor");

        }*/

        if (color >= 100){

            state = State.line;

            leftMotor.setPower(0.5);
            rightMotor.setPower(0.5);

            telemetry.addData("Robot is over: ", "Line");

        }

        else {

            state = State.turningLeft;

            leftMotor.setPower(0.25);
            rightMotor.setPower(0.5);

            telemetry.addData("Robot is over", "Floor");

        }

    }

}
