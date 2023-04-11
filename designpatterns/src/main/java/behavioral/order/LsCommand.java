package behavioral.order;

public class LsCommand implements ICommand{
    private final LinuxSystem linuxSystem;

    public LsCommand(LinuxSystem linuxSystem) {
        this.linuxSystem = linuxSystem;
    }

    @Override
    public void execute() {
        linuxSystem.ls();
    }
}
