package thu.robots.components;

import java.awt.*;
import java.util.List;

public class Roboter implements IRobot {
    private int posX;
    private int posY;
    private double orientation;
    private int radius;
    private int velocity;

    @Override
    public List<BaseSensor> getSensors() {
        return null;
    }

    @Override
    public void activateAutonomousStearing() {

    }

    @Override
    public Image getImage() {
        return null;
    }

    @Override
    public void setInitialPose(int posX, int posY, double orientation) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getPosX() {
        return 0;
    }

    @Override
    public int getPosY() {
        return 0;
    }

    @Override
    public double getOrientation() {
        return 0;
    }

    @Override
    public int getVelocity() {
        return 0;
    }

    @Override
    public int getRadius() {
        return 0;
    }

    @Override
    public void move(double deltaTimeSec) {

    }
}

