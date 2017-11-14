package org.firstinspires.ftc.teamcode.v2.KNO3Stuff.KNO3Automodes.blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * @author Jaxon A Brown
 */
@Autonomous(name = "blueauto6 far close shoot cap")
public class BlueAuto6 extends BlueAutoFarCloseBase {

    @Override
    public void main() {
        super.main();
        waitFor(1);

        drive.driveFor(0.5, -10);
        drive.turnPIDfast(45);
        drive.turnPIDfast(90);
        drive.driveForStop(0.5, -15);
    }
}