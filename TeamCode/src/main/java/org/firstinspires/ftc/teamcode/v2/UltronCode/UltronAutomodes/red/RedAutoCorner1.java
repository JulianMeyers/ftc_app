package org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.red;

import android.hardware.Sensor;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.UltronAutoRed;

/**
 * Created by Julian on 11/15/2017.
 */
@Autonomous (name = "RedAutoCorner1")
public class RedAutoCorner1 extends UltronAutoRed {

    @Override
    public void main() {
        //Do stuff

        jewelSystem.rightDown();

        int RCSRed = sensorSystem.getRightColorSensorData()[0];
        int RCSBlue = sensorSystem.getRightColorSensorData()[1];

        // Assuming color sensor is facing forwards...

        if (RCSRed > RCSBlue) {
            // go backwards
        } else if  {
            // go forwards
        } else if (RCSRed == RCSBlue) {
            // something happened! don't move
        }

    }
}
