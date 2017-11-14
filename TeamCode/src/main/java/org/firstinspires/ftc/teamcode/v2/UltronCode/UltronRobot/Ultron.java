package org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.General.Robot;
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

    public static SimpleColor ALLIANCE_COLOR;

    public Ultron(OpMode opMode, SimpleColor alliance) {
        super(opMode);
        ALLIANCE_COLOR = alliance;
        putSubSystem("drive", new DriveSystem(this));
    }
}
