package thu.robots.components;

import java.util.List;

public class AutomatischeSteuerung {
    private Roboter roboter;
    private Environment environment;

    public AutomatischeSteuerung(Roboter roboter) {
        this.roboter = roboter;
    }
    public SensorData lookForKontact(List<BaseSensor> sensors ) {
        for (BaseSensor sensor : sensors) {
            List<SensorData> sensorDataList = Environment.simulateSensorData(roboter, sensor);
            for (SensorData sensorData : sensorDataList) {
                if (sensorData.getDistance() < 100) {
                    System.out.println("Hinderniss erkannt");
                    roboter.setVelocity(0);
                    return sensorData;
                }


            }
            return null;
        }
        return null;
    }

    public void steuereDurchParcours() {

        while (true) {

            // Aktuelle Sensorwerte abrufen
            //environment.simulateSensorData(roboter);
            //boolean Hindernis = false;
            List<BaseSensor> sensors = roboter.getSensors();

            SensorData sensorData = lookForKontact(sensors);
            double winkel=roboter.normalizeOrientation(Math.toDegrees(sensorData.getAngle()));
                if (sensorData != null) {
                    double newOrientation;

                    if (winkel <=0) {
                        while (lookForKontact(sensors)!=null){
                            newOrientation = roboter.getOrientation() - roboter.getOrientationIncrement();
                            roboter.setOrientation(newOrientation);
                        }
                        newOrientation = roboter.getOrientation() + roboter.getOrientationIncrement();
                        //System.out.println("Drehe nach unten");
                    } else {
                        newOrientation = roboter.getOrientation() - roboter.getOrientationIncrement();
                        //System.out.println("Drehe nach oben");
                    }
                    roboter.setOrientation(newOrientation);
                } else {

                    roboter.move(0.1);

                }
            roboter.setVelocity(10);
            System.out.println(roboter.getPosX());
            System.out.println(roboter.getPosY());
            try {
                Thread.sleep((long) (0.1 * 1000));
            } catch (InterruptedException ex) {
                break;
            }
            }

        }
    }
