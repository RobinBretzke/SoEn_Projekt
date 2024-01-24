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
    private final double orientationIncrement=5;
    private final int velocityIncrement = 10;

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
    @Override
    public void setInitialPose(int posX, int posY, double orientation) {
        this.posX=posX;
        this.posY=400-posY;
        this.orientation=orientation;
    }

    public void setOrientation(double orientation){
        this.orientation = orientation;
    }

    public void setVelocity(int velocity){this.velocity=velocity;}

    @Override
    public void activateAutonomousStearing() {
    }

    //Getter
    @Override
    public List<BaseSensor> getSensors() {
        return sensoren;
    }

    @Override
    public Image getImage() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getPosX() {
        return posX;
    }

    @Override
    public int getPosY() {
        return posY;
    }

    @Override
    public double getOrientation() {
        return orientation;
    }

    @Override
    public int getVelocity() {
        return velocity;
    }

    public java.awt.Color getColor(){return color;}

    @Override
    public int getRadius() {
        return radius;
    }

    @Override
    public void move(double deltaT) {
        // Berechnen der Wegstrecke
        int deltaX = (int)(deltaT*velocity*Math.cos(orientation/180.*Math.PI));
        int deltaY = (int)(deltaT*velocity*Math.sin(orientation/180.*Math.PI));

        int radius = this.radius;

        this.posX = posX + deltaX;
        this.posY = posY + deltaY;



    }

    public double getOrientationIncrement() {
        return orientationIncrement;
    }

    public int getVelocityIncrement() {
        return velocityIncrement;
    }

    public double normalizeOrientation(double orientation) {
        if (orientation <= -180) {
            orientation = 360 + orientation;
        } else if (orientation > 180) {
            orientation = (orientation - 360);
        }
        return orientation;
    }
}

