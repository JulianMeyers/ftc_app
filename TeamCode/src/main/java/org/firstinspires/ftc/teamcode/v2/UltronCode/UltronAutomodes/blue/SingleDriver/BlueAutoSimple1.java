package org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.blue.SingleDriver;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.AutoTransitioner;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.UltronAutoBlue;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.blue.Base.BlueAutoSimpleBase;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.RobotSubSystems.LiftSystem;

/**
 * Created by Julian on 12/2/2017.
 */
@Autonomous (name = "BlueAutoSimple1")
public class BlueAutoSimple1 extends BlueAutoSimpleBase {
    @Override
    public void postInit() {
        super.postInit();
        AutoTransitioner.transitionOnStop(this,"Teleop One");
    }
}