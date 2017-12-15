package org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.RobotSubSystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.General.Robot;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.General.SubSystem;
import org.firstinspires.ftc.teamcode.v2.UltronCode.UltronRobot.Ultron;

/**
 * Created by Julian on 12/15/2017.
 */

public class VuforiaSystem extends SubSystem {

    protected VuforiaLocalizer vuforia;
    protected int cameraMonitorViewId;
    protected VuforiaLocalizer.Parameters parameters;
    protected VuforiaTrackables relicTrackables;
    protected VuforiaTrackable relicTemplate;

    public enum CryptoboxKey{
        RIGHT,CENTER,LEFT
    }

    public VuforiaSystem(Robot robot) {
        super(robot);
    }

    @Override
    public void init() {
        cameraMonitorViewId = hardwareMap().appContext.getResources().getIdentifier(Ultron.cameraViewID, "id", hardwareMap().appContext.getPackageName());
        parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = Ultron.vuforiaLicenseKey;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT;
        vuforia = ClassFactory.createVuforiaLocalizer(parameters);
        relicTrackables = vuforia.loadTrackablesFromAsset(Ultron.vuMarkAsset);
        relicTemplate = relicTrackables.get(0);
        relicTemplate.setName(Ultron.relicTemplate);
    }

    @Override
    public void handle() {

    }

    @Override
    public void stop() {

    }

    public void activateVuforia() {
        relicTrackables.activate();
    }

    public RelicRecoveryVuMark checkForVuMark() {return RelicRecoveryVuMark.from(relicTemplate);}
}
