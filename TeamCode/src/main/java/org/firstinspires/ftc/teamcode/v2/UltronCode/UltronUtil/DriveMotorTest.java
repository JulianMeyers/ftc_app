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
        driveSystem.modeReset();
        driveSystem.modeVoltage();
        driveSystem.brakeMode();
        driveSystem.driveForward(1);
        waitFor(1);
        driveSystem.driveForward(0);
        while (opModeIsActive()) {
            driveSystem.displayPositions();
            telemetry.update();
        }
    }
}
