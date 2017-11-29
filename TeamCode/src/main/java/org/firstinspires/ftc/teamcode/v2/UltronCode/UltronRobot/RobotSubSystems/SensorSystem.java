package org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.RobotSubSystems;

import android.graphics.Color;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.General.Robot;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.General.SubSystem;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.Ultron;

/**
 * Created by Julian on 11/25/2017.
 */

public class SensorSystem extends SubSystem {

    private BNO055IMU imu;
    private Orientation angles;
    private double heading;
    private double roll;
    private double pitch;

    // Color sensor defs
    private ColorSensor rightColorSensor;
    private ColorSensor leftColorSensor;

    public SensorSystem(Robot robot) {
        super(robot);
    }

    @Override
    public void init() {
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json";
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        imu = hardwareMap().get(BNO055IMU.class, Ultron.IMU_KEY);
        imu.initialize(parameters);

        imu.startAccelerationIntegration(new Position(), new Velocity(), 1000);

        rightColorSensor = hardwareMap().colorSensor.get(Ultron.RIGHT_COLOR_SENSOR_KEY);
        leftColorSensor = hardwareMap().colorSensor.get(Ultron.LEFT_COLOR_SENSOR_KEY);

    }

    @Override
    public void handle() {
        updateGyro();
        displayValues();
    }

    @Override
    public void stop() {

    }

    void updateGyro() {
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS);
        heading = angles.firstAngle;
        roll = angles.secondAngle;
        pitch = angles.thirdAngle;
    }

    public int[] getRightColorSensorData() {
        return new int[]{rightColorSensor.red(),rightColorSensor.blue()};
    }

    public int[] getLeftColorSensorData() {
        return new int[]{leftColorSensor.red(),leftColorSensor.blue()};
    }

    public double getYaw() {
        return heading;
    }

    public double getRoll() {
        return roll;
    }

    public double getPitch() {
        return pitch;
    }

    public void displayValues() {
        telemetry().addData("Heading", heading);
        telemetry().addData("Roll", roll);
        telemetry().addData("Pitch", pitch);
    }
}
