package org.firstinspires.ftc.teamcode.v2.UltronCode.UltronUtil;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.UltronAuto;

/**
 * Created by Julian on 11/19/2017.
 */
@Autonomous (name = "Drive Motor Test", group = "Util")
public class DriveMotorTest extends UltronAuto {

    public DriveMotorTest() {
        super(null);
    }

    @Override
    public void main() {
        driveForwardsToGivenPosition(0.75, 1000);
        telemetry.addData("Current Distance", driveSystem.getRightEncoderValue());
        telemetry.update();
        sleep(2000);
        driveBackwardsToGivenPosition(-0.75, -1000);
        telemetry.addData("Current Distance", driveSystem.getRightEncoderValue());
        telemetry.update();
        sleep(2000);
    }
}
