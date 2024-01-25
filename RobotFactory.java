package thu.robots.components;

import java.awt.*;
import java.util.LinkedList;

public class RobotFactory extends AbstractRobotFactory{

    @Override
    public Roboter createRobot() {
        LinkedList<BaseSensor> Sensoren = new LinkedList<>();
        Sensor Sensor1 = new Sensor(0,2*Math.PI/3,10);
        Sensoren.add(Sensor1);
        Roboter Roboter=new Roboter(400,10,20,20,0, Color.red, Sensoren);
        Roboter.activateAutonomousStearing();
        return Roboter;
    }
}
