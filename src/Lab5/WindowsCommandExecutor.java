package Lab5;

public class WindowsCommandExecutor extends BaseCommandExecutor {
    @Override
    protected String[] buildCommand(String command) {
        return new String[]{"cmd.exe", "/c", command};
    }
}