package org.firstinspires.ftc.teamcode.v2.KNO3Stuff.KNO3Automodes.blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by jaxon on 4/21/2017.
 */
@Autonomous(name = "bluedefense1 shoot 2nddef")
public class BlueDefense2 extends BlueAutoShootBase {

    @Override
    public void main() {
        super.main();

        drive.turnPIDfast(145);
        drive.driveWithCorrectionFast(45, 145);
    }
}
