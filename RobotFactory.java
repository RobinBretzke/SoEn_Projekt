package thu.robots.components;

public class RobotFactory extends AbstractRobotFactory{

    @Override
    public Roboter createRobot() {
        Roboter Roboter=new Roboter(10,10,20,20,0);
        return Roboter;
    }
}
