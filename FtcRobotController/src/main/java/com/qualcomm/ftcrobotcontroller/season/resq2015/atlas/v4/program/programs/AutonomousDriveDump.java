package com.qualcomm.ftcrobotcontroller.season.resq2015.atlas.v4.program.programs;

import com.qualcomm.ftcrobotcontroller.opMode.annotation.Program;
import com.qualcomm.ftcrobotcontroller.season.resq2015.atlas.v4.program.AtlasAutonomousProgram;

/**
 * @Author Jaxon Brown
 */
@Program(name = "Auto DriveDump", enabled = true)
public class AutonomousDriveDump extends AtlasAutonomousProgram {
    @Override
    public void main() {
        sweeper.sweepOut();

        drive.drive(0.93, 1);

        waitFor(5000);

        drive.stop();

        sweeper.sweepStop();

        climberShelterArm.climberArmOut();

        waitFor(1500);

        climberShelterArm.setClimberArm(0.2);

        waitFor(500);

        climberShelterArm.climberArmStaticPos();

        waitFor(250);

        //drive.drive(-1, -1);
        //waitFor(2500);
        //drive.drive(1, -1);
        //waitFor(1000);
        //drive.stop();
        //sweeper.sweepStop();
    }
}
