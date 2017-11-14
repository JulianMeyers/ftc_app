package org.firstinspires.ftc.teamcode.v2.KNO3Stuff.KNO3Automodes.red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * @author Jaxon A Brown
 */
@Autonomous(name = "redauto5 close far shoot ramp")
public class RedAuto5 extends RedAutoCloseFarBase {

    @Override
    public void main() {
        super.main();

        drive.turnPIDfast(200);
        drive.driveWithCorrectionFast(68, 200, true);
    }
}