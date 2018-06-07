package Command;

import Task.TODOListManager;

import java.util.ArrayList;

public class ExitCommand extends InputCommand {
    public ExitCommand(final ArrayList<String> args) {
        super(args);
    }

    @Override
    public int getMinRequiredArgsCount() {
        return 0;
    }

    @Override
    public int getMaxOptionalArgsCount() {
        return 0;
    }

    @Override
    public void execute(final TODOListManager.Controller controller) {
        controller.onExit();
    }

    @Override
    public InputCommandType getType() {
        return InputCommandType.EXIT;
    }

    @Override
    protected void setArguments(final ArrayList<String> args) {
    }
}
