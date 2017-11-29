package org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.UltronAuto;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.UltronAutoBlue;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronUtil.SimpleColor;

/**
 * Created by Julian on 11/15/2017.
 */
@Autonomous(name = "BlueAutoCorner1")
public class BlueAutoCorner1 extends UltronAutoBlue{

    @Override
    public void main() {
        //Do stuff

        jewelSystem.leftDown();

        int LCSRed = sensorSystem.getLeftColorSensorData()[0];
        int LCSBlue = sensorSystem.getLeftColorSensorData()[1];

    }
}
