package Command;

import Command.AddListCommand;
import Command.InputCommandType;
import Task.TODOListManager;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AddListCommandTest {
    private AddListCommand command = new AddListCommand(new ArrayList<String>() {{ add("name"); }});

    @Test
    public void getMinRequiredArgsCount() throws Exception {
        assertEquals(1, command.getMinRequiredArgsCount());
    }

    @Test
    public void getMaxOptionalArgsCount() throws Exception {
        assertEquals(1, command.getMaxOptionalArgsCount());
    }

    @Test
    public void execute() throws Exception {
        command.execute(new TODOListManager().getController());
    }

    @Test
    public void getType() throws Exception {
        assertEquals(InputCommandType.ADD_LIST, command.getType());
    }

    @Test
    public void setArguments() throws Exception {
    }
}
