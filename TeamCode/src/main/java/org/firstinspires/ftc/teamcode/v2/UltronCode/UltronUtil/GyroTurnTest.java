package org.firstinspires.ftc.teamcode.v2.UltronCode.UltronUtil;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.UltronAuto;

/**
 * Created by Julian on 12/1/2017.
 */
@Autonomous (name = "GyroTest", group = "Util")
public class GyroTurnTest extends UltronAuto {

    public GyroTurnTest() {
        super(null);
    }

    @Override
    public void main() {
        try {
            driveSystem.gyroTurn(Math.PI/2,0.5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (opModeIsActive()) {
            telemetry.addData("Heading", sensorSystem.getYaw());
        }
    }
}
