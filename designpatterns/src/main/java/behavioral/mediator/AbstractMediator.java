package behavioral.mediator;

/**
 * 抽象中介者
 */
public abstract class AbstractMediator {
    protected PlaneA planeA;
    protected PlaneB planeB;

    public void setPlaneA(PlaneA planeA) {
        this.planeA = planeA;
    }

    public void setPlaneB(PlaneB planeB) {
        this.planeB = planeB;
    }

    public abstract void notifyPlaneA();

    public abstract void notifyPlaneB();
}
