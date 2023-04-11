package behavioral.mediator;

/**
 * 具体中介者对象
 */
public class ControlTower extends AbstractMediator{
    @Override
    public void notifyPlaneA() {
        super.planeA.notifyMe();
    }

    @Override
    public void notifyPlaneB() {
        super.planeB.notifyMe();
    }

}
