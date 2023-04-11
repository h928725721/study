package behavioral.order;

public class CdCommand implements ICommand{
    private final LinuxSystem linuxSystem;

    public CdCommand(LinuxSystem linuxSystem) {
        this.linuxSystem = linuxSystem;
    }

    @Override
    public void execute() {
        linuxSystem.cd();
    }
}
