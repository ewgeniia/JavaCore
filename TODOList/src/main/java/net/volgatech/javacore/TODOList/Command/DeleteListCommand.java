package Command;

import Task.TODOListManager;

import java.util.ArrayList;

public class DeleteListCommand extends InputCommand {
    private int listId;

    public DeleteListCommand(final ArrayList<String> args) {
        super(args);
        setArguments(args);
    }

    @Override
    public int getMinRequiredArgsCount() {
        return 1;
    }

    @Override
    public int getMaxOptionalArgsCount() {
        return 1;
    }

    @Override
    public void execute(final TODOListManager.Controller controller) {
        controller.deleteTaskList(listId);
    }

    @Override
    public InputCommandType getType() {
        return InputCommandType.DELETE_LIST;
    }

    @Override
    protected void setArguments(final ArrayList<String> args) {
        listId = Integer.parseUnsignedInt(args.get(0));
    }
}
