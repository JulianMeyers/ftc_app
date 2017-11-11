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

import android.app.Activity;
import android.view.View;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


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

@Autonomous(name="Red Auton", group="Linear Opmode")
public class RedAuton extends LinearOpMode {

    // Declare OpMode members.

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontDrive = null;
    private DcMotor leftRearDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightRearDrive = null;
    private DcMotor rightLiftMotor = null;
    private DcMotor leftLiftMotor = null;

    private Servo rightTopManipulator = null;
    private Servo leftTopManipulator = null;
    private Servo rightLowerManipulator = null;
    private Servo leftLowerManipulator = null;

    private Servo lowerJewel = null;
    private Servo upperJewel = null;

    private ColorSensor colorSensor = null;

    @Override
    public void runOpMode() {
        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        leftFrontDrive  = hardwareMap.get(DcMotor.class, "LFD");
        leftRearDrive = hardwareMap.get(DcMotor.class, "LRD");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "RFD");
        rightRearDrive = hardwareMap.get(DcMotor.class, "RRD");
        rightLiftMotor = hardwareMap.get(DcMotor.class, "RL");
        leftLiftMotor = hardwareMap.get(DcMotor.class, "LL");

        rightTopManipulator = hardwareMap.get(Servo.class, "TRS");
        leftTopManipulator = hardwareMap.get(Servo.class, "TLS");
        rightLowerManipulator = hardwareMap.get(Servo.class, "BRS");
        leftLowerManipulator = hardwareMap.get(Servo.class, "BLS");

        lowerJewel = hardwareMap.get(Servo.class, "LJ");
        upperJewel = hardwareMap.get(Servo.class, "UJ");

        colorSensor = hardwareMap.get(ColorSensor.class, "SC");

        // Set the LED in the beginning
        colorSensor.enableLed(true);

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        leftRearDrive.setDirection(DcMotor.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);//
        rightRearDrive.setDirection(DcMotor.Direction.REVERSE);
        rightLiftMotor.setDirection(DcMotor.Direction.FORWARD);
        leftLiftMotor.setDirection(DcMotor.Direction.REVERSE);

        leftFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        leftRearDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightRearDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightLiftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftLiftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
        telemetry.addData("Encoder Value", rightLiftMotor.getCurrentPosition());

        rightLiftMotor.setPower(0);
        leftLiftMotor.setPower(0);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive() && runtime.seconds()<0.1) {
        }

        lowerJewel.setPosition(0);//out
        upperJewel.setPosition(0.6);//out

        while(runtime.seconds() < 1.0 && opModeIsActive()){
        }

        if (colorSensor.blue()>2){
            telemetry.addData("I saw: ", "Red");
            rightFrontDrive.setPower(1);
            leftFrontDrive.setPower(1);
            rightRearDrive.setPower(1);
            leftRearDrive.setPower(1);

            while(runtime.seconds() < 1.5 && opModeIsActive()){
            }

            rightFrontDrive.setPower(0);
            leftFrontDrive.setPower(0);
            rightRearDrive.setPower(0);
            leftRearDrive.setPower(0);
        }else if (colorSensor.red() > 2){
            telemetry.addData("I saw: ", "Red");
            rightFrontDrive.setPower(-1);
            leftFrontDrive.setPower(-1);
            rightRearDrive.setPower(-1);
            leftRearDrive.setPower(-1);

            while(runtime.seconds() < 1.5 && opModeIsActive()){
            }

            rightFrontDrive.setPower(0);
            leftFrontDrive.setPower(0);
            rightRearDrive.setPower(0);

            leftRearDrive.setPower(0);
        }



    }
}
