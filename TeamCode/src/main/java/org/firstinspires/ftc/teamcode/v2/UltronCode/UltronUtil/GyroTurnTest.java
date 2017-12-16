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
        sensorSystem.updateGyro();
        double initialTurn = sensorSystem.getYaw();
        double finalTurn = turn(Math.PI/2, 0.5);
//        while (opModeIsActive()) {
//            telemetry.addData("Initial Yaw", Math.toDegrees(initialTurn));
//            telemetry.addData("Final Yaw", Math.toDegrees(finalTurn));
//            sensorSystem.updateGyro();
//            telemetry.addData("Actual Yaw", Math.toDegrees(sensorSystem.getYaw()));
//            telemetry.update();
//        }
        sleep(2000);
        turn(-Math.PI/2, -0.5);
        sleep(2000);
    }
}
