package thu.robots.components;

import javax.swing.*;
import java.awt.*;

public class main {
    public static void main(String[] args) {
        RobotGUI robotGUI=new RobotGUI("Roboter GUI");
        robotGUI.setVisible(true);
        
        Roboter Roboter=new Roboter(100,100,0,50,0, Color.red,new Sensor(0,Math.PI/4,10));
        robotGUI.setRobot(Roboter);
        java.io.File file=new java.io.File("environment.txt");
        Environment env=EnvironmentLoader.loadFromFile(file);
        robotGUI.setEnvironment(env);


    }
}
