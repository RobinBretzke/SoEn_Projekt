package thu.robots.components;

import javax.swing.*;
import java.awt.*;

public class main {
    public static void main(String[] args) {
        java.io.File file=new java.io.File("environment.txt");
        Environment env=EnvironmentLoader.loadFromFile(file);
        RobotGUI robotGUI=new RobotGUI("Roboter GUI", env);

        Roboter Roboter=new Roboter(100,100,0,50,0, Color.red);
        robotGUI.setRobot(Roboter);

        robotGUI.setEnvironment(env);
        robotGUI.setVisible(true);


    }
}
