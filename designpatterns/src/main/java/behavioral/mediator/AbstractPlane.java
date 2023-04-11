package behavioral.mediator;

public abstract class AbstractPlane {

    protected AbstractMediator mediator;

    public AbstractPlane(AbstractMediator mediator) {
        this.mediator = mediator;
    }

    public abstract void fly();
}
