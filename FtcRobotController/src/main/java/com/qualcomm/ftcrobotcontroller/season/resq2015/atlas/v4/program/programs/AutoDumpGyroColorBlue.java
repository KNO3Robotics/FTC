package com.qualcomm.ftcrobotcontroller.season.resq2015.atlas.v4.program.programs;

import com.qualcomm.ftcrobotcontroller.opMode.annotation.Program;
import com.qualcomm.ftcrobotcontroller.robot.Robot;
import com.qualcomm.ftcrobotcontroller.season.resq2015.atlas.v4.program.AtlasAutonomousProgram;
import com.qualcomm.ftcrobotcontroller.util.Condition;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * @author Jaxon A Brown
 */
@Program(name = "Auto Dump Color BLUE", enabled = false)
public class AutoDumpGyroColorBlue extends AtlasAutonomousProgram {
    private GyroSensor gyroSensor;
    private ColorSensor colorSensor;
    private Servo leftButtoner;
    private Servo rightButtoner;

    @Override
    public Robot buildRobot() {
        Robot robot = super.buildRobot();
        this.gyroSensor = hardwareMap.gyroSensor.get("gyro");
        gyroSensor.calibrate();
        this.colorSensor = hardwareMap.colorSensor.get("color");
        this.rightButtoner = hardwareMap.servo.get("right_buttoner");
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

        sweeper.sweepOut();

        drive.drive(1, 1);

        waitFor(1000);

        drive.stop();

        waitFor(500);

        gyroSensor.resetZAxisIntegrator();

        telemetry.addData("start h", gyroSensor.getHeading());

        drive.drive(-.40, .40);

        waitFor(new Condition() {
            @Override
            public boolean get() {
                int head = gyroSensor.getHeading();
                telemetry.addData("head", head);
                return head > 180 && head < 320;
            }
        });

        drive.stop();

        waitFor(500);

        gyroSensor.resetZAxisIntegrator();

        //drive(5000);

        long startTime = System.currentTimeMillis();
        while(this.opModeIsActive() && startTime + 4000 > System.currentTimeMillis()) {
            int head = gyroSensor.getHeading();
            if(head > 2 && head < 180) {
                drive.drive(0.95, 1);
            } else if(head > 180 && head < 358) {
                drive.drive(1, 0.95);
            } else {
                drive.drive(1, 1);
            }
        }

        //waitFor(5000);

        drive.stop();

        waitFor(500);

        gyroSensor.resetZAxisIntegrator();

        drive.drive(-.40, .40);

        waitFor(new Condition() {
            @Override
            public boolean get() {
                int head = gyroSensor.getHeading();
                return head > 180 && head < 315;
            }
        });

        drive.stop();

        waitFor(500);

        drive.drive(.40, .40);

        waitFor(1000);

        drive.stop();

        sweeper.sweepStop();

        climberShelterArm.climberArmOut();

        waitFor(500);

        boolean right = false;

        if(colorSensor.blue() > colorSensor.red()) {
            right = true;
        }

        waitFor(2000);

        climberShelterArm.setClimberArm(0.2);

        waitFor(1000);

        climberShelterArm.climberArmStaticPos();

        waitFor(250);

        drive.drive(-.4, -.4);

        waitFor(500);

        if(right) {
            rightButtoner.setPosition(0.2);
        } else {
            leftButtoner.setPosition(0.2);
        }

        drive.drive(.4, .4);

        waitFor(400);

        drive.stop();

        if(right) {
            rightButtoner.setPosition(0.7);
        } else {
            leftButtoner.setPosition(0.7);
        }

        waitFor(150);

        if(right) {
            rightButtoner.setPosition(0.2);
        } else {
            leftButtoner.setPosition(0.2);
        }

        //drive.drive(-1, -1);
        //waitFor(2500);
        //drive.drive(1, -1);
        //waitFor(1000);
        //drive.stop();
        //sweeper.sweepStop();
    }

    private void drive(long durationMillis) {
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

    private int getChange(int start, int curr) {
        int change = curr - start;
        if(change > 180) {
            change -= 360;
        } else if(change < -180) {
            change += 360;
        }
        return change;
    }
}
