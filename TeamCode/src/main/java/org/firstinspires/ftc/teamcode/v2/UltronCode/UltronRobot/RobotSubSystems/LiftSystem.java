package org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.RobotSubSystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.General.Robot;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.General.SubSystem;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.Ultron;

/**
 * Created by Julian on 11/15/2017.
 */

public class LiftSystem extends SubSystem {
    private DcMotor rightLiftMotor;
    private DcMotor leftLiftMotor;

    private Servo rightTopServo;
    private Servo leftTopServo;
    private Servo rightLowerServo;
    private Servo leftLowerServo;

    public int currentSetting = 0;
    private boolean dPadWasUp = false;
    private boolean dPadWasDown = false;

    public LiftSystem(Robot robot) {
        super(robot);
    }

    @Override
    public void init() {
        rightLiftMotor = hardwareMap().dcMotor.get(Ultron.LIFT_R_WINCH_KEY);
        leftLiftMotor = hardwareMap().dcMotor.get(Ultron.LIFT_L_WINCH_KEY);
        rightTopServo = hardwareMap().servo.get(Ultron.RIGHT_TOP_SERVO_KEY);
        leftTopServo = hardwareMap().servo.get(Ultron.LEFT_TOP_SERVO_KEY);
        rightLowerServo = hardwareMap().servo.get(Ultron.RIGHT_LOWER_SERVO_KEY);
        leftLowerServo = hardwareMap().servo.get(Ultron.LEFT_LOWER_SERVO_KEY);

        rightLiftMotor.setDirection(DcMotor.Direction.FORWARD);
        leftLiftMotor.setDirection(DcMotor.Direction.REVERSE);

        liftBrakeMode();
        setLiftVoltageMode();

        //WE HAVE A DEAD MOTOR FOR OUR LIFT, DO NOT USE
    }

    @Override
    public void handle() {
        handleChangeInDPad();

        if(gamepad1().b){
            openTop();
        }

        if (gamepad1().x){
            closeTop();
        }

        if (gamepad1().dpad_left) {
            openLower();
        }
        if (gamepad1().dpad_right) {
            closeLower();
        }
        telemetry().addData("Lift Position: ", getLiftPosition());
    }

    @Override
    public void stop() {
        liftFloatMode();
        setLifPower(0);
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

    public void goToTargetLiftPos(int position) {
        int currentPosition = rightLiftMotor.getCurrentPosition();
        int difference = currentPosition - position;
        if (difference > 20) {
            setLifPower(-0.5);
        }else if (difference < -20) {
            setLifPower(0.75);
        }
    }

    public void handleChangeInDPad() {
        if (gamepad1().dpad_up && !dPadWasUp) {
            if (currentSetting < 4) {
                currentSetting++;
            }
        }
        if (gamepad1().dpad_down && !dPadWasDown) {
            if (currentSetting > 0) {
                currentSetting--;
            }
        }
        if (currentSetting == 0) {
            goToTargetLiftPos(Ultron.ZERO_CUBE_HEIGHT);
        }else if (currentSetting == 1) {
            goToTargetLiftPos(Ultron.HALF_CUBE_HEIGHT);
        }else if (currentSetting == 2) {
            goToTargetLiftPos(Ultron.ONE_CUBE_HEIGHT);
        }else if (currentSetting == 3) {
            goToTargetLiftPos(Ultron.TWO_CUBE_HEIGHT);
        }else if (currentSetting == 4) {
            goToTargetLiftPos(Ultron.THREE_CUBE_HEIGHT);
        }
        dPadWasUp = gamepad1().dpad_up;
        dPadWasDown = gamepad1().dpad_down;
    }

    public int getLiftPosition() {
        return rightLiftMotor.getCurrentPosition();
    }
}
