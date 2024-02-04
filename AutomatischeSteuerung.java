package thu.robots.components;

import java.util.List;

public class AutomatischeSteuerung {
    private Roboter roboter;
    private Environment environment;
    private boolean hindernis;

    public AutomatischeSteuerung(Roboter roboter) {
        this.roboter = roboter;
    }

    /**
     * Iteriert durch die Sensoren und Simuliert die Sensordaten. Wird ein Hindernis erkannt, wird die Variable "hindernis"
     * auf True gesetzt und gibt die sensorData zurück. Zudem wird die Geschwindigkeit des Roboters auf 0 gesetzt.
     * @param sensors

     * @return sensorData
     */
    public SensorData findeKontakt(List<BaseSensor> sensors) {
        for (BaseSensor sensor : sensors) {
            List<SensorData> sensorDataList = Environment.simulateSensorData(roboter, sensor);
            for (SensorData sensorData : sensorDataList) {
                if (sensorData.getDistance() < sensor.getMaxRange()) {
                    roboter.setVelocity(0);
                    hindernis = true;
                    return sensorData;
                }

            }
            hindernis = false;
            return null;
        }
        hindernis = false;
        return null;
    }

    /**
     * Der Roboter fährt so lange gerade aus, bis die Mathode "findeKontakt" ein Hindernis erkennt. Ist dies der Fall, dreht sich der
     * Roboter so lange von dem Hindernis weg, bis der Sensor es aus den Augen verloren hat. Daraufhin nimmt der Roboter wieder
     * seine ursprüngliche Geschwindigkeit auf.
     */
    public void steuereDurchParcours() {
        List<BaseSensor> sensors = roboter.getSensors();
        while (true) {
            SensorData sensorData = findeKontakt(sensors);
            double winkel = roboter.normalizeOrientation(sensorData.getAngle());
            if (sensorData != null) {
                double newOrientation;

                if (winkel <= 0) {

                    newOrientation = roboter.getOrientation() + roboter.getOrientationIncrement();
                    newOrientation = roboter.normalizeOrientation(newOrientation);
                    roboter.setOrientation(newOrientation);
                    try {
                        Thread.sleep((long) (0.1 * 1000));
                    } catch (InterruptedException ex) {
                        break;
                    }


                } else {
                    //newOrientation = roboter.getOrientation() - roboter.getOrientationIncrement();

                    newOrientation = roboter.getOrientation() - roboter.getOrientationIncrement();
                    newOrientation = roboter.normalizeOrientation(newOrientation);
                    roboter.setOrientation(newOrientation);
                    try {
                        Thread.sleep((long) (0.1 * 1000));
                    } catch (InterruptedException ex) {
                        break;
                    }

                }
            } else {

                roboter.move(0.1);

            }
            if (hindernis) {
                roboter.setVelocity(0);
            } else {
                roboter.setVelocity(30);
            }
            try {
                Thread.sleep((long) (0.1 * 1000));
            } catch (InterruptedException ex) {
                break;
            }
        }
    }

    /**
     * Gibt den Boolean "hindernis" zurück.
     * @return
     */

    public boolean isHindernis() {
        return hindernis;
    }
}
