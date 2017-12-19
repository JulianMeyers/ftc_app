package org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.blue.Base;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.UltronAutoBlue;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.RobotSubSystems.LiftSystem;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.RobotSubSystems.VuforiaSystem;

/**
 * Created by Julian on 11/15/2017.
 */

public class BlueAutoCornerBase extends UltronAutoBlue {

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
        autoGoToLiftPos(LiftSystem.LiftState.HALF_CUBE_HEIGHT);

        jewelSystem.rightDown();
        waitFor(3);
        int RCSRed = sensorSystem.getRedColor();
        int RCSBlue = sensorSystem.getBlueColor();

        // Assuming color sensor is facing forwards...

        if (RCSRed > RCSBlue) {
            driveBackwardsToGivenPosition(-0.5,-200);// go backwards
            jewelSystem.rightUp();
            driveForwardsToGivenPosition(0.5,200);
        } else if (RCSBlue > RCSRed){
            driveForwardsToGivenPosition(0.5,200);// go forwards
            jewelSystem.rightUp();//raise arm
            driveBackwardsToGivenPosition(-0.5,-200);//go backwards
        } else {
            // something happened! don't move
        }

        switch (cryptoboxKey) {
            case RIGHT:
                driveStraightForward(1500, 0.5);//I need calibration
            case CENTER:
                driveStraightForward(2000, 0.5);//I need calibration
            case LEFT:
                driveStraightForward(2500, 0.5);//I need calibration
        }

        turn(-Math.PI/2);//Gyroturn right 90 degrees probably
        driveTime(0.75,3);//Go forwards time(to ensure that we get as close as possible to making it in

        cubeSystem.openTop();
        driveBackwardsToGivenPosition(-0.5, -200);


    }
}
