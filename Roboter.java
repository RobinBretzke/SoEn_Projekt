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

    private  LinkedList<Sensor> Sensoren;

    public Roboter(int posX, int posY, int orientation, int radius, int velocity, java.awt.Color color, LinkedList<Sensor> Sensoren) {

        this.posX = posX;
        this.posY = posY;
        this.orientation = orientation;
        this.radius = radius;
        this.velocity = velocity;
        this.color=color;
        this.Sensoren=Sensoren;
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
    public void activateAutonomousStearing() {}


    //Getter
    @Override
    public List<BaseSensor> getSensors() {
        return null;
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
        return 400-posY;
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
    public LinkedList<Sensor> getSensoren() {
        return Sensoren;
    }
    @Override
    public int getRadius() {
        return radius;
    }

    @Override
    public void move(double deltaT) {
        // Berechnen der Wegstrecke
        int deltaX = (int)(deltaT*velocity*Math.cos(orientation/180.*Math.PI));
        int deltaY = (int)(deltaT*velocity*Math.sin(orientation/180.*Math.PI));


        // Zusatzaufgabe: Verhindern des Verschwindens

        // Prüfen, ob neue Position außerhalb der Fläche wäre.
        // Falls ja, Geschwindigkeit auf 0 setzen und alte Position weiter verwenden
        // Falls nein, neue Position berechnen und setzen
        int radius = this.radius;
        if(posX+deltaX<=radius||posY+deltaY<=radius||posX+deltaX>=600-radius||posY+deltaY>=400-radius){
            velocity = 0;
        }
        else{
            this.posX = posX + deltaX;
            this.posY = posY + deltaY;


        }
    }

}

