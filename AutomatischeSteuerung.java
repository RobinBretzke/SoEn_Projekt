package thu.robots.components;

import java.util.List;

public class AutomatischeSteuerung {
    private Roboter roboter;
    private Environment environment;

    public AutomatischeSteuerung(Roboter roboter) {
        this.roboter = roboter;
    }

    public void steuereDurchParcours() {
        List<BaseSensor> sensors = roboter.getSensors();
        boolean Hindernis = false;
        while (true) {

            // Aktuelle Sensorwerte abrufen
            //environment.simulateSensorData(roboter);



            for (BaseSensor sensor : sensors) {
                List<SensorData> sensorDataList = Environment.simulateSensorData(roboter,sensor);
                for (SensorData sensorData : sensorDataList) {
                    if (sensorData.getDistance() < sensor.getMaxRange()) {
                        Hindernis = true;
                        System.out.println("Hinderniss erkannt");
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
                    newOrientation = roboter.getOrientation() + roboter.getOrientationIncrement();
                    System.out.println("Drehe nach unten");
                } else {
                   newOrientation = roboter.getOrientation() - roboter.getOrientationIncrement();
                    System.out.println("Drehe nach oben");
                }
                roboter.setOrientation(newOrientation);
            } else {

                roboter.move(0.1);

            }
            roboter.setVelocity(10);
        }
    }
}