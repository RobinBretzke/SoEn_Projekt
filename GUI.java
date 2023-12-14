package thu.robots.components;

import javax.swing.*;
import java.awt.*;

public class GUI {
    private JPanel mainPanel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("GUI");
        frame.setContentPane(new GUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        RobotFactory myFactory = new RobotFactory();
        Roboter roboter = myFactory.createRobot();




    }
}
