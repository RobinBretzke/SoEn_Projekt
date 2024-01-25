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
        java.io.File file=new java.io.File("TestEnvironment.txt");
        Environment env=EnvironmentLoader.loadFromFile(file);
        RobotGUI robotGUI=new RobotGUI("Roboter GUI", env);
        robotGUI.setVisible(true);
        LinkedList<BaseSensor> Sensoren = new LinkedList<>();
        Sensor Sensor1 = new Sensor(0,Math.PI/8,1);
        Sensoren.add(Sensor1);
        Roboter Roboter= new Roboter(367,200,0,20,0, Color.red,Sensoren);
        robotGUI.setRobot(Roboter);
        robotGUI.setEnvironment(env);
        //Roboter.activateAutonomousStearing();

    }
}
