package thu.robots.components;

public class SensorData {
    private static double angle;
    private int distance;
    private BaseSensor relatedSensor;

    /**
     * Erstellt ein Sensordatum in Polarkoordinaten
     * @param angle Winkel, aus dem die Messung stammt (relativ zum Sensor)
     * @param distance Entfernung
     * @param relatedSensor Sensor, der die Messung durchgeführt hat
     */
    public SensorData(double angle, int distance, BaseSensor relatedSensor) {
        this.angle = angle;
        this.distance = distance;
        this.relatedSensor = relatedSensor;
    }

    /**
     * @return Winkel in Radiant relativ zum Sensor
     */
    public static double getAngle() {
        return angle;
    }

    /**
     *
     * @return Entfernung in Pixel, relativ zum Sensor
     */
    public int getDistance() {
        return distance;
    }

    /**
     *
     * @return Sensor, der die Messung durchgeführt hat
     */
    public BaseSensor getRelatedSensor() {
        return relatedSensor;
    }


    /**
     *
     * @return X-Position der Messung kartesich, relativ zum Sensor
     */
    public int getX(){
        return (int)(getDistance()*Math.cos(getAngle()));
    }

    /**
     *
     * @return Y-Position der Messung kartesich, relativ zum Sensor
     */
    public int getY(){
        return (int)(getDistance()*Math.sin(getAngle()));
    }

    public String toString(){
        return String.format("X=%d, Y=%d, Distanz=%d, Winkel=%.0f°, Sensor: %.0f°", getX(), getY(), getDistance(), getAngle()*180./Math.PI, getRelatedSensor().getOrientationToRobot()*180./Math.PI);
    }

}
