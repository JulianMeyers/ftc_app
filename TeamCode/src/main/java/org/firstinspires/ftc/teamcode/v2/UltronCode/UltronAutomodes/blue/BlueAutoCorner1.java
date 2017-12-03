package org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.UltronAuto;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronAutomodes.UltronAutoBlue;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.RobotSubSystems.LiftSystem;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronUtil.SimpleColor;

/**
 * Created by Julian on 11/15/2017.
 */
@Autonomous(name = "BlueAutoCorner1")
public class BlueAutoCorner1 extends UltronAutoBlue{

    @Override
    public void main() {
        cubeSystem.closeTop();
        autoGoToLiftPos(LiftSystem.LiftState.HALF_CUBE_HEIGHT);

        jewelSystem.rightDown();

        int RCSRed = sensorSystem.getRedColor();
        int RCSBlue = sensorSystem.getBlueColor();

        if (RCSRed > RCSBlue) {//Sees Red
            driveBackwardDistance(0.5,1000);// go backwards
            jewelSystem.rightUp();
            driveForwardDistance(0.5, 1000);
        } else if (RCSBlue > RCSRed){//Sees Blue
            driveForwardDistance(0.5, 1000);// go forwards
            jewelSystem.rightUp();//raise arm
            driveBackwardDistance(0.5,1000);//go backwards
        } else {
            // something happened! don't move
        }

        driveTime(0.5, 2);
        turn(Math.PI/2, 0.5);
        driveTime(0.5, 3);
        cubeSystem.openTop();


    }
}
