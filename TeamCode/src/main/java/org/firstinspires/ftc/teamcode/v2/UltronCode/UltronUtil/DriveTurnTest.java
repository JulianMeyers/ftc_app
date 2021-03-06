package org.firstinspires.ftc.teamcode.v2.UltronCode.UltronUtil;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.UltronAuto;

/**
 * Created by Julian on 12/17/2017.
 */
@Autonomous(name = "DriveTurnTest", group = "Util")
public class DriveTurnTest extends UltronAuto {
    public DriveTurnTest() {
        super(null);
    }

    @Override
    public void main() {
        driveSystem.driveAngle(0,Math.PI/2,1);
        sleep(1000);
        driveSystem.stopMotors();
    }
}
