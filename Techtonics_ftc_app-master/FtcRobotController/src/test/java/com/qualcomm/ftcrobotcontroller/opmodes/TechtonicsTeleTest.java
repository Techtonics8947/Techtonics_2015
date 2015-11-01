package com.qualcomm.ftcrobotcontroller.opmodes;

import android.os.Build;

import com.qualcomm.ftcrobotcontroller.BuildConfig;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;

/**
 * Created by rexsantee on 10/31/15.
 */

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.KITKAT)
@RunWith(RobolectricGradleTestRunner.class)

public class TechtonicsTeleTest {

    TechtonicsTele opMode;
    DcMotor leftMotor;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testInit() throws Exception {
        assertTrue (1 == 1);
    }

    @Test
    public void testLoop() throws Exception {
        assertTrue (1 == 1);
    }
}