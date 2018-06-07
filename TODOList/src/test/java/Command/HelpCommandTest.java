package Command;

import Command.HelpCommand;
import Command.InputCommandType;
import Task.TODOListManager;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class HelpCommandTest {
    private HelpCommand command = new HelpCommand(new ArrayList<>());

    @Test
    public void getMinRequiredArgsCount() throws Exception {
        assertEquals(0, command.getMinRequiredArgsCount());
    }

    @Test
    public void getMaxOptionalArgsCount() throws Exception {
        assertEquals(0, command.getMaxOptionalArgsCount());
    }

    @Test
    public void execute() throws Exception {
        command.execute(new TODOListManager().getController());
    }

    @Test
    public void getType() throws Exception {
        assertEquals(InputCommandType.HELP, command.getType());
    }

    @Test
    public void setArguments() throws Exception {
    }
}
