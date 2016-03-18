package com.qualcomm.ftcrobotcontroller.season.resq2015.atlas.v4.program.programs;

import com.qualcomm.ftcrobotcontroller.opMode.annotation.Program;
import com.qualcomm.ftcrobotcontroller.season.resq2015.atlas.v4.program.AtlasAutonomousProgram;

/**
 * @Author Jaxon Brown
 */
@Program(name = "Auto DumpRamp", enabled = false)
public class AutonomousDumpRamp extends AtlasAutonomousProgram {
    @Override
    public void main() {
        sweeper.sweepOut();

        drive.drive(1, 1);

        waitFor(5000);

        drive.stop();

        climberShelterArm.climberArmOut();

        waitFor(1000);

        drive.drive(-1, -1);

        waitFor(2500);

        drive.stop();

        waitFor(500);

        drive.drive(-1, 1);

        waitFor(1000);

        drive.stop();

        waitFor(500);

        drive.drive(-1, -1);

        waitFor(3000);

        churroSnatcher.lock();

        drive.stop();

        sweeper.sweepStop();

    }
}
