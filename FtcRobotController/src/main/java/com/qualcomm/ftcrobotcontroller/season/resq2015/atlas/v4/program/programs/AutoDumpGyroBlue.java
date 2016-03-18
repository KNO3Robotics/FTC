package com.qualcomm.ftcrobotcontroller.season.resq2015.atlas.v4.program.programs;

import com.qualcomm.ftcrobotcontroller.opMode.annotation.Program;
import com.qualcomm.ftcrobotcontroller.robot.Robot;
import com.qualcomm.ftcrobotcontroller.season.resq2015.atlas.v4.program.AtlasAutonomousProgram;
import com.qualcomm.ftcrobotcontroller.util.Condition;
import com.qualcomm.robotcore.hardware.GyroSensor;

/**
 * @author Jaxon A Brown
 */
@Program(name = "Auto DumpGyro BLUE", enabled = true)
public class AutoDumpGyroBlue extends AtlasAutonomousProgram {
    protected GyroSensor gyroSensor;
    @Override
    public Robot buildRobot() {
        Robot robot = super.buildRobot();
        this.gyroSensor = hardwareMap.gyroSensor.get("gyro");
        gyroSensor.calibrate();
        return robot;
    }

    @Override
    public void main() {
        waitFor(new Condition() {
            @Override
            public boolean get() {
                return !gyroSensor.isCalibrating();
            }
        });

        telemetry.addData("Ready", "READY!!!!");

        sweeper.sweepOut();

        drive.drive(1, 1);

        waitFor(1200);

        drive.stop();

        waitFor(500);

        gyroSensor.resetZAxisIntegrator();

        telemetry.addData("start h", gyroSensor.getHeading());

        drive.drive(-.40, .40);

        waitFor(new Condition() {
            int targetMin = 360 - 33;
            @Override
            public boolean get() {
                int head = gyroSensor.getHeading();
                telemetry.addData("head", head);
                return head < targetMin && head > 180;
            }
        });

        drive.stop();

        waitFor(500);

        gyroSensor.resetZAxisIntegrator();

        //drive(5000);

        drive.resetEncoder();
        while(drive.getEncoder() < 10380) {
            int head = gyroSensor.getHeading();
            telemetry.addData("head", head);
            /*if(head > 7 && head < 180) {
                drive.drive(0.9, 1);
            } else if(head > 180 && head < 353) {
                drive.drive(1, 0.9);
            } else {
            */    drive.drive(0.92, 1);
            //}
        }

        //waitFor(5000);

        drive.stop();

        waitFor(750);

        gyroSensor.resetZAxisIntegrator();

        drive.drive(-.40, .40);

        waitFor(new Condition() {
            int targetMin = 360 - 32;

            @Override
            public boolean get() {
                int head = gyroSensor.getHeading();
                return head < targetMin && head > 180;
            }
        });

        drive.stop();

        waitFor(500);

        sweeper.sweepStop();

        drive.drive(.40, .40);

        waitFor(1250);

        drive.stop();

        climberShelterArm.climberArmOut();

        waitFor(2000);

        climberShelterArm.setClimberArm(0.45);

        waitFor(1000);

        climberShelterArm.climberArmOut();

        waitFor(1000);

        climberShelterArm.setClimberArm(0.45);

        waitFor(1000);

        climberShelterArm.climberArmOut();

        waitFor(1000);

        climberShelterArm.setClimberArm(0.45);

        waitFor(1000);

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
