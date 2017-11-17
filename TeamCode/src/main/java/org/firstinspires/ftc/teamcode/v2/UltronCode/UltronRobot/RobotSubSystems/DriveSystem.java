package org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.RobotSubSystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.General.Robot;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.General.SubSystem;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.Ultron;

/**
 * Created by Julian on 11/14/2017.
 */

public class DriveSystem extends SubSystem{

    private DcMotor frontRight, frontLeft, rearRight, rearLeft;
    //Add sensorSystem here
    private boolean slow = false;

    public DriveSystem(Robot robot) {
        super(robot);
    }

    @Override
    public void init() {
        frontLeft = hardwareMap().dcMotor.get(Ultron.DRIVE_FL_KEY);
        rearLeft = hardwareMap().dcMotor.get(Ultron.DRIVE_RL_KEY);
        frontRight = hardwareMap().dcMotor.get(Ultron.DRIVE_FR_KEY);
        rearRight = hardwareMap().dcMotor.get(Ultron.DRIVE_RR_KEY);

        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        rearLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        rearRight.setDirection(DcMotor.Direction.REVERSE);

        modeReset();
        modeVoltage();
        floatMode();
    }

    @Override
    public void handle() {
        if (gamepad1().right_bumper) {
            slow = true;
        } else {
            slow = false;
        }
        mecanumNoTrig();
    }

    @Override
    public void stop() {
        stopMotors();
    }

    public void floatMode() {
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rearLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rearRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    public void brakeMode() {
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void stopMotors() {
        brakeMode();
        frontLeft.setPower(0);
        rearLeft.setPower(0);
        frontRight.setPower(0);
        rearRight.setPower(0);
    }

    public void modeVoltage() {
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rearLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rearRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void modeSpeed() {
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rearLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rearRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void modeReset() {
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rearLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rearRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void mecanumTrig() {
        double r = Math.hypot(gamepad1().left_stick_x, gamepad1().left_stick_y);
        double robotAngle = Math.atan2(gamepad1().left_stick_y, gamepad1().left_stick_x) - Math.PI / 4;
        double rightX = -gamepad1().right_stick_x;

        double frontLeftPower = r * Math.cos(robotAngle) + rightX;
        double frontRightPower = r * Math.sin(robotAngle) - rightX;
        double rearLeftPower = r * Math.sin(robotAngle) + rightX;
        double rearRightPower = r * Math.cos(robotAngle) - rightX;

        frontLeftPower = Range.clip(frontLeftPower, -1.0, 1.0) ;
        frontRightPower = Range.clip(frontRightPower, -1.0, 1.0) ;
        rearLeftPower = Range.clip(rearLeftPower, -1.0, 1.0) ;
        rearRightPower = Range.clip(rearRightPower, -1.0, 1.0) ;

        if (slow) {
            frontLeft.setPower(frontLeftPower / 2.0);
            frontRight.setPower(frontRightPower / 2.0);
            rearLeft.setPower(rearLeftPower / 2.0);
            rearRight.setPower(rearRightPower / 2.0);
        } else {
            frontLeft.setPower(frontLeftPower);
            frontRight.setPower(frontRightPower);
            rearLeft.setPower(rearLeftPower);
            rearRight.setPower(rearRightPower);
        }

    }

    public void mecanumNoTrig() {
        double Ch1 = -gamepad1().right_stick_x;
        double Ch3 = gamepad1().left_stick_y;
        double Ch4 = gamepad1().left_stick_x;

        double frontLeftPower = Ch3 + Ch1 + Ch4;
        double rearLeftPower = Ch3 + Ch1 - Ch4;
        double frontRightPower = Ch3 - Ch1 - Ch4;
        double rearRightPower = Ch3 - Ch1 + Ch4;

        frontLeft.setPower(frontLeftPower);
        rearLeft.setPower(rearLeftPower);
        frontRight.setPower(frontRightPower);
        rearRight.setPower(rearRightPower);
    }
}
