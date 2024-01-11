package thu.robots.components;

import javax.swing.*;

public class RobotGUI extends JFrame {
    private JPanel pRootPanel;
    private JPanel pDrawPanel;
    private JScrollPane spInfo;
    private JScrollPane spStatus;

    public RobotGUI(String title) {
        setTitle(title);
        setContentPane(pRootPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
