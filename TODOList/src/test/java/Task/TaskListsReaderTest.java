package Task;

import Task.Task;
import Task.TaskList;
import Task.*;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class TaskListsReaderTest {
    @org.junit.Test
    public void read() throws Exception {
        // 1. Create file with xml content.
        writeXmlData("lists/temp.xml");
        // 2. Read tasks from it.
        TaskList taskList = TaskListsReader.read("lists/temp.xml");
        assertEquals("TODO List Number One", taskList.getName());
        assertEquals(1, taskList.toArrayList().size());
        Task task1 = taskList.toArrayList().get(0);
        assertEquals("Do something", task1.getText());
        assertEquals(1, task1.getId());
        assertEquals(TaskStatus.IN_PROGRESS, task1.getStatus());
    }

    private void writeXmlData(final String filePath) throws Exception {
        File file = new File(filePath);
        PrintStream stream = new PrintStream(file);
        stream.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                "<Root>\n" +
                "    <TaskList id=\"1\" name=\"TODO List Number One\">\n" +
                "        <Task id=\"1\" status=\"in progress\">Do something</Task>\n" +
                "    </TaskList>\n" +
                "</Root>");
    }
}