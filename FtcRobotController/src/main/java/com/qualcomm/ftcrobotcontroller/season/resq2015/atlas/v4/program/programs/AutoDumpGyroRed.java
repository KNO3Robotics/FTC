package com.qualcomm.ftcrobotcontroller.season.resq2015.atlas.v4.program.programs;

import com.qualcomm.ftcrobotcontroller.opMode.annotation.Program;
import com.qualcomm.ftcrobotcontroller.robot.Robot;
import com.qualcomm.ftcrobotcontroller.season.resq2015.atlas.v4.program.AtlasAutonomousProgram;
import com.qualcomm.ftcrobotcontroller.util.Condition;
import com.qualcomm.robotcore.hardware.GyroSensor;

/**
 * @author Jaxon A Brown
 */
@Program(name = "Auto DumpGyro RED", enabled = true)
public class AutoDumpGyroRed extends AtlasAutonomousProgram {
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

        drive.drive(.40, -.40);

        waitFor(new Condition() {
            int targetMin = 32;

            @Override
            public boolean get() {
                int head = gyroSensor.getHeading();
                telemetry.addData("head", head);
                return head > targetMin && head < 180;
            }
        });

        drive.stop();

        waitFor(500);

        gyroSensor.resetZAxisIntegrator();

        drive.resetEncoder();

        //drive(5000);

        long startTime = System.currentTimeMillis();
        while(drive.getEncoder() < 10500) {
            int head = gyroSensor.getHeading();
            telemetry.addData("head", head);
            /*if(head > 50 && head < 180) {
                drive.drive(0.95, 1);
            } else if(head > 180 && head < 300) {
                drive.drive(1, 0.95);
            } else {
             */   drive.drive(0.92, 1);
           // }
        }

        //waitFor(5000);

        drive.stop();

        waitFor(750);

        gyroSensor.resetZAxisIntegrator();

        drive.drive(.40, -.40);

        waitFor(new Condition() {
            int targetMin = 29;

            @Override
            public boolean get() {
                int head = gyroSensor.getHeading();
                return head > targetMin && head < 180;
            }
        });

        drive.stop();

        waitFor(500);

        sweeper.sweepStop();

        drive.drive(.40, .40);

        waitFor(1300);

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

    protected void drive(long durationMillis) {
        long startTime = System.currentTimeMillis();
        int startAngle = gyroSensor.getHeading();
        while(this.opModeIsActive() && startTime + durationMillis < System.currentTimeMillis()) {
            int change = gyroSensor.getHeading() - startAngle;
            if(change > 0) {
                drive.drive(0.95, 1);
            } else if(change < 0) {
                drive.drive(1, 0.95);
            } else {
                drive.drive(1, 1);
            }
        }
    }

    protected int getChange(int start, int curr) {
        int change = curr - start;
        if(change > 180) {
            change -= 360;
        } else if(change < -180) {
            change += 360;
        }
        return change;
    }
}
