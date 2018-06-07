package Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;
    private String name;
    private int id;
    private int taskIdToInsert;

    public TaskList(final String name, int id) {
        taskList = new ArrayList<>();
        this.id = id;
        this.name = name;
        taskIdToInsert = 1;
    }

    public boolean addTask(final String text) {
        if (hasTaskWithText(text)) {
            return false;
        }
        taskList.add(new Task(taskIdToInsert, text));
        ++taskIdToInsert;
        return true;
    }

    public boolean deleteTask(int taskId) {
        int foundTaskToDeleteIndex = -1;
        for (int i = 0; i < taskList.size(); ++i) {
            Task task = taskList.get(i);
            if (task.getId() == taskId) {
                foundTaskToDeleteIndex = i;
                break;
            }
        }
        if (foundTaskToDeleteIndex != -1) {
            taskList.remove(foundTaskToDeleteIndex);
            return true;
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Task> toArrayList() {
        return taskList;
    }

    public boolean hasTaskWithText(final String text) {
        for (Task task : taskList) {
            if (task.getText().equals(text)) {
                return true;
            }
        }
        return false;
    }

    public Task getTask(int taskId) {
        for (Task task : taskList) {
            if (task.getId() == taskId) {
                return task;
            }
        }
        return null;
    }
}
