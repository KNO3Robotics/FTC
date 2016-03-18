package com.qualcomm.ftcrobotcontroller.season.resq2015.atlas.v4.robot;

import com.qualcomm.ftcrobotcontroller.robot.Robot;
import com.qualcomm.ftcrobotcontroller.robot.SubSystem;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by jaxon on 10/30/15.
 */
public class Sweeper extends SubSystem {
    private DcMotor sweeper;

    private boolean wasFast = false;
    private long lastSampleTime = -1;
    private int lastSample = 0;
    private long reverseTime = -1;

    public Sweeper(Robot robot) {
        super(robot);
    }

    @Override
    public void init() {
        sweeper = robot.hardwareMap.dcMotor.get("sweeper");

        lastSample = sweeper.getCurrentPosition();

        sweepStop();
    }

    @Override
    public void handle() {
        double spinSpeed = robot.gamepad2.right_trigger - robot.gamepad2.left_trigger;

        robot.telemetry.addData("Sweeper - Speed", spinSpeed);

        /*if(spinSpeed > 0.5) {
            if(wasFast) {
                if(System.currentTimeMillis() - lastSampleTime > 250 && !(sweeper.getCurrentPosition() - lastSample > 20)) {
                    sweep(-1);
                    wasFast = false;
                    reverseTime = System.currentTimeMillis();
                } else {
                    sweep(spinSpeed);
                }
            } else {
                sweep(spinSpeed);
            }
        } else {
            lastSampleTime = -1;
            if(reverseTime != -1 && System.currentTimeMillis() - reverseTime > 500) {
                reverseTime = -1;
                sweep(spinSpeed);
            } else {
                sweep(-1);
            }
        }*/

        if(Math.abs(spinSpeed) >= 0.5) {
            sweep(spinSpeed > 0 ? 1 : -1);
        } else if(Math.abs(robot.gamepad1.right_stick_y) > 0.5) {
            sweep(robot.gamepad1.right_stick_y > 0 ? 0.7 : -0.7);
        } else {
            sweep(0);
        }

        robot.telemetry.addData("Sweeper - Set - sweeper", sweeper.getPower());
    }

    @Override
    public void stop() {

    }

    private void sweep(double speed) {
        try {
            sweeper.setPower(speed);
        } catch(Exception ex) {}
    }

    public void sweepIn() {
        sweep(1);
    }

    public void sweepOut() {
        sweep(-1);
    }

    public void sweepStop() {
        sweep(0);
    }
}
