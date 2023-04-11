package other.delegate;

public class EmployeeB implements IEmployee {
    @Override
    public void work(String taskCommand) {
        System.out.println("我是员工B，我正在工作:" + taskCommand);
    }
}
