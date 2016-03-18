package com.qualcomm.ftcrobotcontroller.season.resq2015.atlas.v2.robot;

import com.qualcomm.ftcrobotcontroller.robot.Robot;
import com.qualcomm.ftcrobotcontroller.robot.SubSystem;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by jaxon on 10/30/15.
 */
public class Drive extends SubSystem {
    private DcMotor frontLeft, backLeft, frontRight, backRight;

    public Drive(Robot robot) {
        super(robot);
    }

    @Override
    public void init() {
        frontLeft = robot.hardwareMap.dcMotor.get("front_left");
        backLeft = robot.hardwareMap.dcMotor.get("back_left");
        frontRight = robot.hardwareMap.dcMotor.get("front_right");
        backRight = robot.hardwareMap.dcMotor.get("back_right");

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);

        drive(0, 0);
    }

    boolean reverse = false;

    @Override
    public void handle() {
        /*double speed = robot.gamepad1.right_trigger - robot.gamepad1.left_trigger;
        double direction = robot.gamepad1.right_stick_x;

        if(Math.abs(direction) < 0.1) {
            left(speed);
            right(speed);
        } else {
            left(speed * (Math.sin(direction * Math.PI) + Math.PI / 4));
            right(speed * (Math.cos(direction * Math.PI) + Math.PI / 4));
        }*/

        if(robot.gamepad1.x) {
            reverse = false;
        } else if(robot.gamepad1.y) {
            reverse = true;
        }

        if(!reverse) {
            left(robot.gamepad1.right_stick_y);
            right(robot.gamepad1.left_stick_y);
        } else {
            left(-robot.gamepad1.left_stick_y);
            right(-robot.gamepad1.right_stick_y);
        }

        robot.telemetry.addData("Drive - Set - frontLeft", frontLeft.getPower());
        robot.telemetry.addData("Drive - Set - backLeft", backLeft.getPower());
        robot.telemetry.addData("Drive - Set - frontRight", frontRight.getPower());
        robot.telemetry.addData("Drive - Set - backRight", backRight.getPower());
        robot.telemetry.addData("Drive - Enc - Left", frontLeft.getCurrentPosition());
        robot.telemetry.addData("Drive - Enc - Right", frontRight.getCurrentPosition());
    }

    @Override
    public void stop() {
        stopDrive();
        frontLeft.close();
        frontRight.close();
        backLeft.close();
        backRight.close();
    }

    private void left(double power) {
        try {
            frontLeft.setPower(power);
            backLeft.setPower(power);
        } catch(Exception ex) {}
    }

    private void right(double power) {
        try {
            frontRight.setPower(power);
            backRight.setPower(power);
        } catch(Exception ex) {}
    }

    public void drive(double left, double right) {
        left(left);
        right(right);
    }

    public void stopDrive() {
        drive(0, 0);
    }
}
