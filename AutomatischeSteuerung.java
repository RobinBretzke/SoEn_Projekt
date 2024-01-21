package thu.robots.components;

import java.util.List;

public class AutomatischeSteuerung {
    private Roboter roboter;
    private Environment environment;

    public AutomatischeSteuerung(Roboter roboter) {
        this.roboter = roboter;
    }

    public void steuereDurchParcours() {
        while (true) {
            // Aktuelle Sensorwerte abrufen
            environment.simulateSensorData(roboter);
            List<Sensor> sensors = roboter.getSensoren();

            boolean Hindernis = false;
            for (BaseSensor sensor : sensors) {
                List<SensorData> sensorDataList = Environment.simulateSensorData(roboter,sensor);
                for (SensorData sensorData : sensorDataList) {
                    if (sensorData.getDistance() < sensor.getMaxRange()) {
                        Hindernis = true;
                        break;
                    }
                }
                if (Hindernis) {
                    break;
                }
            }
            if (Hindernis) {
                double newOrientation = roboter.getOrientation() + Math.PI/2;
                roboter.setOrientation(newOrientation);
            }
            roboter.move(0.1);

        }
    }
}