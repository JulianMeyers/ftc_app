package org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.General.Robot;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.RobotSubSystems.CubeSystem;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.RobotSubSystems.DriveSystem;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.RobotSubSystems.JewelSystem;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.RobotSubSystems.LiftSystem;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.RobotSubSystems.SensorSystem;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronUtil.SimpleColor;

/**
 * Created by Julian on 11/14/2017.
 */

public class Ultron extends Robot{

    //DriveSystem
    public static final String DRIVE_FL_KEY = "LFD";//DC Port 0
    public static final String DRIVE_RL_KEY = "LRD";//DC Port 1
    public static final String DRIVE_FR_KEY = "RFD";//DC Port 2
    public static final String DRIVE_RR_KEY = "RRD";//DC Port 3
    public static final double MIN_TURN_SPEED = 0.2;

    //LiftSystem
    public static final String LIFT_R_WINCH_KEY = "RL";//MR Port 0
    public static final String LIFT_L_WINCH_KEY = "LL";//MR Port 1
    public static final int ZERO_CUBE_HEIGHT = 0;
    public static final int HALF_CUBE_HEIGHT = 0;
    public static final int ONE_CUBE_HEIGHT = 0;
    public static final int TWO_CUBE_HEIGHT = 0;
    public static final int THREE_CUBE_HEIGHT = 0;

    //CubeSystem
    public static final String RIGHT_TOP_SERVO_KEY = "RTS";//Servo Port 0
    public static final String LEFT_TOP_SERVO_KEY = "LTS";//Servo Port 1
//    public static final String RIGHT_LOWER_SERVO_KEY = "RLS";
//    public static final String LEFT_LOWER_SERVO_KEY = "LLS";
    public static final double RIGHT_TOP_SERVO_OPEN = 0.41;
    public static final double RIGHT_TOP_SERVO_CLOSED = 0;
    public static final double LEFT_TOP_SERVO_OPEN = 0.51;
    public static final double LEFT_TOP_SERVO_CLOSED = 1;
//    public static final double RIGHT_LOWER_SERVO_OPEN = 0;
//    public static final double RIGHT_LOWER_SERVO_CLOSED = 0;
//    public static final double LEFT_LOWER_SERVO_OPEN = 0;
//    public static final double LEFT_LOWER_SERVO_CLOSED = 0;

    //JewelSystem
    public static final String RIGHT_TOP_JEWEL_SERVO_KEY = "RUJ";//Servo Port 2
    public static final String RIGHT_LOWER_JEWEL_SERVO_KEY = "RLJ";//Servo Port 3
    public static final String LEFT_TOP_JEWEL_SERVO_KEY = "LUJ";//Servo Port 4
    public static final String LEFT_LOWER_JEWEL_SERVO_KEY = "LLJ";//Servo Port 5
    public static final double RIGHT_TOP_SERVO_DOWN = 0;
    public static final double RIGHT_LOWER_SERVO_DOWN = 0;
    public static final double RIGHT_TOP_SERVO_UP = 0;
    public static final double RIGHT_LOWER_SERVO_UP = 0;
    public static final double LEFT_TOP_SERVO_DOWN = 0;
    public static final double LEFT_LOWER_SERVO_DOWN = 0;
    public static final double LEFT_TOP_SERVO_UP = 0;
    public static final double LEFT_LOWER_SERVO_UP = 0;

    //SensorSystem
    public static final String IMU_KEY = "IMU";//I2C 0
    public static final String COLOR_SENSOR_KEY = "CS";//I2C 1


    public SimpleColor ALLIANCE;

    public Ultron(OpMode opMode, SimpleColor alliance, Boolean twoDrivers) {
        super(opMode);
        ALLIANCE = alliance;
        TWO_DRIVERS = twoDrivers;
        putSubSystem("sensor", new SensorSystem(this));
        putSubSystem("drive", new DriveSystem(this));
        putSubSystem("lift", new LiftSystem(this));
        putSubSystem("cube", new CubeSystem(this));
        putSubSystem("jewel", new JewelSystem(this));
    }
}
