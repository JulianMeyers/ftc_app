package org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.blue.Base;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.AutoTransitioner;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.UltronAutoBlue;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.RobotSubSystems.LiftSystem;

/**
 * Created by Julian on 12/2/2017.
 */

public class BlueAutoSimpleBase extends UltronAutoBlue {

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

        int distanceForJewel = 300;

        if (RCSBlue > RCSRed) {
            driveBackwardsToGivenPosition(-0.5,-distanceForJewel);// go backwards
            jewelSystem.rightUp();
            waitFor(1);
            driveForwardsToGivenPosition(0.5,distanceForJewel);
        } else if (RCSRed > RCSBlue){
            driveForwardsToGivenPosition(0.5,distanceForJewel);// go forwards
            jewelSystem.rightUp();//raise arm
            waitFor(1);
            driveBackwardsToGivenPosition(-0.5,-distanceForJewel);//go backwards
        } else {
            // something happened! don't move
        }

        telemetry.update();
        driveForwardsToGivenPosition(0.5, 2500 );
        autoGoToLiftPos(LiftSystem.LiftState.ZERO_CUBE_HEIGHT);
        cubeSystem.openTop();
    }
}
