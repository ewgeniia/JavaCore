package Command;

import Task.TODOListManager;

import java.util.ArrayList;

public class EditTaskTextCommand extends InputCommand {
    private int listId;
    private int taskId;
    private String text;

    public EditTaskTextCommand(final ArrayList<String> args) {
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
        controller.editTaskText(taskId, listId, text);
    }

    @Override
    public InputCommandType getType() {
        return InputCommandType.EDIT_TASK_TEXT;
    }

    @Override
    protected void setArguments(final ArrayList<String> args) {
        taskId = Integer.parseUnsignedInt(args.get(0));
        listId = Integer.parseUnsignedInt(args.get(1));
        text = args.get(2);
    }
}
