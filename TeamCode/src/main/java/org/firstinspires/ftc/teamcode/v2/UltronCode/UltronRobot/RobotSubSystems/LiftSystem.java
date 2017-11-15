package org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.RobotSubSystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
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

        //WE HAVE A DEAD MOTOR FOR OUR LIFT, DO NOT USE
    }

    @Override
    public void handle() {

        if (gamepad1().right_trigger>0.01){
            leftLiftMotor.setPower(gamepad1().right_trigger);
            rightLiftMotor.setPower(gamepad1().right_trigger);
        }else if (gamepad1().left_trigger>0.01){
            leftLiftMotor.setPower(-gamepad1().left_trigger);
            rightLiftMotor.setPower(-gamepad1().left_trigger);
        }else{
            rightLiftMotor.setPower(0);
            leftLiftMotor.setPower(0);
        }

        if(gamepad1().b){
            leftTopServo.setPosition(0.5);//These values should be adjusted to be even for the actual bot
            rightTopServo.setPosition(0.5);
        }

        if (gamepad1().x){
            leftTopServo.setPosition(0);
            rightTopServo.setPosition(1);
        }
    }

    @Override
    public void stop() {
        leftLiftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightLiftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightLiftMotor.setPower(0);
        leftLiftMotor.setPower(0);
    }
}
