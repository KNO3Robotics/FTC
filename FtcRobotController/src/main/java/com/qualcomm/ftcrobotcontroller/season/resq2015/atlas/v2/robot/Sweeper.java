package com.qualcomm.ftcrobotcontroller.season.resq2015.atlas.v2.robot;

import com.qualcomm.ftcrobotcontroller.robot.Robot;
import com.qualcomm.ftcrobotcontroller.robot.SubSystem;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by jaxon on 10/30/15.
 */
public class Sweeper extends SubSystem {
    private DcMotor sweeper;

    public Sweeper(Robot robot) {
        super(robot);
    }

    @Override
    public void init() {
        sweeper = robot.hardwareMap.dcMotor.get("sweeper");

        sweeper.setDirection(DcMotor.Direction.REVERSE);

        sweepStop();
    }

    @Override
    public void handle() {
        double spinSpeed = robot.gamepad2.right_trigger - robot.gamepad2.left_trigger;

        robot.telemetry.addData("Sweeper - Speed", spinSpeed);

        sweep(spinSpeed);

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
