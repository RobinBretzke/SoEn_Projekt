package thu.robots.components;

public class RobotFactory extends AbstractRobotFactory{

    @Override
    public Roboter createRobot() {

        return new Roboter(0,0,0,10,0);
    }
}
