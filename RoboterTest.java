package thu.robots.components;
import org.junit.jupiter.api.Assertions;
import java.awt.*;
import java.util.LinkedList;

class RoboterTest {
    private Roboter roboter;

    public void setup(){
        LinkedList<BaseSensor> Sensoren = new LinkedList<>();
        Sensor Sensor1 = new Sensor(0,Math.PI/8,1);
        Sensoren.add(Sensor1);
        roboter=new Roboter(100,100,0,20,0, Color.red,Sensoren);
    }

    @org.junit.jupiter.api.Test
    void setInitialPose() {
        roboter.setInitialPose(300,300,50);
        Assertions.assertEquals(300,roboter.getPosX(),0);
        Assertions.assertEquals(300,roboter.getPosY(),0);
        Assertions.assertEquals(50,roboter.getOrientation(),0);

    }

    @org.junit.jupiter.api.Test
    void move() {
        LinkedList<BaseSensor> Sensoren = new LinkedList<>();
        Sensor Sensor1 = new Sensor(0,Math.PI/8,1);
        Sensoren.add(Sensor1);
        Roboter roboter2=new Roboter(100,100,60,20,50, Color.red,Sensoren);
        roboter2.move(0.1);
        Assertions.assertEquals(102.5,roboter2.getPosX(),0.5);
        Assertions.assertEquals(104.3,roboter2.getPosY(),0.5);

    }
    @org.junit.jupiter.api.Test
    void normalizeOrientation360() {
        roboter.setOrientation(360);
        roboter.setOrientation(roboter.normalizeOrientation(roboter.getOrientation()));
        Assertions.assertEquals(0,roboter.getOrientation());
    }
    @org.junit.jupiter.api.Test
    void normalizeOrientation180() {
        roboter.setOrientation(180);
        roboter.setOrientation(roboter.normalizeOrientation(roboter.getOrientation()));
        Assertions.assertEquals(180,roboter.getOrientation());
    }
    @org.junit.jupiter.api.Test
    void normalizeOrientationMinus180() {
        roboter.setOrientation(-180);
        roboter.setOrientation(roboter.normalizeOrientation(roboter.getOrientation()));
        Assertions.assertEquals(180,roboter.getOrientation());
    }
    @org.junit.jupiter.api.Test
    void testCollision(){
        java.io.File file=new java.io.File("TestEnvironment.txt");
        Environment env=EnvironmentLoader.loadFromFile(file);
        Validator val=new Validator(env);
        roboter.setInitialPose(457,100,0);
        EnvironmentObject obj =val.checkCollosion(roboter);
        Assertions.assertEquals(500,obj.getX());
        Assertions.assertEquals(200,obj.getY());
    }

    @org.junit.jupiter.api.Test
    void checkTargetZone(){
        java.io.File file=new java.io.File("TestEnvironment.txt");
        Environment env=EnvironmentLoader.loadFromFile(file);
        Validator val=new Validator(env);
        roboter.setInitialPose(800,300,0);
        Assertions.assertEquals(true,val.checkTargetZone(roboter));

    }



}