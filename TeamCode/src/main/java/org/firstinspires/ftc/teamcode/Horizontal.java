package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Config
public class Horizontal {
    ElapsedTime timer;
    String action;
    OpMode opmode;

    Servo rightExtender1;
    Servo rightExtender2;
    Servo leftExtender1;
    Servo leftExtender2;
    Servo leftIntake;
    Servo rightIntake;
    DcMotor intake;

    public static double INTAKE_POWER = 0.9;
    public static double FLAT_RIGHT_INTAKE = 0.9;
    public static double FLAT_LEFT_INTAKE = 0.9;
    public static double LOWER_RIGHT_INTAKE = 0;
    public static double LOWER_LEFT_INTAKE = 0.47;

    public Horizontal(OpMode op) {
        opmode = op;
        timer = new ElapsedTime();
        action = "RETRACT";

        rightExtender1 = opmode.hardwareMap.get(Servo.class, "rh1");
        rightExtender2 = opmode.hardwareMap.get(Servo.class, "rh2");
        leftExtender1 = opmode.hardwareMap.get(Servo.class, "lh1");
        leftExtender2 = opmode.hardwareMap.get(Servo.class, "lh2");
        leftIntake = opmode.hardwareMap.get(Servo.class, "li");
        rightIntake = opmode.hardwareMap.get(Servo.class, "ri");
        intake = opmode.hardwareMap.dcMotor.get("intake");

    }

    public void loop() {
        //scoring actions
        if (action == "RETRACT") {
            retract();
        }

        if (action == "EXTEND") {
            extend();
        } else if (action == "ZERO") {
            zero();
        }
        if (action == "EXTENDOUTAKE") {
            extendOutake();
        }
        if (action == "ZEROOUTAKE") {
            zeroOutake();
        }

    }

    private void setHorizontalLift(double pos) {
        //needs to be reworked later on but its whatever
        rightExtender1.setPosition(1 - (pos * 0.733));
        rightExtender2.setPosition(pos * 0.733);

        leftExtender1.setPosition(pos);
        leftExtender2.setPosition(1 - pos);
        //these might need to be reversed but its whateevr

    }

    private void setIntakePosition(boolean lower) {

        if (lower) {
            leftIntake.setPosition(LOWER_LEFT_INTAKE);
            // rightIntake.setPosition(LOWER_RIGHT_INTAKE);
        } else {
            leftIntake.setPosition(FLAT_LEFT_INTAKE);
            rightIntake.setPosition(FLAT_RIGHT_INTAKE);
        }
    }

    private void retract() {
        setHorizontalLift(0);
        setIntakePosition(false);
        intake.setPower(0);
    }


    private void extend() {
        setHorizontalLift(1);
        setIntakePosition(true);
        intake.setPower(INTAKE_POWER);

    }

    private void zero() {
        setHorizontalLift(0);
        setIntakePosition(true);
        intake.setPower(INTAKE_POWER);
    }

    public void changeAction(String newAction) {
        if (newAction != action) {

            action = newAction;
            timer.reset();

        }

    }

    private void zeroOutake() {
        setHorizontalLift(0);
        setIntakePosition(true);
        intake.setPower(-INTAKE_POWER);
    }

    private void extendOutake() {
        setHorizontalLift(1);
        setIntakePosition(true);
        intake.setPower(-INTAKE_POWER);
    }

    public void updateAction() {
        // Bring all the way back
        if (opmode.gamepad1.a && opmode.gamepad1.dpad_right) {
            changeAction("RETRACT");
            // Extend fully
        } else if (opmode.gamepad1.a && opmode.gamepad1.dpad_left) {
            changeAction("EXTEND");


            // Tilt down to pick up sample
        } else if (opmode.gamepad1.a && opmode.gamepad1.dpad_down) {
            changeAction("ZERO");
        } else if (opmode.gamepad1.a && opmode.gamepad1.dpad_up) {
            if (action.equals("EXTEND")) {
               changeAction("EXTENDOUTAKE");

            } else if (action.equals("ZERO")) {
                changeAction("ZEROOUTAKE");

            }

        }



    }
}