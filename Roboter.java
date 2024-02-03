package thu.robots.components;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.LinkedList;
import java.util.List;

public class Roboter implements IRobot{
    private int posX;
    private int posY;
    private double orientation;
    private int radius;
    private int velocity;
    private java.awt.Color color;
    private static List<BaseSensor> sensoren;
    private final double orientationIncrement=30;
    private final double orientationIncrementManuell=5;
    private final int velocityIncrement = 10;

    /**
     *
     * @param posX
     * @param posY
     * @param orientation
     * @param radius
     * @param velocity
     * @param color
     * @param sensoren
     */
    public Roboter(int posX, int posY, int orientation, int radius, int velocity, java.awt.Color color, LinkedList<BaseSensor> sensoren) {

        this.posX = posX;
        this.posY = posY;
        this.orientation = orientation;
        this.radius = radius;
        this.velocity = velocity;
        this.color=color;
        this.sensoren=sensoren;

    }

    //Setter

    /**
     * Setzt den Roboter auf eine Anfangsposition
     * @param posX
     * @param posY
     * @param orientation Orientierung in Radiant
     */
    @Override
    public void setInitialPose(int posX, int posY, double orientation) {
        this.posX=posX;
        this.posY=posY;
        this.orientation=orientation;
    }

    /**
     * Setzt die Orientierung des Roboters
     * @param orientation
     */
    public void setOrientation(double orientation){
        this.orientation = orientation;
    }

    /**
     * Setzt die Geschwindigkeit des Roboters
     * @param velocity
     */
    public void setVelocity(int velocity){
        this.velocity=velocity;
    }

    /**
     * Instanziiert die Automatische Steuerung und startet die Methode "steuereDurchParcours" in der Klasse "AutomatischeSteuerung"
     */
    @Override
    public void activateAutonomousStearing() {
        AutomatischeSteuerung automatischeSteuerung = new AutomatischeSteuerung(this);
        automatischeSteuerung.steuereDurchParcours();
    }

    //Getter

    /**
     * Gibt die Liste der Sensoren zurück
     * @return sensoren
     */
    @Override
    public List<BaseSensor> getSensors() {
        return sensoren;
    }

    /**
     * Methode nicht Implementiert. return null
     * @return null
     */
    @Override
    public Image getImage() {
        return null;
    }

    /**
     * Methode nicht Implementiert. return null
     * @return
     */
    @Override
    public String getName() {
        return null;
    }

    /**
     * Gibt die Position in X des Roboters zurück
     * @return posX
     */
    @Override
    public int getPosX() {
        return posX;
    }

    /**
     * Gibt die Position in Y des Roboters zurück
     * @return posY
     */
    @Override
    public int getPosY() {
        return posY;
    }

    /**
     * Gibt die Orientierung in Grad des Roboters zurück
     * @return orientation
     */
    @Override
    public double getOrientation() {
        return orientation;
    }

    /**
     * Gibt die Geschwindigkeit des Roboters zurück
     * @return velocity
     */
    @Override
    public int getVelocity() {
        return velocity;
    }

    /**
     * Gibt die Farbe des Roboters zurück
     * @return color
     */
    public java.awt.Color getColor(){return color;}

    /**
     * Gibt den Radius des Roboters zurück
     * @return radius
     */
    @Override
    public int getRadius() {
        return radius;
    }

    /**
     * Berechnet die neue Position des Roboters nach einer gewissen Zeit (deltaT)
     * @param deltaT Zeitdifferenz, für die die Bewegung berechnet werden soll
     */
    @Override
    public void move(double deltaT) {
        // Berechnen der Wegstrecke
        int deltaX = (int)(deltaT*velocity*Math.cos(Math.toRadians(orientation)));
        int deltaY = (int)(deltaT*velocity*Math.sin(Math.toRadians(orientation)));

        this.posX = posX + deltaX;
        this.posY = posY + deltaY;

    }

    /**
     * Gibt zurück, um wieviel Grad sich die Orietierung des Roboters ändern soll
     * @return
     */
    public double getOrientationIncrement() {
        return orientationIncrement;
    }

    /**
     * Gibt zurück, um wieviel Pixel sich die GEschwindigkeit des Roboters ändern soll
     * @return orientationIncrement
     */
    public int getVelocityIncrement() {
        return velocityIncrement;
    }

    /**
     * Gibt zurück um wieviel Grad sich der Roboter im manuellen Betrieb pro Tastendruck drehen soll
     * @return orientationIncrementManuell
     */
    public double getOrientationIncrementManuell() {
        return orientationIncrementManuell;
    }

    /**
     * Berechnet die Orientierung in Grad auf ein Wert zwischen 0° und +/-180°
     * @param orientation
     * @return orientation
     */
    public double normalizeOrientation(double orientation) {
        if (orientation <= -180) {
            orientation = 360 + orientation;
        } else if (orientation > 180) {
            orientation = (orientation - 360);
        }
        return orientation;
    }
}

