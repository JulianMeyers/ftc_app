package org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.RobotSubSystems;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.detectors.JewelDetector;

import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.General.SubSystem;

/**
 * Created by Seb on 2/1/18.
 */

public class DogeCVSystem extends SubSystem {

    private JewelDetector jewelDetector = null;

    @Override
    public void init() {

        telemetry().addData("Status", "Initialized");

        jewelDetector = new JewelDetector();
        jewelDetector.init(hardwareMap().appContext, CameraViewDisplay.getInstance());

        //Jewel Detector Settings
        jewelDetector.areaWeight = 0.02;
        jewelDetector.detectionMode = JewelDetector.JewelDetectionMode.MAX_AREA; // PERFECT_AREA
        //jewelDetector.perfectArea = 6500; <- Needed for PERFECT_AREA
        jewelDetector.debugContours = true;
        jewelDetector.maxDiffrence = 15;
        jewelDetector.ratioWeight = 15;
        jewelDetector.minArea = 700;

        jewelDetector.enable();
    }

    @Override
    public void handle() {

    }

    @Override
    public void stop() {

    }
}
