package Command;

import Task.TODOListManager;

import java.util.ArrayList;

public class ShowCommand extends InputCommand {
    private String listName;
    private Integer listId;
    public ShowCommand(final ArrayList<String> args) {
        super(args);
        setArguments(args);
    }

    @Override
    public int getMinRequiredArgsCount() {
        return 0;
    }

    @Override
    public int getMaxOptionalArgsCount() {
        return 1;
    }

    @Override
    public void execute(TODOListManager.Controller controller) {
        if (listId != null) {
            controller.onShowList(listId.intValue());
            return;
        } else  if (listName != null) {
            controller.onShowList(listName);
            return;
        }
        controller.onShow();
    }

    @Override
    public InputCommandType getType() {
        return InputCommandType.SHOW;
    }

    @Override
    protected void setArguments(final ArrayList<String> args) {
        if (args.isEmpty()) {
            return;
        }
        try {
            listId = Integer.parseUnsignedInt(args.get(0));
        } catch (Exception ex) {
            listName = args.get(0);
        }
    }
}
