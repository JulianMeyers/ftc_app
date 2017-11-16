package org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.RobotSubSystems;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.General.Robot;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.General.SubSystem;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.Ultron;

/**
 * Created by Julian on 11/16/2017.
 */

public class CubeSystem extends SubSystem {

    private Servo rightTopServo;
    private Servo leftTopServo;
    private Servo rightLowerServo;
    private Servo leftLowerServo;

    public CubeSystem(Robot robot) {
        super(robot);
    }

    @Override
    public void init() {
        rightTopServo = hardwareMap().servo.get(Ultron.RIGHT_TOP_SERVO_KEY);
        leftTopServo = hardwareMap().servo.get(Ultron.LEFT_TOP_SERVO_KEY);
        rightLowerServo = hardwareMap().servo.get(Ultron.RIGHT_LOWER_SERVO_KEY);
        leftLowerServo = hardwareMap().servo.get(Ultron.LEFT_LOWER_SERVO_KEY);
        openTop();
        openLower();
    }

    @Override
    public void handle() {
        if (Ultron.TWO_DRIVERS) {
            if (gamepad2().b) {
                openTop();
            }

            if (gamepad2().x) {
                closeTop();
            }

            if (gamepad2().dpad_left) {
                openLower();
            }
            if (gamepad2().dpad_right) {
                closeLower();
            }
        }else {
            if (gamepad1().b) {
                openTop();
            }

            if (gamepad1().x) {
                closeTop();
            }

            if (gamepad1().dpad_left) {
                openLower();
            }
            if (gamepad1().dpad_right) {
                closeLower();
            }
        }
    }

    @Override
    public void stop() {
        openTop();
        openLower();
    }

    public void openTop() {
        rightTopServo.setPosition(Ultron.RIGHT_TOP_SERVO_OPEN);
        leftTopServo.setPosition(Ultron.LEFT_TOP_SERVO_OPEN);
    }

    public void closeTop() {
        rightTopServo.setPosition(Ultron.RIGHT_TOP_SERVO_CLOSED);
        leftTopServo.setPosition(Ultron.LEFT_TOP_SERVO_CLOSED);
    }

    public void openLower() {
        rightLowerServo.setPosition(Ultron.RIGHT_LOWER_SERVO_OPEN);
        leftLowerServo.setPosition(Ultron.LEFT_LOWER_SERVO_OPEN);
    }

    public void closeLower() {
        rightLowerServo.setPosition(Ultron.RIGHT_LOWER_SERVO_CLOSED);
        leftLowerServo.setPosition(Ultron.LEFT_LOWER_SERVO_CLOSED);
    }
}
