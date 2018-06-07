package Command;

import Task.TODOListManager;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class EditListNameCommandTest {
    private EditListNameCommand command = new EditListNameCommand(new ArrayList<String>() {{ add("1"); add("NewName"); }});

    @Test
    public void getMinRequiredArgsCount() throws Exception {
        assertEquals(2, command.getMinRequiredArgsCount());
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
        assertEquals(InputCommandType.EDIT_LIST_NAME, command.getType());
    }

    @Test
    public void setArguments() throws Exception {
    }
}
