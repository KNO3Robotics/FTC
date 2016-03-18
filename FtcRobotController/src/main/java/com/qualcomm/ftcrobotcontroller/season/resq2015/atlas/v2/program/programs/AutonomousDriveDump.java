package com.qualcomm.ftcrobotcontroller.season.resq2015.atlas.v2.program.programs;

import com.qualcomm.ftcrobotcontroller.opMode.annotation.Program;
import com.qualcomm.ftcrobotcontroller.season.resq2015.atlas.v2.program.AtlasAutonomousProgram;

/**
 * @Author Jaxon Brown
 */
@Program(name = "Auto DriveDump", enabled = false)
public class AutonomousDriveDump extends AtlasAutonomousProgram {
    @Override
    public void main() {
        sweeper.sweepOut();

        drive.drive(1, 1);

        waitFor(5000);

        drive.stop();

        climberShelterArm.climberArmOut();

        waitFor(1000);

        drive.drive(-1, -1);

        waitFor(1900);

        drive.drive(1, -1);

        waitFor(1000);

        drive.stop();

        sweeper.sweepStop();
    }
}
