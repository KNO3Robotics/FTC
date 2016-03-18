package com.qualcomm.ftcrobotcontroller.season.resq2015.atlas.v3.program.programs;

import com.qualcomm.ftcrobotcontroller.opMode.DriverControlledProgram;
import com.qualcomm.ftcrobotcontroller.opMode.annotation.Program;
import com.qualcomm.ftcrobotcontroller.robot.Robot;
import com.qualcomm.ftcrobotcontroller.season.resq2015.atlas.v3.robot.Atlas;

/**
 * Created by jaxon on 12/18/15.
 */
@Program(name = "Teleop", enabled = false)
public class Teleop extends DriverControlledProgram {
    private Atlas atlas;

    @Override
    protected Robot buildRobot() {
        atlas = new Atlas(this);

        return atlas;
    }
}
