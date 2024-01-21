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
            //environment.simulateSensorData(roboter);
            List<BaseSensor> sensors = roboter.getSensors();

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
                double newOrientation;
                if (SensorData.getAngle() >= 0 && SensorData.getAngle() < Math.PI){
                    newOrientation = roboter.getOrientation() + Math.PI / 2;
                } else {
                    newOrientation = roboter.getOrientation() - Math.PI / 2;
                }
                roboter.setOrientation(newOrientation);

            }

            roboter.move(0.1);
            roboter.setVelocity(10);

        }
    }
}