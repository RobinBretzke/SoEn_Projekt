package thu.robots.components;

import java.awt.*;
import java.util.LinkedList;

public class RobotFactory extends AbstractRobotFactory{

    @Override
    public Roboter createRobot() {
        LinkedList<Sensor> Sensoren = new LinkedList<>();
        Sensor Sensor1 = new Sensor(0,Math.PI/4,10);
        Sensoren.add(Sensor1);
        Roboter Roboter=new Roboter(10,10,20,20,0, Color.red, Sensoren);
        return Roboter;
    }
}
