package Task;

import Task.TaskList;
import org.junit.Test;

import static org.junit.Assert.*;

public class TaskListTest {
    private TaskList taskList = new TaskList("default name", -1);

    @Test
    public void addTask() throws Exception {
        assertEquals(true, taskList.addTask("some name"));
        assertEquals(false, taskList.addTask("some name"));
        assertEquals(1, taskList.toArrayList().size());
    }

    @Test
    public void deleteTask() throws Exception {
        assertEquals(false, taskList.deleteTask(1));
        taskList.addTask("some task");
        assertEquals(true, taskList.deleteTask(1));
    }

    @Test
    public void getId() throws Exception {
        assertEquals(-1, taskList.getId());
    }

    @Test
    public void getName() throws Exception {
        assertEquals("default name", taskList.getName());
    }

    @Test
    public void toArrayList() throws Exception {
        assertEquals(0, taskList.toArrayList().size());
    }

    @Test
    public void hasTaskWithText() throws Exception {
        assertEquals(false, taskList.hasTaskWithText("text"));
        taskList.addTask("text");
        assertEquals(true, taskList.hasTaskWithText("text"));
    }
}
