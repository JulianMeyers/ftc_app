package org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.red.SingleDriver;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.AutoTransitioner;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.red.Base.RedAutoSideBase;

/**
 * Created by Julian on 12/16/2017.
 */
@Autonomous(name = "RedAutoSide1")
public class RedAutoSide1 extends RedAutoSideBase {
    @Override
    public void postInit() {
        super.postInit();
        AutoTransitioner.transitionOnStop(this,"Teleop One");
    }
}
