package thu.robots.components;

import java.util.List;

public class AutomatischeSteuerung {
    private Roboter roboter;
    private List<Sensor> sensoren;

    public AutomatischeSteuerung(Roboter roboter) {
        this.roboter = roboter;
        this.sensoren = roboter.getSensoren();
    }

    public void steuereDurchParcours() {
        //while (true) {
            // Aktuelle Sensorwerte abrufen
            for (Sensor sensor : sensoren) {
                SensorData sensorData = (SensorData) Environment.simulateSensorData(roboter,sensor);
            }
                // Hier könnten verschiedene Logiken für die Steuerung basierend auf den Sensorwerten implementiert werden
                roboter.setVelocity(5);
                roboter.move(0.1);
        }
    //}
}