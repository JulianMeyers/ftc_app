package org.firstinspires.ftc.teamcode.v2.KNO3Stuff.KNO3Automodes.red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * @author Jaxon A Brown
 */
@Autonomous(name = "redauto4 close far shoot cap")
public class RedAuto4 extends RedAutoCloseFarBase {

    @Override
    public void main() {
        super.main();

        drive.driveFor(0.5, -20);
    }
}