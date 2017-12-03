package org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes;

import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
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
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.Ultron;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronUtil.SimpleColor;

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

    protected VuforiaLocalizer vuforia;
    protected int cameraMonitorViewId;
    protected VuforiaLocalizer.Parameters parameters;
    protected VuforiaTrackables relicTrackables;
    protected VuforiaTrackable relicTemplate;

    protected double currentYaw;

    private SimpleColor alliance;

    public enum CryptoboxKey{
        RIGHT,CENTER,LEFT
    }

    public UltronAuto(SimpleColor alliance) {this.alliance = alliance;}

    @Override
    protected Robot buildRobot() {
        Ultron ultron = new Ultron(this, alliance, false);
        driveSystem = (DriveSystem)ultron.getSubSystem("drive");
        liftSystem = (LiftSystem)ultron.getSubSystem("lift");
        cubeSystem = (CubeSystem)ultron.getSubSystem("cube");
        jewelSystem = (JewelSystem)ultron.getSubSystem("jewel");
        sensorSystem = (SensorSystem)ultron.getSubSystem("sensor");
        return ultron;
    }

    @Override
    public void postInit() {
        initializeVuforia();
    }

    private void initializeVuforia() {
        cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = "Ac8vX/3/////AAAAGRLM+gjmWUoAhO8Kns/kDEpxkCW1u1lX1uZW3r/rphUKMxf6jDE4oTHTZ3F/J0GwvD+hMipwaFBziRnmUDZHHXNM//wCa80uaLKJPlK7KkLqmz8dedEKTeMZomxY0T/dAee/7nGRrOtTihtJZvQJNv9RHvgWGF8pR0/lzZobtkU7V3uv+DC/gXY9sJxn/yQdxjxBXuW83wzVcT8tsefn7G8+9T9Til2ZOt/SNV8ilLdfiYFfMUaDdbJnTmaQlhITHP2dtmu71op2u5tsHlABrhs1RDwq3DPC+X+DLJfaPV8kTrgxgo0yxGjDgDmprty6x/BZkv7GD347DQlGBPRuhoSCzrtrR9XtC+FYeuVsbWOu";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT;
        vuforia = ClassFactory.createVuforiaLocalizer(parameters);
        relicTrackables = vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate");
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
        int initialPos = driveSystem.getEncoderValues();
        while (driveSystem.getEncoderValues() < initialPos + distance && opModeIsActive()) {
            driveSystem.driveForward(speed);
        }
    }

    public void driveBackwardDistance(double speed, double distance) {
        int initialPos = driveSystem.getEncoderValues();
        while (driveSystem.getEncoderValues() > initialPos - distance && opModeIsActive()) {
            driveSystem.driveForward(-speed);
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
