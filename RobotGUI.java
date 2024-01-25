package thu.robots.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JTextArea taInfo;
    private Roboter roboter;
    private Thread updateThread;
    private Environment env;
    private double deltaT = 0.1;
    private List<EnvironmentObject> environmentObjects;
    private EnvironmentObject obj;

    /**
     * Setzt das Environment auf die GUI und den Titel. Zudem wird der default close operater auf "Exit on close" gesetzt
     * @param title
     * @param env
     */
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

    /**
     * Setzt den Roboter und startet die Methode "startCalculating"
     * @param roboter
     */
    public void setRobot(Roboter roboter) {
        this.roboter =roboter;
        startCalculating();
    }

    /**
     * Setzt das Environment
     * @param env
     */
    public void setEnvironment(Environment env){
        this.env=env;
    }

    /**
     * Ruft die Methode "move" in der Roboter Klasse auf. Zudem wird ein neuer Validator erstellt, welcher überprüft ob der Roboter
     * mit einem Hindernis zusammengestoßen ist.Falls ja, wird die geschwindigkeit des Roboters auf Null gesetzt und auf dem Panel "Roboter Info"
     * Medung "Kollision" ausgegeben. Wird die linke Seite der Oberfläche erreicht, wird die Meldung
     * "Ziel Erreicht" ausgegeben.
     */
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
                        taInfo.setText("Kollision");
                        break;

                    }
                    if (val.checkTargetZone(roboter)) {
                        taInfo.setText("Ziel erreicht");
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

    /**
     * Bestimmt die Events bei bestimmten Tastendrücke.
     * Pfeiltaste Links: Roboter dreht um 5° nach Links
     * Pfeiltaste Rechts: Roboter dreht um 5° nach Rechts
     * Pfeiltaste Hoch: Roboter beschleunigt um 10 Pixel
     * Pfeiltaste Runter: Roboter verzögert um 10 Pixel
     * @param evt
     */
    private void formKeyPressed(KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if (roboter == null) {
            return;
        }
        int key = evt.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            double orientation = roboter.getOrientation() - roboter.getOrientationIncrementManuell();
            roboter.setOrientation(roboter.normalizeOrientation(orientation));
        } else if (key == KeyEvent.VK_RIGHT) {
            double orientation = roboter.getOrientation() + roboter.getOrientationIncrementManuell();
            roboter.setOrientation(roboter.normalizeOrientation(orientation));
        } else if (key == KeyEvent.VK_UP) {
            int velocity = roboter.getVelocity();
            roboter.setVelocity(velocity + roboter.getVelocityIncrement());
        } else if (key == KeyEvent.VK_DOWN) {
            int velocity = roboter.getVelocity();
            roboter.setVelocity(velocity - roboter.getVelocityIncrement());
        }
    }

    /**
     * Zeichnet die Grafischen Inhalte auf der GUI mit hilfe der Klasse "EnvironmentLoader".
     * Zudem wird String erstellt, welcher auf dem Fenster "Roboter Status" ausgegeben wird
     */
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

            }
        };
    }

    }


