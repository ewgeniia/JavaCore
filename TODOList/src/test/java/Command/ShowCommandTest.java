package Command;

import Command.InputCommandType;
import Command.ShowCommand;
import Task.TODOListManager;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ShowCommandTest {
    private ShowCommand command = new ShowCommand(new ArrayList<>());

    @Test
    public void getMinRequiredArgsCount() throws Exception {
        assertEquals(0, command.getMinRequiredArgsCount());
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
        assertEquals(InputCommandType.SHOW, command.getType());
    }

    @Test
    public void setArguments() throws Exception {
    }
}
