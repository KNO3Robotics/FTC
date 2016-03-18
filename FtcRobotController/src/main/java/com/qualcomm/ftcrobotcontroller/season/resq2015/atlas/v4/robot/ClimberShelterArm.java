package com.qualcomm.ftcrobotcontroller.season.resq2015.atlas.v4.robot;

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

    private double armPos = 0;

    @Override
    public void handle() {
        double armPos = this.armPos;
        boolean stat = robot.gamepad1.b;

        if(robot.gamepad1.a) {
            armPos -= 0.01;
        }

        if(armPos > 1) {
            armPos = 1;
        }
        if(armPos < 0) {
            armPos = 0;
        }

        if(stat) {
            climberArmStaticPos();
        } else {
            setClimberArm(armPos);
        }

        robot.telemetry.addData("ARm - pos", this.armPos);
    }

    @Override
    public void stop() {
        climberArm.getController().pwmDisable();
    }

    public void setClimberArm(double pos) {
        try {
            climberArm.setPosition(pos);
            armPos = pos;
        } catch(Exception ex) {}
    }

    public void climberArmOut() {
        setClimberArm(0.1);
    }

    public void climberArmStaticPos() {
        setClimberArm(0.55);
    }

    public void climberArmLoaded() {
        setClimberArm(1);
    }
}
