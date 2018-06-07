package Command;

import Task.TODOListManager;

import java.util.ArrayList;

public abstract class InputCommand {
    private static final String ARG_PARSE_ERROR_COUNT_MESSAGE
            = "Invalid arguments count! Min: %d, max: %d, given: %d.\n";

    public InputCommand(final ArrayList<String> args) {
        if (args.size() < getMinRequiredArgsCount() ||
            args.size() > getMaxOptionalArgsCount()) {
            throw new IllegalArgumentException(
                    String.format(ARG_PARSE_ERROR_COUNT_MESSAGE + getHelpMessage() + ".",
                            getMinRequiredArgsCount(),
                            getMaxOptionalArgsCount(),
                            args.size()));
        }
    }

    public abstract int getMinRequiredArgsCount();
    public abstract int getMaxOptionalArgsCount();

    public abstract void execute(final TODOListManager.Controller controller);
    public abstract InputCommandType getType();

    private String getHelpMessage() {
        return getType().getHelpMessage();
    }

    protected abstract void setArguments(final ArrayList<String> args);
}
