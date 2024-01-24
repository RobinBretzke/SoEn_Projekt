package thu.robots.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.List;

public class RobotGUI extends JFrame {
    private JPanel pRootPanel;
    private JPanel pDrawPanel;
    private JScrollPane spInfo;
    private JScrollPane spStatus;
    private JTextArea taRobotStatus;
    private Roboter roboter;
    private Thread updateThread;
    private Environment env;
    private double deltaT = 0.1;
    private List<EnvironmentObject> environmentObjects;
    private EnvironmentObject obj;


    public RobotGUI(String title, Environment env) {
        this.env=env;
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

    public void setEnvironment(Environment env){
        this.env=env;
    }

    private void startCalculating() {

        updateThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Validator val=new Validator(env);

                while (true) {
                    roboter.move(deltaT);
                    repaint();
                    EnvironmentObject obj=val.checkCollosion(roboter);

                    if (obj!=null){
                        roboter.setVelocity(0);
                        System.out.println("Robot PosX "+ roboter.getPosX());
                        System.out.println("Robot PosY "+roboter.getPosY());
                        System.out.println("Hinderniss PosX "+obj.getX());
                        System.out.println("Hinderniss PosY "+obj.getY());
                        System.out.println("Hinderniss Width "+obj.getWidth());
                        System.out.println("Hinderniss Lengh "+obj.getLength());
                        break;

                    }
                    if (val.checkTargetZone(roboter)) {
                        System.out.println("Target reached");
                        break;
                    }
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

    private void formKeyPressed(KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if (roboter == null) {
            return;
        }
        int key = evt.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            double orientation = roboter.getOrientation() - roboter.getOrientationIncrement();
            roboter.setOrientation(roboter.normalizeOrientation(orientation));
        } else if (key == KeyEvent.VK_RIGHT) {
            double orientation = roboter.getOrientation() + roboter.getOrientationIncrement();
            roboter.setOrientation(roboter.normalizeOrientation(orientation));
        } else if (key == KeyEvent.VK_UP) {
            int velocity = roboter.getVelocity();
            roboter.setVelocity(velocity + roboter.getVelocityIncrement());
        } else if (key == KeyEvent.VK_DOWN) {
            int velocity = roboter.getVelocity();
            roboter.setVelocity(velocity - roboter.getVelocityIncrement());
        }
    }



    private void createUIComponents() {
        pDrawPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (roboter == null) {
                    return;
                }

                environmentObjects=env.getObjects();
                for (int i=0;i<env.getObjects().size();i++){
                    obj =environmentObjects.get(i);
                    Color color=obj.getColor();
                    Rectangle2D rechteck=obj.getRectangle();
                    double orientation=obj.getOrientation();
                    Graphics2D g2d=(Graphics2D) g;
                    AffineTransform transform=new AffineTransform();
                    transform.rotate(orientation,rechteck.getX()+rechteck.getWidth()/2, rechteck.getY()+rechteck.getHeight()/2);
                    g2d.setTransform(transform);
                    g.setColor(color);
                    g.fillRect((int)rechteck.getX(),(int)rechteck.getY(),(int)rechteck.getWidth(),(int)rechteck.getHeight());
                    g2d.setTransform(new AffineTransform());
                }


                Color color = roboter.getColor();
                int posX = roboter.getPosX();
                int posY = roboter.getPosY();
                //posY = pDrawPanel.getHeight() - posY;
                double orientation = roboter.getOrientation();
                int velocity = roboter.getVelocity();
                int diameter = 2*roboter.getRadius();


                String statusStr = "X: " + posX + "\n";
                statusStr += "Y: " + posY + "\n";
                statusStr += "Orientierung: " + orientation + "°\n";
                statusStr += "Geschwindigkeit: " + velocity + " Pixel/s\n";

                taRobotStatus.setText(statusStr);


                g.setColor(color);
                g.fillOval(posX - diameter / 2, posY - diameter / 2, diameter, diameter);
                g.setColor(Color.BLACK);
                g.fillArc(posX - diameter / 2, posY - diameter / 2, diameter, diameter, -(int)orientation -45, 90);


                int maxX = pDrawPanel.getWidth() + diameter / 2;
                int minX = -diameter / 2;
                int maxY = pDrawPanel.getHeight() + diameter / 2;
                int minY = -diameter / 2;
/*
                if (posX > maxX || posX < minX || posY > maxY || posY < minY) {
                    updateThread.interrupt();
                    JOptionPane.showMessageDialog(this, "Der Roboter ist verschwunden!", "Roboter ist verschwunden", JOptionPane.ERROR_MESSAGE);
                }

 */
            }
        };

    }

    public double getDeltaT() {
        return deltaT;
    }


    }


