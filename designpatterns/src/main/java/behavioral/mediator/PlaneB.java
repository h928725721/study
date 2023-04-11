package behavioral.mediator;

public class PlaneB extends AbstractPlane {

    public PlaneB(AbstractMediator mediator) {
        super(mediator);
    }

    @Override
    public void fly() {
        System.out.println("我是PlaneB，要过航线，请让道");
        super.mediator.notifyPlaneA();
    }

    public void notifyMe(){
        System.out.println("我是PlaneB，已收到通知，准备让道");
    }
}
