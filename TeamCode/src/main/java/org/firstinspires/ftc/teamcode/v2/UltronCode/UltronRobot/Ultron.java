package org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.General.Robot;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.RobotSubSystems.DriveSystem;
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
    public static final String RIGHT_TOP_SERVO_KEY = "RTS";
    public static final String LEFT_TOP_SERVO_KEY = "LTS";
    public static final String RIGHT_LOWER_SERVO_KEY = "RLS";
    public static final String LEFT_LOWER_SERVO_KEY = "LLS";

    public static SimpleColor ALLIANCE_COLOR;

    public Ultron(OpMode opMode, SimpleColor alliance) {
        super(opMode);
        ALLIANCE_COLOR = alliance;
        putSubSystem("drive", new DriveSystem(this));
        putSubSystem("lift", new LiftSystem(this));
    }
}
