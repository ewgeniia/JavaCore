package Command;

import Task.TODOListManager;

import java.util.ArrayList;

public class EditListNameCommand extends InputCommand {
    private int listId;
    private String name;

    public EditListNameCommand(final ArrayList<String> args) {
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
        controller.editListName(listId, name);
    }

    @Override
    public InputCommandType getType() {
        return InputCommandType.EDIT_LIST_NAME;
    }

    @Override
    protected void setArguments(final ArrayList<String> args) {
        listId = Integer.parseUnsignedInt(args.get(0));
        name = args.get(1);
    }
}
