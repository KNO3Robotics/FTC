package com.qualcomm.ftcrobotcontroller.season.resq2015.atlas.v3.robot;

import com.qualcomm.ftcrobotcontroller.robot.Robot;
import com.qualcomm.ftcrobotcontroller.robot.SubSystem;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by jaxon on 11/11/15.
 */
public class ZiplineLatchers extends SubSystem {
    private Servo leftLatcher, rightLatcher;

    public ZiplineLatchers(Robot robot) {
        super(robot);
    }

    @Override
    public void init() {
        leftLatcher = robot.hardwareMap.servo.get("left_latcher");
        rightLatcher = robot.hardwareMap.servo.get("right_latcher");

        setLeftLatcher(0.85);
        setRightLatcher(0.08);
    }

    @Override
    public void handle() {
        boolean leftLatch = robot.gamepad2.dpad_right;
        boolean rightLatch = robot.gamepad2.dpad_left;

        setLeftLatcher(leftLatch ? 0.5 : 0.85);
        setRightLatcher(rightLatch ? 0.55 : 0.08);
    }

    @Override
    public void stop() {
        leftLatcher.getController().pwmDisable();
        rightLatcher.getController().pwmDisable();
    }
    private void setLeftLatcher(double pos) {
        try {
            leftLatcher.setPosition(pos);
        } catch(Exception ex) {}
    }

    private void setRightLatcher(double pos) {
        try {
            rightLatcher.setPosition(pos);
        } catch(Exception ex) {}
    }

    public void leftLatcherOut() {
        leftLatcher.setPosition(0.5);
    }

    public void leftLatcherIn() {
        leftLatcher.setPosition(0.85);
    }

    public void rightLatcherOut() {
        rightLatcher.setPosition(0.55);
    }

    public void rightLatcherIn() {
        rightLatcher.setPosition(0.08);
    }
}
