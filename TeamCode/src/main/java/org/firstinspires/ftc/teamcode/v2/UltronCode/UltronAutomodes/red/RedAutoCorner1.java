package org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.red;

import android.hardware.Sensor;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.UltronAutoRed;

import java.util.Collections;

/**
 * Created by Julian on 11/15/2017.
 */
@Autonomous (name = "RedAutoCorner1")
public class RedAutoCorner1 extends UltronAutoRed {

    enum CryptoboxKey{
        RIGHT,CENTER,LEFT
    }

    @Override
    public void main() {
        relicTrackables.activate();
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
        long stopVuSearch = System.currentTimeMillis() + 5000;
        CryptoboxKey cryptoboxKey = CryptoboxKey.RIGHT;
        int[] positionSeen = new int[3];//Right is 0, center is 1, left is 2
        while (opModeIsActive() && System.currentTimeMillis()< stopVuSearch) {
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
            cryptoboxKey = CryptoboxKey.RIGHT;
        } else if (maxPos == positionSeen[1]) {
            cryptoboxKey = CryptoboxKey.CENTER;
        } else {
            cryptoboxKey = CryptoboxKey.LEFT;
        }

        jewelSystem.rightDown();

        int RCSRed = sensorSystem.getRightColorSensorData()[0];
        int RCSBlue = sensorSystem.getRightColorSensorData()[1];

        // Assuming color sensor is facing forwards...

        if (RCSRed > RCSBlue) {
            // go backwards
            //raise arm
            //go forwards
        } else if (RCSBlue > RCSRed){
            // go forwards
            //raise arm
            //go backwards
        } else {
            // something happened! don't move
        }
        //Go to forwards correct amount based on cryptoboxkey
        //Gyroturn right 90 degrees probably
        //Go forwards time(to ensure that we get as close as possible to making it in


    }
}
