package Task;

import Task.TODOListManager;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

public class TODOListManagerTest {
    private TODOListManager manager = new TODOListManager();

    @Test
    public void handling_stream_with_unknown_commands() throws Exception {
        String str = "asdfsdf\nsuip\nsdfsdf\naddlist \"superlist\"\naddtask \"supertask\"\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes(Charset.defaultCharset()));
        manager.doMainLoop(stream);
    }

    @Test
    public void handling_stream_with_exit_command() throws Exception {
        String str = "exit";
        InputStream stream = new ByteArrayInputStream(str.getBytes(Charset.defaultCharset()));
        manager.doMainLoop(stream);
    }
}