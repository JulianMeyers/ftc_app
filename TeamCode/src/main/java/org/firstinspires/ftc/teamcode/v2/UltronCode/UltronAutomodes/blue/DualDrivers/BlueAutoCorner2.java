package org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.blue.DualDrivers;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.AutoTransitioner;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.UltronAutoBlue;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.blue.Base.BlueAutoCornerBase;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.blue.SingleDriver.BlueAutoCorner1;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.RobotSubSystems.LiftSystem;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.RobotSubSystems.VuforiaSystem;

/**
 * Created by Julian on 11/15/2017.
 */
@Autonomous (name = "BlueAutoCorner2")
public class BlueAutoCorner2 extends BlueAutoCornerBase {
    @Override
    public void postInit() {
        super.postInit();
        AutoTransitioner.transitionOnStop(this,"Teleop Two");
    }
}