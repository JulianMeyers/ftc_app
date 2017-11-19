package org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.General.Robot;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.RobotSubSystems.CubeSystem;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.RobotSubSystems.DriveSystem;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.RobotSubSystems.JewelSystem;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.RobotSubSystems.LiftSystem;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronUtil.SimpleColor;

/**
 * Created by Julian on 11/14/2017.
 */

public class Ultron extends Robot{

    //DriveSystem
    public static final String DRIVE_FL_KEY = "LFD";
    public static final String DRIVE_RL_KEY = "LRD";
    public static final String DRIVE_FR_KEY = "RFD";
    public static final String DRIVE_RR_KEY = "RRD";

    //LiftSystem
    public static final String LIFT_R_WINCH_KEY = "RL";
    public static final String LIFT_L_WINCH_KEY = "LL";
    public static final int ZERO_CUBE_HEIGHT = 0;
    public static final int HALF_CUBE_HEIGHT = 0;
    public static final int ONE_CUBE_HEIGHT = 0;
    public static final int TWO_CUBE_HEIGHT = 0;
    public static final int THREE_CUBE_HEIGHT = 0;

    //CubeSystem
    public static final String RIGHT_TOP_SERVO_KEY = "RTS";
    public static final String LEFT_TOP_SERVO_KEY = "LTS";
    public static final String RIGHT_LOWER_SERVO_KEY = "RLS";
    public static final String LEFT_LOWER_SERVO_KEY = "LLS";
    public static final double RIGHT_TOP_SERVO_OPEN = 0;
    public static final double RIGHT_TOP_SERVO_CLOSED = 0;
    public static final double LEFT_TOP_SERVO_OPEN = 0;
    public static final double LEFT_TOP_SERVO_CLOSED = 0;
    public static final double RIGHT_LOWER_SERVO_OPEN = 0;
    public static final double RIGHT_LOWER_SERVO_CLOSED = 0;
    public static final double LEFT_LOWER_SERVO_OPEN = 0;
    public static final double LEFT_LOWER_SERVO_CLOSED = 0;

    //JewelSystem
    public static final String RIGHT_TOP_JEWEL_SERVO_KEY = "UJ";
    public static final String RIGHT_LOWER_JEWEL_SERVO_KEY = "LJ";
    public static final String LEFT_TOP_JEWEL_SERVO_KEY = "UJ";
    public static final String LEFT_LOWER_JEWEL_SERVO_KEY = "LJ";
    public static final double RIGHT_TOP_SERVO_DOWN = 0;
    public static final double RIGHT_LOWER_SERVO_DOWN = 0;
    public static final double RIGHT_TOP_SERVO_UP = 0;
    public static final double RIGHT_LOWER_SERVO_UP = 0;
    public static final double LEFT_TOP_SERVO_DOWN = 0;
    public static final double LEFT_LOWER_SERVO_DOWN = 0;
    public static final double LEFT_TOP_SERVO_UP = 0;
    public static final double LEFT_LOWER_SERVO_UP = 0;

    public SimpleColor ALLIANCE;

    public Ultron(OpMode opMode, SimpleColor alliance, Boolean twoDrivers) {
        super(opMode);
        ALLIANCE = alliance;
        TWO_DRIVERS = twoDrivers;
        putSubSystem("drive", new DriveSystem(this));
        putSubSystem("lift", new LiftSystem(this));
        putSubSystem("cube", new CubeSystem(this));
        putSubSystem("jewel", new JewelSystem(this));
    }
}
