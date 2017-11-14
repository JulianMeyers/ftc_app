package org.firstinspires.ftc.teamcode.v2.KNO3Stuff.KNO3Automodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import net.kno3.season.velocityvortex.tyche.v4.program.TycheAuto;

/**
 * Created by jaxon on 2/17/2017.
 */
@Autonomous(name = "Collect")
public class CollectAuto extends TycheAuto {

    public CollectAuto() {
        super(null);
    }

    @Override
    public void main() {
        collector.collect(1);
        while (opModeIsActive()) {
            telemetry.update();
        }
    }
}
