package org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronOpMode.AutonomousProgram;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.RobotSubSystems.CubeSystem;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.RobotSubSystems.DriveSystem;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.General.Robot;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.RobotSubSystems.JewelSystem;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.RobotSubSystems.LiftSystem;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.RobotSubSystems.SensorSystem;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.RobotSubSystems.VuforiaSystem;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.Ultron;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronUtil.SimpleColor;

/**
 * Created by Julian on 11/15/2017.
 */

public abstract class UltronAuto extends AutonomousProgram{
    public DriveSystem driveSystem;
    public LiftSystem liftSystem;
    public CubeSystem cubeSystem;
    public JewelSystem jewelSystem;
    public SensorSystem sensorSystem;
    public VuforiaSystem vuforiaSystem;

    protected double currentYaw;

    protected RelicRecoveryVuMark vuMark;

    private SimpleColor alliance;

    public UltronAuto(SimpleColor alliance) {this.alliance = alliance;}

    @Override
    protected Robot buildRobot() {
        Ultron ultron = new Ultron(this, alliance, false);
        driveSystem = (DriveSystem)ultron.getSubSystem("drive");
        liftSystem = (LiftSystem)ultron.getSubSystem("lift");
        cubeSystem = (CubeSystem)ultron.getSubSystem("cube");
        jewelSystem = (JewelSystem)ultron.getSubSystem("jewel");
        sensorSystem = (SensorSystem)ultron.getSubSystem("sensor");
        vuforiaSystem = (VuforiaSystem)ultron.getSubSystem("vuforia");
        return ultron;
    }

    @Override
    public void postInit() {
        vuforiaSystem.activateVuforia();
    }


    /**
     * turns to the left specified amount
     * @param target
     * @param turnSpeed
     * @return
     */
    public double turn(double target, double turnSpeed) {
        sensorSystem.updateGyro();
        currentYaw = sensorSystem.getYaw();
        return turnAbsolute((target + currentYaw), turnSpeed);
    }

    //This function turns a number of degrees compared to where the robot was when the program started. Positive numbers trn left.
    public double turnAbsolute(double target, double inTurnSpeed) {
        double tolerance = Math.PI/180;
        double turnSpeedMultiplier = 1;
        double turnSpeed = inTurnSpeed*turnSpeedMultiplier;
        currentYaw = sensorSystem.getYaw();  //Set variables to gyro readings
        while (Math.abs(currentYaw - target) > tolerance && opModeIsActive()) {//Continue while the robot direction is further than three degrees from the target

            turnSpeedMultiplier = Math.abs(currentYaw - target)/Math.abs(target);

            if (inTurnSpeed*turnSpeedMultiplier < Ultron.MIN_TURN_SPEED) {
                turnSpeed = Ultron.MIN_TURN_SPEED;
            }

            if (currentYaw > target) {  //if gyro is positive, we will turn right
                driveSystem.driveAngle(0, Math.PI/2, turnSpeed);
            }

            if (currentYaw < target) {  //if gyro is positive, we will turn left
                driveSystem.driveAngle(0, Math.PI/2, -turnSpeed);
            }
            sensorSystem.updateGyro();
            currentYaw = sensorSystem.getYaw();  //Set variables to gyro readings
            telemetry.update();
        }
        telemetry.update();
        driveSystem.stopMotors();
        return currentYaw;
    }

    public void driveStraightForward(int distance, double power) {

        driveSystem.resetEncoders();
        double leftSpeed; //Power to feed the motors
        double rightSpeed;

        double target = sensorSystem.getYaw();  //Starting direction
        double startPosition = driveSystem.getRightEncoderValue();  //Starting position

        while (driveSystem.getRightEncoderValue() < distance + startPosition && opModeIsActive()) {  //While we have not passed out intended distance
            sensorSystem.updateGyro();
            currentYaw = sensorSystem.getYaw();  //Current direction

            leftSpeed = power + Math.toDegrees(currentYaw - target) / 100;  //Calculate speed for each side
            rightSpeed = power - Math.toDegrees(currentYaw - target) / 100;  //See Gyro Straight video for detailed explanation

            leftSpeed = Range.clip(leftSpeed, -1, 1);
            rightSpeed = Range.clip(rightSpeed, -1, 1);

            driveSystem.setTurnPower(rightSpeed,leftSpeed);

            telemetry.addData("Distance to go", distance + startPosition - sensorSystem.getYaw());
            telemetry.update();
        }

        driveSystem.stopMotors();
    }

