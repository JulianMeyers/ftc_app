package org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.RobotSubSystems.VuforiaSystem;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronUtil.SimpleColor;

/**
 * Created by Seb on 2/1/18.
 */

public class UltronAutoRobot extends Ultron {
    public UltronAutoRobot(OpMode opMode, SimpleColor alliance, Boolean twoDrivers) {
        super(opMode, alliance, twoDrivers);
        putSubSystem("vuforia", new VuforiaSystem(this));
    }
}
