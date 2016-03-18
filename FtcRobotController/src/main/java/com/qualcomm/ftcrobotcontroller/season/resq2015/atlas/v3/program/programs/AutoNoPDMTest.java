package com.qualcomm.ftcrobotcontroller.season.resq2015.atlas.v3.program.programs;

import com.qualcomm.ftcrobotcontroller.opMode.DriverControlledProgram;
import com.qualcomm.ftcrobotcontroller.opMode.annotation.Program;
import com.qualcomm.ftcrobotcontroller.robot.Robot;

/**
 * Created by jaxon on 11/27/15.
 */
@Program(name="NPDMTest", enabled = false)
public class AutoNoPDMTest extends DriverControlledProgram {
    @Override
    protected Robot buildRobot() {
        return new Robot(this) {};
    }


}
