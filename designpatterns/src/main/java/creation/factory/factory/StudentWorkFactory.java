package creation.factory.factory;

public class StudentWorkFactory implements IWorkFactory{
    @Override
    public Work getWork() {
        return new StudentWork();
    }
}
