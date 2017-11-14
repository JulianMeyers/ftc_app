package org.firstinspires.ftc.teamcode.v2.KNO3Stuff.KNO3Automodes.blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by jaxon on 12/2/2016.
 */
@Autonomous(name = "blueauto3 shoot ramp")
public class BlueAuto3 extends BlueAutoShootBase {

    @Override
    public void main() {
        super.main();

        drive.driveForStop(0.3, 10);

        drive.turnPIDfast(280);
        drive.driveWithCorrectionFast(65, 280);
    }
}
