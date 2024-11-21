package Lab5;

public class LinuxCommandExecutor extends BaseCommandExecutor {
    @Override
    protected String[] buildCommand(String command) {
        return new String[]{"bash", "-c", command};
    }
}
