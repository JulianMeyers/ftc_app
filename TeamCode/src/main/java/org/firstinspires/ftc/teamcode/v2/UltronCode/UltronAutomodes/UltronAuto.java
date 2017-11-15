package org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes;

import org.firstinspires.ftc.teamcode.v2.KNO3Stuff.KNO3OpMode.AutonomousProgram;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.RobotSubSystems.DriveSystem;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.General.Robot;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.Ultron;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronUtil.SimpleColor;

/**
 * Created by Julian on 11/15/2017.
 */

public abstract class UltronAuto extends AutonomousProgram{
    public DriveSystem drive;

    private SimpleColor alliance;

    public UltronAuto(SimpleColor alliance) {this.alliance = alliance;}

    @Override
    protected Robot buildRobot() {
        Ultron ultron = new Ultron(this, alliance);
        drive = ultron.getSubSystem(DriveSystem.class);
        return ultron;
    }
}
