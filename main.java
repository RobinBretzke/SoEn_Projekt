package thu.robots.components;

public class main {
    public static void main(String[] args) {
        RobotGUI robotGUI=new RobotGUI("Roboter GUI");
        robotGUI.setVisible(true);
        RobotFactory Roboter=new RobotFactory();
        robotGUI.setRobot(Roboter);
    }
}
