package thu.robots.components;

import java.util.List;

public class AutomatischeSteuerung {
    private Roboter roboter;
    private Environment environment;
    private boolean hindernis;

    public AutomatischeSteuerung(Roboter roboter) {
        this.roboter = roboter;
    }

    public SensorData findeKontakt(List<BaseSensor> sensors) {
        for (BaseSensor sensor : sensors) {
            List<SensorData> sensorDataList = Environment.simulateSensorData(roboter, sensor);
            for (SensorData sensorData : sensorDataList) {
                if (sensorData.getDistance() < sensor.getMaxRange()) {
                    System.out.println("Hinderniss erkannt");
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

    public void steuereDurchParcours() {
        List<BaseSensor> sensors = roboter.getSensors();
        while (true) {

            // Aktuelle Sensorwerte abrufen
            //environment.simulateSensorData(roboter);


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

                    //System.out.println("Drehe nach oben");
                }
            } else {

                roboter.move(0.1);

            }

            //System.out.println(roboter.getPosX());
            //System.out.println(roboter.getPosY());
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
}
