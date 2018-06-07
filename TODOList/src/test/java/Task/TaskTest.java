package Task;

import Task.Task;
import Task.TaskStatus;
import org.junit.Test;

import static org.junit.Assert.*;

public class TaskTest {
    private Task task = new Task(-1, "text");

    @Test
    public void setText() throws Exception {
        task.setText("abcd");
        assertEquals("abcd", task.getText());
    }

    @Test
    public void getText() throws Exception {
        String s = task.getText();
        s = "new text"; // trying to change task's data
        assertEquals("text", task.getText()); // asserting that data didn't change
    }

    @Test
    public void getId() throws Exception {
        assertEquals(-1, task.getId());
    }

    @Test
    public void getStatus() throws Exception {
        assertEquals(TaskStatus.IN_PROGRESS, task.getStatus()); // by default Task.TaskStatus.IN_PROGRESS
    }

    @Test
    public void setStatus() throws Exception {
        task.setStatus(TaskStatus.DONE);
        assertEquals(TaskStatus.DONE, task.getStatus());
    }
}
