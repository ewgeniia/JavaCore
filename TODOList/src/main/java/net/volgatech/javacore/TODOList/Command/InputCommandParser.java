package Command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputCommandParser {
    private static final int MATCH_DATA_GROUP_ID = 1;
    private static final int FIRST_ELEMENT_ID = 0;

    private Pattern pattern;
    private HashMap<String, InputCommandType> commandTypeMap;

    public InputCommandParser() {
        pattern = Pattern.compile("([^\"]\\S*|\".*\")\\s*");
        commandTypeMap = new HashMap<String, InputCommandType>() {{
            put("show", InputCommandType.SHOW);
            put("help", InputCommandType.HELP);
            put("exit", InputCommandType.EXIT);
            put("save", InputCommandType.SAVE);
            put("import", InputCommandType.IMPORT);
            put("addlist", InputCommandType.ADD_LIST);
            put("addtask", InputCommandType.ADD_TASK);
            put("deletelist", InputCommandType.DELETE_LIST);
            put("deletetask", InputCommandType.DELETE_TASK);
            put("edittaskstatus", InputCommandType.EDIT_TASK_STATUS);
            put("edittasktext", InputCommandType.EDIT_TASK_TEXT);
            put("editlistname", InputCommandType.EDIT_LIST_NAME);
        }};
    }

    public InputCommand parse(final String input) throws Exception {
        final ArrayList<String> tokens = tokenize(input);
        if (tokens.isEmpty()) {
            throw new IllegalArgumentException("empty input");
        }

        final InputCommandType type = commandTypeMap.get(tokens.get(FIRST_ELEMENT_ID).toLowerCase());
        if (type == null) {
            throw new IllegalArgumentException("unknown command");
        }

        return getCommand(type, spliceToOnlyArguments(tokens));
    }

    private InputCommand getCommand(final InputCommandType type, final ArrayList<String> args) throws Exception {
        switch (type) {
            case ADD_LIST:
                return new AddListCommand(args);
            case DELETE_LIST:
                return new DeleteListCommand(args);
            case ADD_TASK:
                return new AddTaskCommand(args);
            case DELETE_TASK:
                return new DeleteTaskCommand(args);
            case EDIT_TASK_STATUS:
                return new EditTaskStatusCommand(args);
            case EDIT_TASK_TEXT:
                return new EditTaskTextCommand(args);
            case EDIT_LIST_NAME:
                return new EditListNameCommand(args);
            case SHOW:
                return new ShowCommand(args);
            case HELP:
                return new HelpCommand(args);
            case EXIT:
                return new ExitCommand(args);
            case SAVE:
                return new SaveCommand(args);
            case IMPORT:
                return new ImportCommand(args);
            default:
                throw new Exception("default branch should be unreachable");
        }
    }

    private ArrayList<String> spliceToOnlyArguments(final ArrayList<String> fullCommandInput) {
        return new ArrayList<String>(fullCommandInput.subList(1, fullCommandInput.size()));
    }

    private ArrayList<String> tokenize(final String input) {
        ArrayList<String> matches = new ArrayList<>();
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            matches.add(matcher.group(MATCH_DATA_GROUP_ID).replaceAll("\"", ""));
        }
        return matches;
    }
}
