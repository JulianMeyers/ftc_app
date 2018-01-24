package org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.blue.Base;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.UltronAutoBlue;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.RobotSubSystems.LiftSystem;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.RobotSubSystems.VuforiaSystem;

/**
 * Created by Julian on 11/15/2017.
 */

public class BlueAutoSideBase extends UltronAutoBlue {

    @Override
    public void main() {
        long stopVuSearch = System.currentTimeMillis() + 5000;
        VuforiaSystem.CryptoboxKey cryptoboxKey;
        int[] positionSeen = new int[3];//Right is 0, center is 1, left is 2
        while (opModeIsActive() && System.currentTimeMillis()< stopVuSearch) {
            vuMark = vuforiaSystem.checkForVuMark();
            switch (vuMark) {
                case RIGHT:
                    positionSeen[0]++;
                    break;
                case CENTER:
                    positionSeen[1]++;
                    break;
                case LEFT:
                    positionSeen[2]++;
                    break;
                case UNKNOWN:
                    break;
            }
        }

        int maxPos = 0;
        for (int pos:positionSeen) {
            if (pos>maxPos)
                maxPos = pos;
        }

        if (maxPos == positionSeen[0]) {
            telemetry.addData("I Saw", "Right");
            cryptoboxKey = VuforiaSystem.CryptoboxKey.RIGHT;
        } else if (maxPos == positionSeen[1]) {
            telemetry.addData("I Saw", "Center");
            cryptoboxKey = VuforiaSystem.CryptoboxKey.CENTER;
        } else {
            telemetry.addData("I Saw", "Left");
            cryptoboxKey = VuforiaSystem.CryptoboxKey.LEFT;
        }
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

        turnAbsolute(-Math.PI/2);//Turn left 90 degrees
        switch (cryptoboxKey) {
            case LEFT:
                driveForwardsToGivenPosition(0.5, 250);//Will likely need to be changed
            case CENTER:
                driveForwardsToGivenPosition(0.5, 350);//Will likely need to be changed
            case RIGHT:
                driveForwardsToGivenPosition(0.5, 550);//Will possibly need to be changed
        }
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
