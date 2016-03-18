package com.qualcomm.ftcrobotcontroller.season.resq2015.atlas.v2.robot;

import com.qualcomm.ftcrobotcontroller.robot.Robot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/**
 * Atlas.
 *
 * Controls:
 * Drive:
 *  - GP1 Left Trigger = Reverse
 *  - GP1 Right
 */
public class Atlas extends Robot {
    public Atlas(OpMode opMode) {
        super(opMode);

        putSubSystem("Drive", new Drive(this));
        putSubSystem("Lift", new Lift(this));
        putSubSystem("Sweeper", new Sweeper(this));
        putSubSystem("Box", new Box(this));
        putSubSystem("ZiplineLatchers", new ZiplineLatchers(this));
        putSubSystem("ClimberShelterArm", new ClimberShelterArm(this));
    }
}
