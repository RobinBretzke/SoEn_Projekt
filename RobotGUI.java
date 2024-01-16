package thu.robots.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RobotGUI extends JFrame {
    private JPanel pRootPanel;
    private JPanel pDrawPanel;
    private JScrollPane spInfo;
    private JScrollPane spStatus;
    private Roboter roboter;
    private Thread updateThread;
    private final double orientationIncrement=5;
    private final int velocityIncrement = 10;


    public RobotGUI(String title) {
        setTitle(title);
        setContentPane(pRootPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        pack();
    }

    public void setRobot(Roboter roboter) {
        this.roboter =roboter;
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

    //Frage, Wann wird das ausgeführt?
    private void createUIComponents() {
        pDrawPanel = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (roboter == null) {
                    return;
                }

                Color color = roboter.getColor();
                int posX = roboter.getPosX();
                int posY = roboter.getPosY();
                posY = pDrawPanel.getHeight() - posY;
                double orientation = roboter.getOrientation();
                int velocity = roboter.getVelocity();
                int diameter = roboter.getRadius();


                String statusStr = "X: " + posX + "\n";
                statusStr += "Y: " + posY + "\n";
                statusStr += "Orientierung: " + orientation + "°\n";
                statusStr += "Geschwindigkeit: " + velocity + " Pixel/s\n";




                g.setColor(color);
                g.fillOval(posX - diameter / 2, posY - diameter / 2, diameter, diameter);
                g.setColor(Color.BLACK);
                g.fillArc(posX - diameter / 2, posY - diameter / 2, diameter, diameter, (int)orientation - 45, 90);


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

    private void formKeyPressed(KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if (roboter == null) {
            return;
        }
        int key = evt.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            double orientation = roboter.getOrientation() + orientationIncrement;
            roboter.setOrientation(normalizeOrientation(orientation));
            System.out.println("Left");
        } else if (key == KeyEvent.VK_RIGHT) {
            double orientation = roboter.getOrientation() - orientationIncrement;
            roboter.setOrientation(normalizeOrientation(orientation));
            System.out.println(("Right"));
        } else if (key == KeyEvent.VK_UP) {
            int velocity = roboter.getVelocity();
            roboter.setVelocity(velocity + velocityIncrement);
            System.out.println(("Up"));
        } else if (key == KeyEvent.VK_DOWN) {
            int velocity = roboter.getVelocity();
            roboter.setVelocity(velocity - velocityIncrement);
            System.out.println("Down");
        }
    }

    private double normalizeOrientation(double orientation) {
        if (orientation <= -180) {
            orientation = 360 + orientation;
        } else if (orientation > 180) {
            orientation = (orientation - 360);
        }
        return orientation;
    }

}