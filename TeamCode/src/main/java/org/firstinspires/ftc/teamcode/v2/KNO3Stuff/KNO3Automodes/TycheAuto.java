package org.firstinspires.ftc.teamcode.v2.KNO3Stuff.KNO3Automodes;



import org.firstinspires.ftc.teamcode.v2.KNO3Stuff.KNO3OpMode.AutonomousProgram;
import org.firstinspires.ftc.teamcode.v2.KNO3Stuff.KNO3Robot.BeaconButtonSystem;
import org.firstinspires.ftc.teamcode.v2.KNO3Stuff.KNO3Robot.CollectorSystem;
import org.firstinspires.ftc.teamcode.v2.KNO3Stuff.KNO3Robot.DriveSystem;
import org.firstinspires.ftc.teamcode.v2.KNO3Stuff.KNO3Robot.LiftSystem;
import org.firstinspires.ftc.teamcode.v2.KNO3Stuff.KNO3Robot.Robot;
import org.firstinspires.ftc.teamcode.v2.KNO3Stuff.KNO3Robot.SensorSystem;
import org.firstinspires.ftc.teamcode.v2.KNO3Stuff.KNO3Robot.ShooterSystem;
import org.firstinspires.ftc.teamcode.v2.KNO3Stuff.KNO3Robot.Tyche;
import org.firstinspires.ftc.teamcode.v2.KNO3Stuff.KNO3Util.SimpleColor;

/**
 * @author Jaxon A Brown
 */
public abstract class TycheAuto extends AutonomousProgram {
    public SensorSystem sensors;
    public DriveSystem drive;
    public CollectorSystem collector;
    public ShooterSystem shooter;
    public BeaconButtonSystem beaconButtons;
    public LiftSystem lift;

    private SimpleColor alliance;

    public TycheAuto(SimpleColor alliance) {
        this.alliance = alliance;
    }

    @Override
    protected Robot buildRobot() {
        Tyche tyche = new Tyche(this, alliance);
        sensors = tyche.getSubSystem(SensorSystem.class);
        drive = tyche.getSubSystem(DriveSystem.class);
        collector = tyche.getSubSystem(CollectorSystem.class);
        shooter = tyche.getSubSystem(ShooterSystem.class);
        beaconButtons = tyche.getSubSystem(BeaconButtonSystem.class);
        lift = tyche.getSubSystem(LiftSystem.class);
        return tyche;
    }
}
