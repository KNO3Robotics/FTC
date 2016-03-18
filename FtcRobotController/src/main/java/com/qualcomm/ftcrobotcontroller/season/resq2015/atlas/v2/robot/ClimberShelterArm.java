package com.qualcomm.ftcrobotcontroller.season.resq2015.atlas.v2.robot;

import com.qualcomm.ftcrobotcontroller.robot.Robot;
import com.qualcomm.ftcrobotcontroller.robot.SubSystem;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by jaxon on 11/11/15.
 */
public class ClimberShelterArm extends SubSystem {
    private Servo climberArm;

    public ClimberShelterArm(Robot robot) {
        super(robot);
    }

    @Override
    public void init() {
        climberArm = robot.hardwareMap.servo.get("climber_arm");
        climberArm.setPosition(0.75);
    }

    boolean hasFlicked = false;

    @Override
    public void handle() {
        boolean flickOut = robot.gamepad1.a;

        if(!hasFlicked && flickOut) {
            setClimberArm(0.1);
            hasFlicked = true;
        } else if(hasFlicked) {
            setClimberArm(0.35);
        }

        robot.telemetry.addData("Climber arm hasFlicked", hasFlicked);
        robot.telemetry.addData("Climber arm flick out", flickOut);
    }

    @Override
    public void stop() {
        climberArm.getController().pwmDisable();
    }

    private void setClimberArm(double pos) {
        try {
            climberArm.setPosition(pos);
        } catch(Exception ex) {}
    }

    public void climberArmOut() {
        setClimberArm(0.1);
        hasFlicked = true;
    }
}
