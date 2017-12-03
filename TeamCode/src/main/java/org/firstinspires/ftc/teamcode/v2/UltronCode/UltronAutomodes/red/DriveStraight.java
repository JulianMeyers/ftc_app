package org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.UltronAuto;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.UltronAutoRed;

/**
 * Created by Julian on 12/2/2017.
 */
@Autonomous (name = "straight")
public class DriveStraight extends UltronAutoRed {
    @Override
    public void main() {
        driveTime(1,2);
    }
}
