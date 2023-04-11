package other.delegate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Manager implements IEmployee{
    private Map<String,IEmployee> workTargetMap = new HashMap<>();

    public Manager() {
        workTargetMap.put("部署项目",new EmployeeA());
        workTargetMap.put("修复BUG",new EmployeeB());
    }

    @Override
    public void work(String taskCommand) {
        IEmployee iEmployee = workTargetMap.get(taskCommand);
        Optional.ofNullable(iEmployee)
                .ifPresent(e -> e.work(taskCommand));
    }
}
