package Command;

import Command.AddTaskCommand;
import Command.InputCommandType;
import Task.TODOListManager;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AddTaskCommandTest {
    private AddTaskCommand command = new AddTaskCommand(new ArrayList<String>() {{ add("1"); add("1"); }});

    @Test
    public void getMinRequiredArgsCount() throws Exception {
        assertEquals(1, command.getMinRequiredArgsCount());
    }

    @Test
    public void getMaxOptionalArgsCount() throws Exception {
        assertEquals(2, command.getMaxOptionalArgsCount());
    }

    @Test
    public void execute() throws Exception {
        command.execute(new TODOListManager().getController());
    }

    @Test
    public void getType() throws Exception {
        assertEquals(InputCommandType.ADD_TASK, command.getType());
    }

    @Test
    public void setArguments() throws Exception {
    }
}
