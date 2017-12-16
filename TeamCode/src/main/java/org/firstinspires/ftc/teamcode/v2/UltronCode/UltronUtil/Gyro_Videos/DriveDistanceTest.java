package org.firstinspires.ftc.teamcode.v2.UltronCode.UltronUtil.Gyro_Videos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.UltronAuto;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronUtil.SimpleColor;

/**
 * Created by Julian on 12/15/2017.
 */
@Autonomous (name = "DriveDistanceTest", group = "Util")
public class DriveDistanceTest extends UltronAuto {
    public DriveDistanceTest() {
        super(null);
    }

    @Override
    public void main() {
        driveForwardDistance(0.5, 1000);
        sleep(5000);
        driveBackwardDistance(0.5, 1000);
    }
}
