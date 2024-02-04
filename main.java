package thu.robots.components;

import java.awt.*;
import java.util.LinkedList;

public class main {
    /**
     * Instanziiert das Environment, die RobotGUI und den Sensor.
     * @param args
     */
    public static void main(String[] args) {
        java.io.File file=new java.io.File("UnitTestEnvironment.txt");
        Environment env=EnvironmentLoader.loadFromFile(file);
        RobotGUI robotGUI=new RobotGUI("Roboter GUI", env);
        robotGUI.setVisible(true);
        LinkedList<BaseSensor> Sensoren = new LinkedList<>();
        Sensor Sensor1 = new Sensor(0,Math.PI/3 ,10);
        Sensoren.add(Sensor1);
        Roboter Roboter= new Roboter(300,200,+30,20,0, Color.red,Sensoren);
        robotGUI.setRobot(Roboter);
        robotGUI.setEnvironment(env);
        Roboter.activateAutonomousStearing();

    }
}
