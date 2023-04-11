package behavioral.order;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 这里就是命令模式的体现，对一些列操作进行封装，这样如果接收方发生了修改，比如新加了一个命令，只需要在这里进行处理就可以
 * 请求方完全不需要做任何修改
 */
public class XshellInvoker {
    private List<ICommand> commands = Lists.newArrayList();

    public XshellInvoker(List<ICommand> commands) {
        this.commands = commands;
    }

    /**
     * 执行指定命令
     */
    public void execute(ICommand command) {
        command.execute();
    }

    /**
     * 执行命令宏
     */
    public void executeCdAndLs() {
        commands.forEach(i -> {
            if (i instanceof LsCommand || i instanceof CdCommand) {
                i.execute();
            }
        });
    }

    public void executeAll() {
        commands.forEach(ICommand::execute);
    }


}
