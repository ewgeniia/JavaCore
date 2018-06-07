package Command;

import Task.TODOListManager;

import java.util.ArrayList;

public class AddListCommand extends InputCommand {
    private String listName;

    public AddListCommand(final ArrayList<String> args) {
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
        controller.addTaskList(listName);
    }

    @Override
    public InputCommandType getType() {
        return InputCommandType.ADD_LIST;
    }

    @Override
    protected void setArguments(final ArrayList<String> args) {
        listName = args.get(0);
    }
}
