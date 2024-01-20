package thu.robots.components;

import java.util.List;

public class AutomatischeSteuerung {
    private Roboter roboter;
    private List<Sensor> sensoren;

    public AutomatischeSteuerung(Roboter roboter) {
        this.roboter = roboter;
        this.sensoren = roboter.getSensoren();
    }

}
