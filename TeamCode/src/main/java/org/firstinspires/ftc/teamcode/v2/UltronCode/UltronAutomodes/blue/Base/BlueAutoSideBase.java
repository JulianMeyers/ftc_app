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
        VuforiaSystem.CryptoboxKey cryptoboxKey = VuforiaSystem.CryptoboxKey.LEFT;
        RelicRecoveryVuMark vuMark = identifyVuMark(5000);

        switch (vuMark) {
            case RIGHT:
                telemetry.addData("I saw", "Right");
                cryptoboxKey = VuforiaSystem.CryptoboxKey.RIGHT;
                break;
            case CENTER:
                telemetry.addData("I saw", "Center");
                cryptoboxKey = VuforiaSystem.CryptoboxKey.CENTER;
                break;
            case LEFT:
                telemetry.addData("I saw", "Left");
                cryptoboxKey = VuforiaSystem.CryptoboxKey.LEFT;
                break;
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

        int distanceForJewel = 300;

//        if (RCSBlue > RCSRed) {
//            driveBackwardsToGivenPosition(-0.5,-distanceForJewel);// go backwards
//            jewelSystem.rightUp();
//            waitFor(1);
//            driveForwardsToGivenPosition(0.5,distanceForJewel);
//        } else if (RCSRed > RCSBlue){
//            driveForwardsToGivenPosition(0.5,distanceForJewel);// go forwards
//            jewelSystem.rightUp();//raise arm
//            waitFor(1);
//            driveBackwardsToGivenPosition(-0.5,-distanceForJewel);//go backwards
//        } else {
//            // something happened! don't move
//        }

        double turnForJewel = Math.PI/12;

        if (RCSBlue > RCSRed) {
            turn(turnForJewel,0.5);// go backwards
            jewelSystem.rightUp();
            waitFor(1);
            turn(-turnForJewel,0.5);
        } else if (RCSRed > RCSBlue){
            turn(-turnForJewel,-0.5);// go forwards
            jewelSystem.rightUp();
            waitFor(1);
            turn(turnForJewel,0.5);
        } else {
            // something happened! don't move
        }
        telemetry.update();

        driveForwardsToGivenPosition(0.5, 2000 );

        turn(-Math.PI/2,0.5);

        driveForwardsToGivenPosition(0.5,500);

        turn(Math.PI/2,0.5);

        driveTime(0.5,1);

        cubeSystem.openTop();

        driveTime(-0.5,0.1);

        autoGoToLiftPos(LiftSystem.LiftState.ZERO_CUBE_HEIGHT);
    }
}
