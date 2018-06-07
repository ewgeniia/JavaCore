package Command;

import Task.TODOListManager;
import Task.TaskStatus;

import java.util.ArrayList;

public class EditTaskStatusCommand extends InputCommand {
    private int taskId;
    private int listId;
    private TaskStatus status;

    public EditTaskStatusCommand(final ArrayList<String> args) {
        super(args);
        setArguments(args);
    }

    @Override
    public int getMinRequiredArgsCount() {
        return 3;
    }

    @Override
    public int getMaxOptionalArgsCount() {
        return 3;
    }

    @Override
    public void execute(final TODOListManager.Controller controller) {
        controller.editTaskStatus(taskId, listId, status);
    }

    @Override
    public InputCommandType getType() {
        return InputCommandType.EDIT_TASK_STATUS;
    }

    @Override
    protected void setArguments(final ArrayList<String> args) {
        taskId = Integer.parseUnsignedInt(args.get(0));
        listId = Integer.parseUnsignedInt(args.get(1));
        status = toTaskStatus(args.get(2));
    }

    private TaskStatus toTaskStatus(final String str) {
        switch (str) {
            case "in progress":
                return TaskStatus.IN_PROGRESS;
            case "cancelled":
                return TaskStatus.CANCELLED;
            case "done":
                return TaskStatus.DONE;
            default:
                throw new IllegalArgumentException("illegal status `" + str + "`");
        }
    }
}
