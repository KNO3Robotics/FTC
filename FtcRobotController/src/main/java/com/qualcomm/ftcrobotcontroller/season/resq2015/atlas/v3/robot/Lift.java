package com.qualcomm.ftcrobotcontroller.season.resq2015.atlas.v3.robot;

import com.qualcomm.ftcrobotcontroller.robot.Robot;
import com.qualcomm.ftcrobotcontroller.robot.SubSystem;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by jaxon on 10/30/15.
 */
public class Lift extends SubSystem {
    private DcMotor liftLeft, liftRight;

    public Lift(Robot robot) {
        super(robot);
    }

    @Override
    public void init() {
        liftLeft = robot.hardwareMap.dcMotor.get("lift_left");
        liftRight = robot.hardwareMap.dcMotor.get("lift_right");

        liftRight.setDirection(DcMotor.Direction.REVERSE);

        lift(0);
    }

    @Override
    public void handle() {
        double lift = -robot.gamepad2.left_stick_y;

        robot.telemetry.addData("Lift - Power", lift);

        lift(lift);

        robot.telemetry.addData("Lift - Set - liftLeft", liftLeft.getPower());
        robot.telemetry.addData("Lift - Set - liftRight", liftRight.getPower());
    }

    @Override
    public void stop() {
        stopLift();
        liftLeft.close();
        liftRight.close();
    }

    private void lift(double power) {
        try {
            liftLeft.setPower(power);
            liftRight.setPower(power);
        } catch(Exception ex) {}
    }

    public void extendLift() {
        lift(-1);
    }

    public void retractLift() {
        lift(1);
    }

    public void stopLift() {
        lift(0);
    }
}
