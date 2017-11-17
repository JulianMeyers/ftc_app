package org.firstinspires.ftc.teamcode.v2.UltronCode.UltronTeleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronOpMode.DriverControlledProgram;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.General.Robot;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.Ultron;

/**
 * Created by Julian on 11/15/2017.
 */
@TeleOp(name = "Teleop Two")
public class UltronTeleopTwoDriver extends DriverControlledProgram{
    @Override
    protected Robot buildRobot() {
        return new Ultron(this, null, true);
    }
}
