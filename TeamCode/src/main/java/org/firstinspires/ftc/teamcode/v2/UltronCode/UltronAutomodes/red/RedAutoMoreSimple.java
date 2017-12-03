package org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.UltronAutoRed;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.RobotSubSystems.LiftSystem;

/**
 * Created by Julian on 12/2/2017.
 */
@Autonomous (name = "RedAutoMoreSimple")
public class RedAutoMoreSimple extends UltronAutoRed {

    @Override
    public void main() {
        jewelSystem.rightDown();

        waitFor(3);
        int RCSRed = sensorSystem.getRedColor();
        int RCSBlue = sensorSystem.getBlueColor();
        telemetry.addData("Red",RCSRed);
        telemetry.addData("Blue",RCSBlue);

        if (RCSRed < RCSBlue) {//Sees Red
            driveBackwardDistance(0.5,1000);// go backwards
            jewelSystem.rightUp();
            driveForwardDistance(0.5, 1000);
        } else if (RCSBlue < RCSRed){//Sees Blue
            driveForwardDistance(0.5, 1000);// go forwards
            jewelSystem.rightUp();//raise arm
            driveBackwardDistance(0.5,1000);//go backwards
        } else {
            // something happened! don't move
        }

        driveTime(0.5, 2);
    }
}
