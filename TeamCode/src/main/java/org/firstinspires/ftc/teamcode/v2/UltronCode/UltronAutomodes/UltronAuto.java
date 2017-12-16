package org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes;

import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
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
import org.opencv.core.Mat;

import java.lang.annotation.Target;

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
        double startPosition = driveSystem.getEncoderValues();  //Starting position

        while (driveSystem.getEncoderValues() < distance + startPosition && opModeIsActive()) {  //While we have not passed out intended distance
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

    public void driveForwardDistance(double speed, double distance) {
        speed = Math.abs(speed);
        distance = Math.abs(distance);
        int initialPos = driveSystem.getEncoderValues();
        int currentDistance = driveSystem.getEncoderValues();
        while (driveSystem.getEncoderValues() < initialPos + distance && opModeIsActive()) {
            telemetry.addData("Current distance", currentDistance);
            telemetry.addData("Target distance", distance);
            telemetry.addData("Difference", initialPos - distance);
            telemetry.update();
            driveSystem.driveForward(speed);
            currentDistance = driveSystem.getEncoderValues();
        }
    }

    public void driveBackwardDistance(double speed, double distance) {
        speed = Math.abs(speed);
        distance = Math.abs(distance);
        int initialPos = driveSystem.getEncoderValues();
        int currentDistance = driveSystem.getEncoderValues();
        while (currentDistance > initialPos - distance && opModeIsActive()) {
            telemetry.addData("Current distance", currentDistance);
            telemetry.addData("Target distance", distance);
            telemetry.addData("Difference", initialPos - distance);
            telemetry.update();
            driveSystem.driveForward(-speed);
            currentDistance = driveSystem.getEncoderValues();
        }
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

        while (opModeIsActive() && Math.abs(liftSystem.getLiftPosition() - targetPos) > THRESHOLD) {
            liftSystem.goToTargetLiftPos(targetPos);
        }

        liftSystem.stop();

    }
}
