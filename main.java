package thu.robots.components;

import java.awt.*;

public class main {
    public static void main(String[] args) {
        RobotGUI robotGUI=new RobotGUI("Roboter GUI");
        robotGUI.setVisible(true);
        Roboter Roboter=new Roboter(10,10,0,10,0, Color.red);
        robotGUI.setRobot(Roboter);




    }
}
