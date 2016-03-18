package com.qualcomm.ftcrobotcontroller.season.resq2015.atlas.v4.program.programs;

import com.qualcomm.ftcrobotcontroller.opMode.annotation.Program;
import com.qualcomm.ftcrobotcontroller.robot.Robot;

/**
 * Created by jaxon on 1/13/16.
 */
@Program(name = "Teleop Practice", enabled = true)
public class TeleopPractice extends Teleop {
    @Override
    public Robot buildRobot() {
        disableTimer();
        return super.buildRobot();
    }
}
