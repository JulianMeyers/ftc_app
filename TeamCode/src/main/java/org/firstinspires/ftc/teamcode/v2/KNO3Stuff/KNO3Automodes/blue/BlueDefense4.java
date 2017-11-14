package org.firstinspires.ftc.teamcode.v2.KNO3Stuff.KNO3Automodes.blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by jaxon on 4/21/2017.
 */
@Autonomous(name = "bluedefense4 shoot quickdef")
public class BlueDefense4 extends BlueAutoShootBase {

    @Override
    public void main() {
        super.main();

        drive.turnPIDfast(225);
        collector.regurgitate(1);
        drive.driveWithCorrectionFastFloating(0.3, 50, 225);
    }
}
