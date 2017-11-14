package org.firstinspires.ftc.teamcode.v2.KNO3Stuff.KNO3Automodes.red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * @author Jaxon A Brown
 */
@Autonomous(name = "redauto7 far close shoot ramp")
public class RedAuto7 extends RedAutoFarCloseBase {

    @Override
    public void main() {
        super.main();

        drive.turnPIDfast(220);
        drive.driveWithCorrectionFast(43, 220);
    }
}