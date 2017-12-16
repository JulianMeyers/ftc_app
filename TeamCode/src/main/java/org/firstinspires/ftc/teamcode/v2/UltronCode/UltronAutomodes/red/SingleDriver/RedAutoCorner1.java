package org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.red.SingleDriver;

import org.firstinspires.ftc.teamcode.AutoTransitioner;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.red.Base.RedAutoCornerBase;

/**
 * Created by Julian on 12/15/2017.
 */

public class RedAutoCorner1 extends RedAutoCornerBase {
    @Override
    public void postInit() {
        super.postInit();
        AutoTransitioner.transitionOnStop(this,"Teleop One");
    }
}
