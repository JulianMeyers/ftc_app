package org.firstinspires.ftc.teamcode.v2.UltronCode.UltronUtil;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.UltronAuto;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.UltronAutoRed;

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
        driveSystem.driveForward(-0.25);
        sleep(60000);
        driveSystem.driveForward(0);
        while (opModeIsActive()) {
            driveSystem.displayValues();
            telemetry.update();
        }
    }
}
