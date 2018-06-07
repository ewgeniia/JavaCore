package Command;

import Task.TODOListManager;

import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class ImportCommand extends InputCommand {
    private String filePath;

    public ImportCommand(final ArrayList<String> args) {
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
    public void execute(final TODOListManager.Controller controller) {
        if (filePath == null) {
            controller.importFromDirectory("lists");
        } else {
            Path path = FileSystems.getDefault().getPath(filePath);
            if (Files.isDirectory(path)) {
                controller.importFromDirectory(filePath);
            } else {
                controller.importFromFile(filePath);
            }
        }
    }

    @Override
    public InputCommandType getType() {
        return InputCommandType.IMPORT;
    }

    @Override
    protected void setArguments(final ArrayList<String> args) {
        if (args.isEmpty()) {
            return;
        }
        filePath = args.get(0);
    }
}
