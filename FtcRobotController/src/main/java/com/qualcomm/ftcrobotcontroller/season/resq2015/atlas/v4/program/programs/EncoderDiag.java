package com.qualcomm.ftcrobotcontroller.season.resq2015.atlas.v4.program.programs;

import com.qualcomm.ftcrobotcontroller.opMode.annotation.Program;
import com.qualcomm.ftcrobotcontroller.season.resq2015.atlas.v4.program.AtlasAutonomousProgram;

/**
 * Created by jaxon on 1/14/16.
 */
@Program(name = "Enc Diag", enabled = true)
public class EncoderDiag extends AtlasAutonomousProgram {
    @Override
    public void main() {
        drive.resetEncoder();
        drive.drive(1, 1);
        while (opModeIsActive()) {
            telemetry.addData("enc", drive.getEncoder());
        }
    }
}
