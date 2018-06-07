package Command;

import Task.TODOListManager;

import java.util.ArrayList;

public class HelpCommand extends InputCommand {
    public HelpCommand(final ArrayList<String> args) {
        super(args);
        setArguments(args);
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
        controller.onHelp();
    }

    @Override
    public InputCommandType getType() {
        return InputCommandType.HELP;
    }

    @Override
    protected void setArguments(ArrayList<String> args) {
    }
}
