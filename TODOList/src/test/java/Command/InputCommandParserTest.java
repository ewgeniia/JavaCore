package Command;

import Command.InputCommand;
import Command.InputCommandParser;
import Command.InputCommandType;
import org.junit.Test;

import static org.junit.Assert.*;

public class InputCommandParserTest {
    private InputCommandParser parser = new InputCommandParser();

    @Test(expected = IllegalArgumentException.class)
    public void throws_exception_on_empty_input() throws Exception {
        parser.parse("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void throws_exception_on_unknown_command() throws Exception {
        parser.parse("clear 1 2 3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void throws_exception_on_command_invalid_arguments_count() throws Exception {
        parser.parse("addtask 1 2 3 4");
    }

    @Test
    public void returns_executable_command_on_correct_input() throws Exception {
        InputCommand command = parser.parse("AddList \"Some List Name\"");
        assertEquals(InputCommandType.ADD_LIST, command.getType());
        assertEquals(1, command.getMaxOptionalArgsCount());
        assertEquals(1, command.getMinRequiredArgsCount());
    }
}