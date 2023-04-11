package behavioral.order;

import com.google.common.collect.Lists;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        LinuxSystem linuxSystem = new LinuxSystem();
        List<ICommand> commands = Lists.newArrayList();
        commands.add(new CdCommand(linuxSystem));
        commands.add(new LsCommand(linuxSystem));
        commands.add(new RestartCommand(linuxSystem));

        XshellInvoker xshellInvoker = new XshellInvoker(commands);
        xshellInvoker.executeAll();
    }
}
