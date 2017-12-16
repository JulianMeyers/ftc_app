package org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.red.DualDrivers;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.AutoTransitioner;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.red.Base.RedAutoSimpleBase;

/**
 * Created by Julian on 12/15/2017.
 */
@Autonomous(name = "RedAutoSimple2")
public class RedAutoSimple2 extends RedAutoSimpleBase {
    @Override
    public void postInit() {
        super.postInit();
        AutoTransitioner.transitionOnStop(this,"Teleop Two");
    }
}
