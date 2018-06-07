package Task;

import Task.TaskList;
import Task.TaskListsSerializer;
import org.junit.Test;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class TaskListsSerializerTest {
    @Test
    public void serialize() throws Exception {
        TaskList list1 = new TaskList("temp", 1);
        list1.addTask("task1");
        TaskListsSerializer.serialize(list1, "lists/temp.xml");
        String content = getFileContent("lists/temp.xml");
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\r\n" +
                "<Root>\r\n" +
                "    <TaskList id=\"1\" name=\"temp\">\r\n" +
                "        <Task id=\"1\" status=\"in progress\">task1</Task>\r\n" +
                "    </TaskList>\r\n" +
                "</Root>\r\n", content);
    }

    private String getFileContent(final String filePath) throws Exception {
        byte[] encoded = Files.readAllBytes(Paths.get(filePath));
        return new String(encoded, Charset.defaultCharset());
    }
}
