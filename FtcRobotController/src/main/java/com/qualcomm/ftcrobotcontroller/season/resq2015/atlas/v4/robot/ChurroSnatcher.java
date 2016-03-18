package com.qualcomm.ftcrobotcontroller.season.resq2015.atlas.v4.robot;

import com.qualcomm.ftcrobotcontroller.robot.Robot;
import com.qualcomm.ftcrobotcontroller.robot.SubSystem;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by jaxon on 12/14/15.
 */
public class ChurroSnatcher extends SubSystem {
    private Servo churroSnatcher1;

    public ChurroSnatcher(Robot robot) {
        super(robot);
    }

    @Override
    public void init() {
        churroSnatcher1 = robot.hardwareMap.servo.get("churro_snatcher");

        unlock();
    }

    @Override
    public void handle() {
        boolean lock = robot.gamepad1.right_bumper;

        if(lock) {
            lock();
        } else {
            unlock();
        }

        robot.telemetry.addData("Churro Snatcher Lock", lock);
        robot.telemetry.addData("Churro Snatcher Pos", churroSnatcher1.getPosition());
    }

    @Override
    public void stop() {
        churroSnatcher1.getController().pwmDisable();
    }

    public void lock() {
        churroSnatcher1.setPosition(1);
    }

    public void unlock() {
        churroSnatcher1.setPosition(0.45);
    }
}