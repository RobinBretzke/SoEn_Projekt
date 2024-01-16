package thu.robots.components;

import javax.swing.*;
import java.awt.*;

public class RobotGUI extends JFrame {
    private JPanel pRootPanel;
    private JPanel pDrawPanel;
    private JScrollPane spInfo;
    private JScrollPane spStatus;
    private Roboter roboter;
    private Thread updateThread;

    public RobotGUI(String title) {
        setTitle(title);
        setContentPane(pRootPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }

    public void setRobot(Roboter robot) {
        this.roboter =robot;
        startCalculating();

    }
    private void startCalculating() {
        double deltaT = 0.2;
        updateThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    roboter.move(deltaT);
                    repaint();
                    try {
                        Thread.sleep((long) (deltaT * 1000));
                    } catch (InterruptedException ex) {
                        break;
                    }
                }
            }
        });
        updateThread.start();
    }
    private void createUIComponents() {
        pDrawPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (roboter == null) {
                    return;
                }

                Color color = roboter.getColor();
                int posX = roboter.getPosX();
                int posY = roboter.getPosY();
                posY = pDrawPanel.getHeight() - posY;
                int orientation = roboter.getOrientation();
                int velocity = roboter.getVelocity();
                int diameter = roboter.getDiameter();


                String statusStr = "X: " + posX + "\n";
                statusStr += "Y: " + posY + "\n";
                statusStr += "Orientierung: " + orientation + "°\n";
                statusStr += "Geschwindigkeit: " + velocity + " Pixel/s\n";

                taRobotStatus.setText(statusStr);


                g.setColor(color);
                g.fillOval(posX - diameter / 2, posY - diameter / 2, diameter, diameter);
                g.setColor(Color.BLACK);
                g.fillArc(posX - diameter / 2, posY - diameter / 2, diameter, diameter, orientation - 45, 90);


                int maxX = pDrawPanel.getWidth() + diameter / 2;
                int minX = -diameter / 2;
                int maxY = pDrawPanel.getHeight() + diameter / 2;
                int minY = -diameter / 2;

                if (posX > maxX || posX < minX || posY > maxY || posY < minY) {
                    updateThread.interrupt();
                    JOptionPane.showMessageDialog(this, "Der Roboter ist verschwunden!", "Roboter ist verschwunden", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
    }

}