package com.qualcomm.ftcrobotcontroller.season.resq2015.atlas.v3.robot;

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
        climberArmLoaded();
    }

    boolean hasFlicked = false;

    @Override
    public void handle() {
        boolean flickOut = robot.gamepad1.a;

        if(!hasFlicked && flickOut) {
            climberArmOut();
            hasFlicked = true;
        } else if(hasFlicked) {
            climberArmStaticPos();
        }

        robot.telemetry.addData("Climber arm hasFlicked", hasFlicked);
        robot.telemetry.addData("Climber arm flick out", flickOut);
    }

    @Override
    public void stop() {
        climberArm.getController().pwmDisable();
    }

    public void setClimberArm(double pos) {
        try {
            climberArm.setPosition(pos);
        } catch(Exception ex) {}
    }

    public void climberArmOut() {
        setClimberArm(0.1);
        hasFlicked = true;
    }

    public void climberArmStaticPos() {
        setClimberArm(0.6);
    }

    public void climberArmLoaded() {
        setClimberArm(1);
    }
}
