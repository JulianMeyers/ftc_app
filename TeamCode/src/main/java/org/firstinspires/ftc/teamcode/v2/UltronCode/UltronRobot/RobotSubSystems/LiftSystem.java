package org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.RobotSubSystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.General.Robot;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.General.SubSystem;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.Ultron;

/**
 * Created by Julian on 11/15/2017.
 */

public class LiftSystem extends SubSystem {
    private DcMotor rightLiftMotor;
    private DcMotor leftLiftMotor;

    public int currentLiftState = 0;
    private boolean dPadWasUp = false;
    private boolean dPadWasDown = false;

    public LiftSystem(Robot robot) {
        super(robot);
    }

    @Override
    public void init() {
        rightLiftMotor = hardwareMap().dcMotor.get(Ultron.LIFT_R_WINCH_KEY);
        leftLiftMotor = hardwareMap().dcMotor.get(Ultron.LIFT_L_WINCH_KEY);

        rightLiftMotor.setDirection(DcMotor.Direction.FORWARD);
        leftLiftMotor.setDirection(DcMotor.Direction.REVERSE);

        liftBrakeMode();
        resetEncoders();
        setLiftVoltageMode();
    }

    @Override
    public void handle() {
        if (robot.TWO_DRIVERS) {
            handleChangeInDPad(gamepad2());
            manualControls(gamepad2());
        }else {
            handleChangeInDPad(gamepad1());
            manualControls(gamepad1());
        }

        telemetry().addData("Lift Position: ", getLiftPosition());
    }

    @Override
    public void stop() {
        liftFloatMode();
        setLifPower(0);
    }

    public void liftFloatMode() {
        rightLiftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        leftLiftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    public void liftBrakeMode() {
        rightLiftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftLiftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void setLifPower(double power) {
        rightLiftMotor.setPower(power);
        leftLiftMotor.setPower(power);
    }

    public void setLiftVoltageMode() {
        rightLiftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftLiftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void setLiftEncoderMode() {
        rightLiftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftLiftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void resetEncoders() {
        rightLiftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftLiftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void goToTargetLiftPos(int position) {
        int currentPosition = rightLiftMotor.getCurrentPosition();
        int difference = currentPosition - position;
        if (difference > 20) {
            setLifPower(-0.5);
        }else if (difference < -20) {
            setLifPower(0.75);
        }
    }

    public void handleChangeInDPad(Gamepad gamepad) {
        Gamepad gamepadToUse = gamepad;
        if (gamepadToUse.dpad_up && !dPadWasUp) {
            if (currentLiftState < 4) {
                currentLiftState++;
            }
        }
        if (gamepadToUse.dpad_down && !dPadWasDown) {
            if (currentLiftState > 0) {
                currentLiftState--;
            }
        }
        if (currentLiftState == 0) {
            goToTargetLiftPos(Ultron.ZERO_CUBE_HEIGHT);
        }else if (currentLiftState == 1) {
            goToTargetLiftPos(Ultron.HALF_CUBE_HEIGHT);
        }else if (currentLiftState == 2) {
            goToTargetLiftPos(Ultron.ONE_CUBE_HEIGHT);
        }else if (currentLiftState == 3) {
            goToTargetLiftPos(Ultron.TWO_CUBE_HEIGHT);
        }else if (currentLiftState == 4) {
            goToTargetLiftPos(Ultron.THREE_CUBE_HEIGHT);
        }
        dPadWasUp = gamepadToUse.dpad_up;
        dPadWasDown = gamepadToUse.dpad_down;
    }

    public void manualControls(Gamepad gamepad) {
        if (gamepad.right_trigger>0.01){
            leftLiftMotor.setPower(gamepad.right_trigger);
            rightLiftMotor.setPower(gamepad.right_trigger);
        }else if (gamepad.left_trigger>0.01){
            leftLiftMotor.setPower(-gamepad.left_trigger);
            rightLiftMotor.setPower(-gamepad.left_trigger);
        }else{
            rightLiftMotor.setPower(0);
            leftLiftMotor.setPower(0);
        }
    }

    public int getLiftPosition() {
        return rightLiftMotor.getCurrentPosition();
    }
}
