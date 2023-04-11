package other.delegate;


public class EmployeeA implements IEmployee {
    @Override
    public void work(String taskCommand) {
        System.out.println("我是员工A，我正在工作:" + taskCommand);
    }
}
