package other.delegate;

public class Boss {
    public void startWork(String taskCommand,Manager manager) {
        manager.work(taskCommand);
    }
}
