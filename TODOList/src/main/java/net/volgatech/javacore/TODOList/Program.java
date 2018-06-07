import Task.TODOListManager;

public class Program {
    private static TODOListManager manager = new TODOListManager();

    public static void main(String[] args) throws Exception {
        try {
            manager.doMainLoop(System.in);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
}
