package org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.red.DualDrivers;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.AutoTransitioner;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.red.Base.RedAutoSideBase;

/**
 * Created by Julian on 12/16/2017.
 */
@Autonomous(name = "RedAutoSide2")
public class RedAutoSide2 extends RedAutoSideBase {
    @Override
    public void postInit() {
        super.postInit();
        AutoTransitioner.transitionOnStop(this,"Teleop Two");
    }
}
