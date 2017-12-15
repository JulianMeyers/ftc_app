package org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.UltronAutoRed;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.RobotSubSystems.LiftSystem;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.RobotSubSystems.VuforiaSystem;

/**
 * Created by Julian on 11/15/2017.
 */
@Autonomous (name = "RedAutoCorner1")
public class RedAutoCorner1 extends UltronAutoRed {

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
        autoGoToLiftPos(LiftSystem.LiftState.HALF_CUBE_HEIGHT);

        jewelSystem.rightDown();
        waitFor(3);
        int RCSRed = sensorSystem.getRedColor();
        int RCSBlue = sensorSystem.getBlueColor();

        // Assuming color sensor is facing forwards...

        if (RCSRed > RCSBlue) {
            driveBackwardDistance(0.5,200);// go backwards
            jewelSystem.rightUp();
            driveForwardDistance(0.5, 200);
        } else if (RCSBlue > RCSRed){
            driveForwardDistance(0.5, 200);// go forwards
            jewelSystem.rightUp();//raise arm
            driveBackwardDistance(0.5,200);//go backwards
        } else {
            // something happened! don't move
        }

        switch (cryptoboxKey) {
            case RIGHT:
                driveStraightForward(500, 0.5);
            case CENTER:
                driveStraightForward(700, 0.5);
            case LEFT:
                driveStraightForward(900, 0.5);
        }

        turn(Math.PI/2,0.5);//Gyroturn right 90 degrees probably
        driveTime(0.75,5);//Go forwards time(to ensure that we get as close as possible to making it in

        cubeSystem.openTop();
        driveBackwardDistance(500, 0.5);


    }
}
