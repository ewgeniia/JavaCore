package Task;

public class Task {
    private TaskStatus status;
    private String text;
    private int id;

    public Task(int id, final String text) {
        this.text = text;
        this.id = id;
        status = TaskStatus.IN_PROGRESS;
    }

    public void setText(final String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public int getId() {
        return id;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(final TaskStatus status) {
        this.status = status;
    }
}
