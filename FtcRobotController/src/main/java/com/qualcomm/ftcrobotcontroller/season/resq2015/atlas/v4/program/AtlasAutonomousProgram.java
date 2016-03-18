package com.qualcomm.ftcrobotcontroller.season.resq2015.atlas.v4.program;

import com.qualcomm.ftcrobotcontroller.opMode.AutonomousProgram;
import com.qualcomm.ftcrobotcontroller.robot.Robot;
import com.qualcomm.ftcrobotcontroller.season.resq2015.atlas.v4.robot.*;

/**
 * @Author Jaxon Brown
 */
public abstract class AtlasAutonomousProgram extends AutonomousProgram {
    protected Drive drive;
    protected Lift lift;
    protected Sweeper sweeper;
    protected Box box;
    protected ZiplineLatchers ziplineLatchers;
    protected ClimberShelterArm climberShelterArm;
    protected ChurroSnatcher churroSnatcher;

    @Override
    protected Robot buildRobot() {
        Atlas atlas = new Atlas(this);

        drive = (Drive) atlas.getSubSystem("Drive");
        lift = (Lift) atlas.getSubSystem("Lift");
        sweeper = (Sweeper) atlas.getSubSystem("Sweeper");
        box = (Box) atlas.getSubSystem("Box");
        ziplineLatchers = (ZiplineLatchers) atlas.getSubSystem("ZiplineLatchers");
        climberShelterArm = (ClimberShelterArm) atlas.getSubSystem("ClimberShelterArm");
        churroSnatcher = (ChurroSnatcher) atlas.getSubSystem("ChurroSnatcher");

        return atlas;
    }
}
