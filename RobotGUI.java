package thu.robots.components;

import javax.swing.*;

public class RobotGUI extends JFrame {
    private JPanel pRootPanel;
    private JPanel pDrawPanel;
    private JScrollPane spInfo;
    private JScrollPane spStatus;
    private RobotFactory robot;

    public RobotGUI(String title) {
        setTitle(title);
        setContentPane(pRootPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }

    public void setRobot(RobotFactory robot) {
        this.robot=robot;

    }
}
