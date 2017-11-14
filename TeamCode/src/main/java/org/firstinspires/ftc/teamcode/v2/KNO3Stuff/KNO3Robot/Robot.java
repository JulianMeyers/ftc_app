package org.firstinspires.ftc.teamcode.v2.KNO3Stuff.KNO3Robot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.v2.KNO3Stuff.KNO3Util.Threading;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jaxon A Brown
 */
public class Robot {
    private final Map<String, SubSystem> subSystems;
    private final Map<Class, SubSystem> classSubSystemMap;
    public final OpMode opMode;
    public final TycheSettings settings;

    /**
     * Gamepad for the first driver. The object reference changes upon every loop, so don't cache it.
     */
    public volatile Gamepad gamepad1;
    /**
     * Gamepad for the second driver. The object reference changes upon every loop, so don't cache it.
     */
    public volatile Gamepad gamepad2;
    /**
     * Telemetry information. Telemetry should be used in all robots to aid in debugging.
     */
    public final Telemetry telemetry;
    /**
     * Hardware map is how to access hardware. Please cache hardware in the init method. This field
     *  probably won't be used anywhere else.
     */
    public final HardwareMap hardwareMap;

    /**
     * Construct your very own Robot!
     * @param opMode the OpMode that this instance of a robot will be used in.
     */
    public Robot(OpMode opMode) {
        this.opMode = opMode;
        telemetry = opMode.telemetry;
        hardwareMap = opMode.hardwareMap;

        settings = new TycheSettings();

        subSystems = new HashMap<>();
        classSubSystemMap = new HashMap<>();
    }

    /**
     * Adds a subsystem to the robot.
     * @param name The name of the subsystem. Use an intelligible name.
     * @param subSystem The subsystem to add to the robot. Go ahead and instantiate one of the ones that you've created.
     */
    protected void putSubSystem(String name, SubSystem subSystem) {
        subSystems.put(name, subSystem);
        classSubSystemMap.put(subSystem.getClass(), subSystem);
    }

    /**
     * Initialization of the robot. Call this to initialize the robot.
     * You probably won't have to do it, because the DriverControlledProgram and AutonomousProgram classes do.
     */
    public final void init() {
        for(SubSystem subSystem : subSystems.values()) {
            try {
                subSystem.init();
            } catch(Exception ex) {
                telemetry.addData("Error!!!", ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    /**
     * Called to handle driver controlled update loops.
     * You probably won't have to do it, because the DriverControlledProgram and AutonomousProgram classes do.
     */
    public final void driverControlledUpdate() {
        this.gamepad1 = opMode.gamepad1;
        this.gamepad2 = opMode.gamepad2;

        for(SubSystem subSystem : subSystems.values()) {
            try {
                subSystem.handle();
            } catch(Exception ex) {
                telemetry.addData("Error!!!", ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    /**
     * Stops all moving mechanisms of robot.
     */
    public final void stopAllComponents() {
        for(SubSystem subSystem : subSystems.values()) {
            try {
                subSystem.stop();
            } catch(Exception ex) {
                telemetry.addData("Error!!!", ex.getMessage());
                ex.printStackTrace();
            }
        }
        Threading.clearStopAllThreads();
    }

    /**
     * Call this to get a subsystem of the robot.
     * @param name The name of the subsystem to fetch.
     * @return The subsystem registered in the robot. null if it does not exist.
     */
    public final SubSystem getSubSystem(String name) {
        return subSystems.get(name);
    }

    public final <T extends SubSystem> T getSubSystem(Class<T> tClass) {
        return (T) classSubSystemMap.get(tClass);
    }

    /**
     * Replace a subsystem that was already registered. Or add a new one.
     * @param name Name of the subsystem.
     * @param subSystem Subsystem to override with/add
     * @return Returns the subsystem passed as a parameter. This is so that you can use it in a builder style.
     */
    public final SubSystem eOverrideSubSystem(String name, SubSystem subSystem) {
        subSystems.put(name, subSystem);
        classSubSystemMap.put(subSystem.getClass(), subSystem);
        return subSystem;
    }
}
