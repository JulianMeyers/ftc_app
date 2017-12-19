package org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.red.Base;

import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.UltronAutoRed;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.RobotSubSystems.LiftSystem;

/**
 * Created by Julian on 12/2/2017.
 */

public class RedAutoSideBase extends UltronAutoRed {

    @Override
    public void main() {
        cubeSystem.closeTop();
        waitFor(0.5);
        autoGoToLiftPos(LiftSystem.LiftState.ONE_CUBE_HEIGHT);

        jewelSystem.rightDown();

        waitFor(2);
        int RCSRed = sensorSystem.getRedColor();
        int RCSBlue = sensorSystem.getBlueColor();
        telemetry.addData("Red",RCSRed);
        telemetry.addData("Blue",RCSBlue);
        telemetry.update();

        knockOffJewelStraight(RCSBlue,RCSRed);

        turnAbsolute(Math.PI/2);//Turn left 90 degrees
        driveForwardsToGivenPosition(0.5,450);//450 For Right, ??? for center, ??? for far
        turnAbsolute(0);//Counter turn
        autoGoToLiftPos(LiftSystem.LiftState.HALF_CUBE_HEIGHT);
        waitFor(0.5);
        driveForwardsToGivenPosition(0.5,400);
        driveTime(1,1);//Full speed into the cryptobox in case we are not lined up
        cubeSystem.openTop();
        driveBackwardsToGivenPosition(-0.5,-400);
        autoGoToLiftPos(LiftSystem.LiftState.ZERO_CUBE_HEIGHT);
        driveTime(1,1);
        waitFor(1);
        driveBackwardsToGivenPosition(-0.5,-300);

    }

    private void knockOffJewelStraight(int Blue, int Red) {
        int difference = Math.abs(Blue-Red);
        if (difference > 4) {
            if (Blue > Red) {
                driveBackwardsToGivenPosition(-0.5, -150);// go backwards
                jewelSystem.rightUp();
                waitFor(0.5);
                driveForwardsToGivenPosition(0.5, 2150);
            } else if (Red > Blue) {
                driveForwardsToGivenPosition(0.5, 150);// go forwards
                jewelSystem.rightUp();//raise arm
                waitFor(0.5);
                driveForwardsToGivenPosition(0.5, 1800);
            } else {
                jewelSystem.rightUp();
                waitFor(0.5);
                driveForwardsToGivenPosition(0.5, 1900);
            }
        }
    }
}
