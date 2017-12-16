package org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.blue.SingleDriver;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.AutoTransitioner;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.blue.Base.BlueAutoSideBase;

/**
 * Created by Julian on 12/16/2017.
 */
@Autonomous(name = "BlueAutoSide1")
public class BlueAutoSide1 extends BlueAutoSideBase {

    @Override
    public void postInit() {
        super.postInit();
        AutoTransitioner.transitionOnStop(this,"Teleop One");
    }
}
