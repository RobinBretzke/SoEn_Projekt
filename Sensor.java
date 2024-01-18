package thu.robots.components;

import java.util.List;

public class Sensor extends BaseSensor{
    /**
     * Erstellt einen neuen Sensor
     *
     * @param orientationToRobot Orientierung (Blickrichtung) des Sensors relativ zur Roboter-Ausrichtung
     * @param beamWidth          Strahlbreite in rad
     * @param measurementRate    Messrate in Hertz
     */
    public Sensor(double orientationToRobot, double beamWidth, int measurementRate) {
        super(orientationToRobot, beamWidth, measurementRate);
    }
    @Override
    public void measurementFromEnvironment(List<SensorData> data) {

    }
}
