package com.qualcomm.ftcrobotcontroller.season.resq2015.atlas.v2.robot;

import com.qualcomm.ftcrobotcontroller.robot.Robot;
import com.qualcomm.ftcrobotcontroller.robot.SubSystem;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by jaxon on 10/30/15.
 */
public class Box extends SubSystem {
    private Servo boxMove, boxTilt;
    
    public Box(Robot robot) {
        super(robot);
    }

    @Override
    public void init() {
        boxMove = robot.hardwareMap.servo.get("box_move");
        boxTilt = robot.hardwareMap.servo.get("box_tilt");

        boxMove(0.5);
        boxTilt(BoxTiltPosition.CENTER);
    }

    @Override
    public void handle() {
        double move = Math.abs(robot.gamepad2.right_stick_x / 2 + 0.5);
        boolean tiltRight = robot.gamepad2.right_bumper;
        boolean tiltLeft = robot.gamepad2.left_bumper;

        robot.telemetry.addData("Box - Move Speed", move);
        robot.telemetry.addData("Box - Tilt Right", tiltRight);
        robot.telemetry.addData("Box - Tilt Left", tiltLeft);

        boxMove(move);

        if(tiltRight && tiltLeft) {
            boxTilt(BoxTiltPosition.CENTER);
        } else if(tiltLeft) {
            boxTilt(BoxTiltPosition.LEFT);
        } else if(tiltRight) {
            boxTilt(BoxTiltPosition.RIGHT);
        } else {
            boxTilt(BoxTiltPosition.CENTER);
        }

        robot.telemetry.addData("Box - Set - boxMove", boxMove.getPosition());
        robot.telemetry.addData("Box - Set - boxTilt", boxTilt.getPosition());
    }

    @Override
    public void stop() {
        boxMove.getController().pwmDisable();
        boxTilt.getController().pwmDisable();
    }

    private void boxMove(double speed) {
        boxMove.setPosition(speed);
    }

    private void boxTilt(BoxTiltPosition position) {
        boxTilt.setPosition(position.pos);
    }

    private enum BoxTiltPosition {
        RIGHT(.7),
        CENTER(.4),
        LEFT(.1);

        public double pos;

        BoxTiltPosition(double pos) {
            this.pos = pos;
        }
    }
}