package org.firstinspires.ftc.teamcode.v2.UltronCode.UltronOpMode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.General.Robot;

/**
 * Created by Julian on 11/15/2017.
 */

public abstract class AutonomousProgram extends LinearOpMode{
    private Robot robot;

    protected abstract Robot buildRobot();

    public abstract void main();

    public void postInit() {};

    public final void runOpMode () throws InterruptedException {
        robot = buildRobot();

        try {
            robot.init();
            postInit();
        }catch (Exception ex) {
            telemetry.addData("Error", ex.getMessage());
            ex.printStackTrace();
        }

        waitForStart();

        try {
            main();
        } catch(Exception ex) {
            telemetry.addData("Error", ex.getMessage());
            ex.printStackTrace();
        }
    }

    protected final Robot getRobot() {return robot;}

    protected final void waitFor(double seconds) {
        long stopTime = System.currentTimeMillis() + (int) (seconds * 1000);
        while(opModeIsActive() && System.currentTimeMillis() < stopTime) {}
    }
}
