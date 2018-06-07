package Command;

import Task.TODOListManager;

import java.util.ArrayList;

public class DeleteTaskCommand extends InputCommand {
    private int taskId;
    private int listId;

    public DeleteTaskCommand(final ArrayList<String> args) {
        super(args);
        setArguments(args);
    }

    @Override
    public int getMinRequiredArgsCount() {
        return 2;
    }

    @Override
    public int getMaxOptionalArgsCount() {
        return 2;
    }

    @Override
    public void execute(final TODOListManager.Controller controller) {
        controller.deleteTask(taskId, listId);
    }

    @Override
    public InputCommandType getType() {
        return InputCommandType.DELETE_TASK;
    }

    @Override
    protected void setArguments(final ArrayList<String> args) {
        taskId = Integer.parseUnsignedInt(args.get(0));
        listId = Integer.parseUnsignedInt(args.get(1));
    }
}
