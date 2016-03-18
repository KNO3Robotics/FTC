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
@Program(name = "Auto Dump Color RED", enabled = false)
public class AutoDumpColorRed extends AutoDumpGyroRed {
    private ColorSensor colorSensor;
    private Servo buttoner;

    @Override
    public Robot buildRobot() {
        Robot robot = super.buildRobot();

        this.colorSensor = hardwareMap.colorSensor.get("color");

        this.buttoner = hardwareMap.servo.get("buttoner");
        this.buttoner.setPosition(0.2);
        return robot;
    }

    @Override
    public void main() {
        super.main();

        boolean red = colorSensor.red() > colorSensor.blue();

        drive.drive(-.4, -.4);

        waitFor(1000);

        drive.stop();

        buttoner.setPosition(0.8);

        if(red) {
            drive.drive(.4, .4);
            waitFor(1000);
            drive.stop();
        } else {
            gyroSensor.resetZAxisIntegrator();
            drive.drive(-.40, .40);
            waitFor(new Condition() {
                int targetMin = 270;

                @Override
                public boolean get() {
                    int head = gyroSensor.getHeading();
                    return head < targetMin && head > 180;
                }
            });
            drive.drive(.4, .4);
            waitFor(300);
            drive.stop();
            gyroSensor.resetZAxisIntegrator();
            drive.drive(.40, -.40);
            waitFor(new Condition() {
                int targetMin = 90;

                @Override
                public boolean get() {
                    int head = gyroSensor.getHeading();
                    return head > targetMin && head < 180;
                }
            });
            drive.stop();
            drive.drive(.4, .4);
            waitFor(700);
            drive.stop();
        }

        buttoner.setPosition(0.5);

        waitFor(500);

        buttoner.setPosition(0.8);

        waitFor(500);

        buttoner.setPosition(0.5);

        waitFor(500);

        buttoner.setPosition(0.8);

        waitFor(500);

        buttoner.setPosition(0.5);

        waitFor(500);

        buttoner.setPosition(0.8);

        //drive.drive(-1, -1);
        //waitFor(2500);
        //drive.drive(1, -1);
        //waitFor(1000);
        //drive.stop();
        //sweeper.sweepStop();
    }
}
