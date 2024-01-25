package thu.robots.components;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class main {
    /**
     * Instanziiert das Environment, die RobotGUI und den Sensor.
     * @param args
     */
    public static void main(String[] args) {
        java.io.File file=new java.io.File("environment.txt");
        Environment env=EnvironmentLoader.loadFromFile(file);
        RobotGUI robotGUI=new RobotGUI("Roboter GUI", env);
        robotGUI.setVisible(true);
        LinkedList<BaseSensor> Sensoren = new LinkedList<>();
        Sensor Sensor1 = new Sensor(0,2*Math.PI/3,10);
        /*Sensor Sensor2 = new Sensor(Math.PI/2,Math.PI/4,5);
        Sensor Sensor3 = new Sensor(-Math.PI/2,Math.PI/4,5);
        Sensoren.add(Sensor3);
        Sensoren.add(Sensor2);
        */
        Sensoren.add(Sensor1);
        Roboter Roboter= new Roboter(60,400,0,20,0, Color.red,Sensoren);
        robotGUI.setRobot(Roboter);
        robotGUI.setEnvironment(env);
        Roboter.activateAutonomousStearing();

    }
}
