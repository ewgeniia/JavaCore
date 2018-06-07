package Command;

import Task.TODOListManager;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ImportCommandTest {
    private ImportCommand command = new ImportCommand(new ArrayList<String>() {{ add("lists"); }});

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
        assertEquals(InputCommandType.IMPORT, command.getType());
    }

    @Test
    public void setArguments() throws Exception {
    }
}
