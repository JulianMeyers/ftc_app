package org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.red.DualDrivers;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.AutoTransitioner;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.red.Base.RedAutoCornerBase;

/**
 * Created by Julian on 12/15/2017.
 */
@Autonomous(name = "RedAutoCorner2")
public class RedAutoCorner2 extends RedAutoCornerBase {
    @Override
    public void postInit() {
        super.postInit();
        AutoTransitioner.transitionOnStop(this,"Teleop Two");
    }
}
