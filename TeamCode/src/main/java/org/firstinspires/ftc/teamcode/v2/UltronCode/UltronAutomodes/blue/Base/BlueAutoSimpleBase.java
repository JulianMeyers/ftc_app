package org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.blue.Base;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.AutoTransitioner;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.UltronAutoBlue;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.RobotSubSystems.LiftSystem;

/**
 * Created by Julian on 12/2/2017.
 */

public class BlueAutoSimpleBase extends UltronAutoBlue {

    @Override
    public void main() {
        cubeSystem.closeTop();
        waitFor(2);
        autoGoToLiftPos(LiftSystem.LiftState.HALF_CUBE_HEIGHT);

        jewelSystem.rightDown();

        waitFor(3);
        int RCSRed = sensorSystem.getRedColor();
        int RCSBlue = sensorSystem.getBlueColor();
        telemetry.addData("Red",RCSRed);
        telemetry.addData("Blue",RCSBlue);

        if (RCSRed > RCSBlue) {//Sees Red
            driveTime(0.5,0.5);// go backwards
            jewelSystem.rightUp();
            waitFor(1);
            //driveTime(-0.5,0.5);
            telemetry.addData("I saw", "Red");
        } else if (RCSBlue > RCSRed){//Sees Blue
            driveTime(-0.5,0.5);// go forwards
            jewelSystem.rightUp();//raise arm
            waitFor(1);
            //driveTime(0.5,0.5);//go backwards
            telemetry.addData("I saw", "Blue");
        } else {
            // something happened! don't move
        }
        telemetry.update();
        driveTime(0.5, 0.5);
        autoGoToLiftPos(LiftSystem.LiftState.ZERO_CUBE_HEIGHT);
        cubeSystem.openTop();
    }
}
