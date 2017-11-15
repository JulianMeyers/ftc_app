package org.firstinspires.ftc.teamcode.v2.UltronCode.UltronOpMode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.General.Robot;

/**
 * Created by Julian on 11/15/2017.
 */

public abstract class DriverControlledProgram extends OpMode {

    private Robot robot;

    protected abstract Robot buildRobot();

    protected void onStart() {
    }

    protected void onUpdate() {
    }

    protected void onStop() {
        robot.stopAllCompoonents();
    }

    @Override
    public final void init() {
        robot = buildRobot();

        robot.init();
    }

    public final void start() {
        onStart();
    }

    public final void loop() {
        robot.driverControlledUpdate();
        onUpdate();
        telemetry.addData("looping", "stuff");
    }

    public final void stop() {
        onStop();
    }

    protected final Robot getRobot() {return robot;}

}
