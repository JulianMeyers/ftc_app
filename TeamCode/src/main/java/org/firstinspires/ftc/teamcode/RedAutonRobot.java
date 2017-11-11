/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@Autonomous(name="Red Auton Robot", group="Linear Opmode")
public class RedAutonRobot extends LinearOpMode {

    HardwareUltron robot = new HardwareUltron();
    private ElapsedTime runTime;

    boolean sawRed;
    boolean sawBlue;


    @Override
    public void runOpMode() {
        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        robot.init(hardwareMap);

         runTime  = new ElapsedTime();

         sawRed = false;
         sawBlue = false;

        // Set the LED in the beginning
        robot.colorSensor.enableLed(true);

        robot.rightLiftMotor.setPower(0);
        robot.leftLiftMotor.setPower(0);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runTime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive() && runTime.seconds()<0.1) {
        }

        robot.lowerJewel.setPosition(0);//out
        robot.upperJewel.setPosition(0.6);//out

        while(runTime.seconds() < 5.0 && opModeIsActive()){
            telemetry.addData("Red: ", robot.colorSensor.red());
            telemetry.addData("Blue: ", robot.colorSensor.blue());
            telemetry.addData("Clear: ", robot.colorSensor.alpha());
            telemetry.update();
            if(robot.colorSensor.red()>0 && robot.colorSensor.blue() == 0){
                sawRed = true;
            }
            if(robot.colorSensor.blue()>0 && robot.colorSensor.red() == 0){
                sawBlue = true;
            }
        }

        if (sawBlue){
            telemetry.addData("I saw: ", "Red");
            telemetry.update();
            robot.rightFrontDrive.setPower(-1);
            robot.leftFrontDrive.setPower(-1);
            robot.rightRearDrive.setPower(-1);
            robot.leftRearDrive.setPower(-1);

            while(runTime.seconds() < 5.5 && opModeIsActive()){
            }

            robot.rightFrontDrive.setPower(0);
            robot.leftFrontDrive.setPower(0);
            robot.rightRearDrive.setPower(0);
            robot.leftRearDrive.setPower(0);
        }else if (sawRed){
            telemetry.addData("I saw: ", "Red");
            telemetry.update();
            robot.rightFrontDrive.setPower(1);
            robot.leftFrontDrive.setPower(1);
            robot.rightRearDrive.setPower(1);
            robot.leftRearDrive.setPower(1);

            while(runTime.seconds() < 5.5 && opModeIsActive()){
            }

            robot.rightFrontDrive.setPower(0);
            robot.leftFrontDrive.setPower(0);
            robot.rightRearDrive.setPower(0);

            robot.leftRearDrive.setPower(0);
        }



    }
}
