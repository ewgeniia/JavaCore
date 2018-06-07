package Command;

import Task.TODOListManager;

import java.util.ArrayList;

public class AddTaskCommand extends InputCommand {
    private Integer listId;
    private String text;

    public AddTaskCommand(final ArrayList<String> args) {
        super(args);
        setArguments(args);
    }

    @Override
    public int getMinRequiredArgsCount() {
        return 1;
    }

    @Override
    public int getMaxOptionalArgsCount() {
        return 2;
    }

    @Override
    public void execute(final TODOListManager.Controller controller) {
        if (listId != null) {
            controller.addTask(text, listId);
            return;
        }
        controller.addTaskToLastInsertedList(text);
    }

    @Override
    public InputCommandType getType() {
        return InputCommandType.ADD_TASK;
    }

    @Override
    protected void setArguments(final ArrayList<String> args) {
        text = args.get(0);
        if (args.size() > 1) {
            listId = Integer.parseUnsignedInt(args.get(1));
        }
    }
}
