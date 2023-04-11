package behavioral.mediator;

public class Test {

    public static void main(String[] args) {
        ControlTower controlTower = new ControlTower();
        PlaneA planeA = new PlaneA(controlTower);
        PlaneB planeB = new PlaneB(controlTower);
        controlTower.setPlaneA(planeA);
        controlTower.setPlaneB(planeB);
        planeA.fly();
        System.out.println("-----------------");
        planeB.fly();
    }

}
