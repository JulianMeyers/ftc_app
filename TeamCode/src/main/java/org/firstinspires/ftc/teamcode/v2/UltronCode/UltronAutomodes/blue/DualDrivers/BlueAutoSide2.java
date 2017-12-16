package org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.blue.DualDrivers;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.AutoTransitioner;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.blue.Base.BlueAutoSideBase;

/**
 * Created by Julian on 12/16/2017.
 */
@Autonomous(name = "BlueAutoSide2")
public class BlueAutoSide2 extends BlueAutoSideBase {
    @Override
    public void postInit() {
        super.postInit();
        AutoTransitioner.transitionOnStop(this,"Teleop Two");
    }
}
