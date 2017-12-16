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

        if (RCSBlue > RCSRed) {
            driveBackwardsToGivenPosition(-0.5,-200);// go backwards
            jewelSystem.rightUp();
            driveForwardsToGivenPosition(0.5,200);
        } else if (RCSRed > RCSBlue){
            driveForwardsToGivenPosition(0.5,200);// go forwards
            jewelSystem.rightUp();//raise arm
            driveBackwardsToGivenPosition(-0.5,-200);//go backwards
        } else {
            // something happened! don't move
        }

        telemetry.update();
        driveForwardsToGivenPosition(0.5, 2500 );
        autoGoToLiftPos(LiftSystem.LiftState.ZERO_CUBE_HEIGHT);
        cubeSystem.openTop();
    }
}
