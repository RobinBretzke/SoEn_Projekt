package thu.robots.components;

import java.awt.*;

public class RobotFactory extends AbstractRobotFactory{

    @Override
    public Roboter createRobot() {
        Roboter Roboter=new Roboter(10,10,20,20,0, Color.red);
        Sensor Sensor1 = new Sensor(0,Math.PI/4,10);
        return Roboter;
    }
}
