package thu.robots.components;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class main {
    public static void main(String[] args) {
        java.io.File file=new java.io.File("TestEnvironment.txt");Environment env=EnvironmentLoader.loadFromFile(file);
        RobotGUI robotGUI=new RobotGUI("Roboter GUI", env);
        robotGUI.setVisible(true);
        LinkedList<Sensor> Sensoren = new LinkedList<>();
        Sensor Sensor1 = new Sensor(0,Math.PI/4,10);
        Sensoren.add(Sensor1);
        Roboter Roboter= new Roboter(100,100,0,50,0, Color.red,Sensoren);
        robotGUI.setRobot(Roboter);

        robotGUI.setEnvironment(env);
    }
}