    /**
     * Takes negative values
     * @param distance
     * @param power
     */
    public void driveStraightBackwards (int distance, double power) {

        driveSystem.resetEncoders();
        double leftSpeed; //Power to feed the motors
        double rightSpeed;

        double target = sensorSystem.getYaw();  //Starting direction
        double startPosition = driveSystem.getRightEncoderValue();  //Starting position

        while (driveSystem.getRightEncoderValue() > distance + startPosition && opModeIsActive()) {  //While we have not passed out intended distance
            sensorSystem.updateGyro();
            currentYaw = sensorSystem.getYaw();  //Current direction

            leftSpeed = power - Math.toDegrees(currentYaw - target) / 100;  //Calculate speed for each side
            rightSpeed = power + Math.toDegrees(currentYaw - target) / 100;  //See Gyro Straight video for detailed explanation

            leftSpeed = Range.clip(leftSpeed, -1, 1);
            rightSpeed = Range.clip(rightSpeed, -1, 1);

            driveSystem.setTurnPower(rightSpeed,leftSpeed);

            telemetry.addData("Distance to go", distance + startPosition - sensorSystem.getYaw());
            telemetry.update();
        }

        driveSystem.stopMotors();
    }

    public void driveForwardsToGivenPosition(double inPower, int distance) {
        driveSystem.resetEncoders();
        int currentRightPos = driveSystem.getRightEncoderValue();
        int currentLeftPos = driveSystem.getLeftEncoderValue();
        while ((currentRightPos<distance || currentLeftPos<distance) && opModeIsActive()) {
            if (currentLeftPos<distance) {
                driveSystem.getFrontLeft().setPower(inPower);
                driveSystem.getRearLeft().setPower(inPower);
            } else {
                driveSystem.getFrontLeft().setPower(0);
                driveSystem.getRearLeft().setPower(0);
            }
            if (currentRightPos<distance) {
                driveSystem.getFrontRight().setPower(inPower);
                driveSystem.getRearRight().setPower(inPower);
            } else {
                driveSystem.getFrontRight().setPower(0);
                driveSystem.getRearRight().setPower(0);
            }
            currentRightPos = driveSystem.getRightEncoderValue();
            currentLeftPos = driveSystem.getLeftEncoderValue();
            telemetry.addData("Right Pos", currentRightPos);
            telemetry.addData("Left Pos", currentLeftPos);
            telemetry.update();
        }
        driveSystem.stopMotors();
    }

    /**
     * takes negative values
     * @param inPower
     * @param distance
     */
    public void driveBackwardsToGivenPosition(double inPower, int distance) {
        driveSystem.resetEncoders();
        int currentRightPos = driveSystem.getRightEncoderValue();
        int currentLeftPos = driveSystem.getLeftEncoderValue();
        while ((currentRightPos>distance || currentLeftPos>distance) && opModeIsActive()) {
            if (currentLeftPos>distance) {
                driveSystem.getFrontLeft().setPower(inPower);
                driveSystem.getRearLeft().setPower(inPower);
            } else {
                driveSystem.getFrontLeft().setPower(0);
                driveSystem.getRearLeft().setPower(0);
            }
            if (currentRightPos>distance) {
                driveSystem.getFrontRight().setPower(inPower);
                driveSystem.getRearRight().setPower(inPower);
            } else {
                driveSystem.getFrontRight().setPower(0);
                driveSystem.getRearRight().setPower(0);
            }
            currentRightPos = driveSystem.getRightEncoderValue();
            currentLeftPos = driveSystem.getLeftEncoderValue();
            telemetry.addData("Right Pos", currentRightPos);
            telemetry.addData("Left Pos", currentLeftPos);
            telemetry.update();
        }
        driveSystem.stopMotors();
    }

    public void driveTime(double speed, double time) {
        long initialTime = System.currentTimeMillis();
        while (System.currentTimeMillis() < initialTime + time*1000 && opModeIsActive()) {
            driveSystem.driveForward(speed);
        }
    }

    public void autoGoToLiftPos(LiftSystem.LiftState inLiftState) {
        int targetPos = 0;
        int THRESHOLD = 100;

        switch (inLiftState) {
            case ZERO_CUBE_HEIGHT:
                targetPos = Ultron.ZERO_CUBE_HEIGHT;
                break;
            case HALF_CUBE_HEIGHT:
                targetPos = Ultron.HALF_CUBE_HEIGHT;
                break;
            case ONE_CUBE_HEIGHT:
                targetPos = Ultron.ONE_CUBE_HEIGHT;
                break;
            case TWO_CUBE_HEIGHT:
                targetPos = Ultron.TWO_CUBE_HEIGHT;
                break;
            case THREE_CUBE_HEIGHT:
                targetPos = Ultron.THREE_CUBE_HEIGHT;
                break;
        }

        liftSystem.getRightLiftMotor().setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while (opModeIsActive() && liftSystem.getRightLiftMotor().isBusy()) {
            liftSystem.goToTargetLiftPos(targetPos);
        }
        while (opModeIsActive() && Math.abs(liftSystem.getLiftPosition() - targetPos) > THRESHOLD) {
            liftSystem.goToTargetLiftPos(targetPos);
        }

        liftSystem.stop();

    }
}
