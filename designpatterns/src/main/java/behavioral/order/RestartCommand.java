package behavioral.order;

public class RestartCommand implements ICommand{
    private final LinuxSystem linuxSystem;

    public RestartCommand(LinuxSystem linuxSystem) {
        this.linuxSystem = linuxSystem;
    }

    @Override
    public void execute() {
        linuxSystem.restart();
    }
}
