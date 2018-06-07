package Task;

import Command.InputCommand;
import Command.InputCommandParser;
import Command.InputCommandType;
import Task.Task;
import Task.TaskList;
import Task.TaskListsSerializer;
import Task.TaskListsReader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.io.File;
import java.nio.file.Files;

public class TODOListManager {
	
	public TODOListManager() {
	    parser = new InputCommandParser();
	    running = true;
	    controller = new Controller();
	    listIdToInsert = 1;
	    taskLists = new ArrayList<>();
	}
    public class Controller {

        public boolean onExit() {
            System.out.println("bye");
            controller.save("lists");
            running = false;
            return true;
        }

        /**
         * Команда для вывода списков задач с их содержимым в System.out
         */
        public boolean onShow() {
            if (taskLists.isEmpty()) {
                System.out.println("nothing to show...");
                return true;
            }
            for (TaskList taskList : taskLists) {
                System.out.println("TaskList #" + taskList.getId() + " \"" + taskList.getName() + "\":");
                ArrayList<Task> tasks = taskList.toArrayList();
                if (tasks.isEmpty()) {
                    System.out.println("\tempty...");
                    continue;
                }
                for (Task task : tasks) {
                    System.out.println("\tTask " + task.getId() + ". Text: \"" + task.getText() + "\". Status: " + task.getStatus().toString() + ".");
                }
            }
            return true;
        }

        public boolean onShowList(int listId) {
            if (taskLists.isEmpty()) {
                System.out.println("nothing to show...");
                return true;
            }
            for (TaskList taskList : taskLists) {
                if (listId == taskList.getId())
                {
                    System.out.println("TaskList #" + taskList.getId() + " \"" + taskList.getName() + "\":");
                    ArrayList<Task> tasks = taskList.toArrayList();
                    if (tasks.isEmpty()) {
                        System.out.println("\tempty...");
                        return true;
                    }
                    for (Task task : tasks) {
                        System.out.println("\tTask " + task.getId() + ". Text: \"" + task.getText() + "\". Status: " + task.getStatus().toString() + ".");
                    }
                    return true;
                }
            }
            System.out.println("TaskList #" + listId + " not found");
            return false;
        }

        public boolean onShowList(final String name) {
            if (taskLists.isEmpty()) {
                System.out.println("nothing to show...");
                return true;
            }
            for (TaskList taskList : taskLists) {
                if (name.equals(taskList.getName()))
                {
                    System.out.println("TaskList #" + taskList.getId() + " \"" + taskList.getName() + "\":");
                    ArrayList<Task> tasks = taskList.toArrayList();
                    if (tasks.isEmpty()) {
                        System.out.println("\tempty...");
                        return true;
                    }
                    for (Task task : tasks) {
                        System.out.println("\tTask " + task.getId() + ". Text: \"" + task.getText() + "\". Status: " + task.getStatus().toString() + ".");
                    }
                    return true;
                }

            }
            System.out.println("TaskList \"" + name + "\" not found");
            return false;
        }

        /**
         * Обработчик для команды `help`
         */
        public boolean onHelp() {
            InputCommandType[] commandTypes = InputCommandType.values();
            if (commandTypes.length == 0) {
                System.out.println("There is no available commands. Developers are too lazy!");
                return true;
            }
            System.out.println("List of available commands:");
            int index = 1;
            for (InputCommandType commandType : commandTypes) {
                System.out.println(index + ". " + commandType.toString() + ". " + commandType.getHelpMessage() + ".");
                ++index;
            }
            return true;
        }

        /**
         * Добавляет задачу в последний добавленный список, если таковой имеется.
         * Выводит в System.out информацию о выполненной операции
         */
        public boolean addTaskToLastInsertedList(final String text) {
            if (taskLists.isEmpty()) {
                System.out.println("Error: no any TaskList to insert at");
                return false;
            }
            TaskList lastInsertedTaskList = taskLists.get(taskLists.size() - 1);
            if (lastInsertedTaskList.addTask(text)) {
                System.out.println("Task \"" + text + "\" added to TaskList #" + lastInsertedTaskList.getId());
                return true;
            } else {
                System.out.println("Error: TaskList #" + lastInsertedTaskList.getId() + " already have task with text \"" + text + "\"");
                return false;
            }
        }

        /**
         * Добавляет задачу в список с заданным идентификатором listId, если таковой имеется.
         * Выводит в System.out информацию о выполненной операции
         */
        public boolean addTask(final String text, int listId) {
            TaskList list = getListById(listId);
            if (list == null) {
                System.out.println("Error: TaskList #" + listId + " doesn't exists");
                return false;
            }
            if (list.addTask(text)) {
                System.out.println("Task \"" + text + "\" added to TaskList #" + list.getId());
                return true;
            } else {
                System.out.println("Error: TaskList #" + list.getId() + " already have task with text \"" + text + "\"");
                return false;
            }
        }

        /**
         * Команда для добавления нового списка задач
         */
        public boolean addTaskList(final String name) {
            for (TaskList list : taskLists) {
                if (name.equals(list.getName())) {
                    System.out.println("Error: TaskList named \"" + list.getName() + "\" already exists!");
                    return false;
                }
            }
            taskLists.add(new TaskList(name, listIdToInsert));
            System.out.println("TaskList #" + listIdToInsert + " \"" + taskLists.get(taskLists.size() - 1).getName() + "\" added");
            ++listIdToInsert;
            return true;
        }

        /**
         * Удаление задачи с заданным taskId из списка с идентификатором listId
         */
        public boolean deleteTask(int taskId, int listId) {
            TaskList list = getListById(listId);
            if (list == null) {
                System.out.println("TaskList #" + listId + " not found");
                return false;
            }
            if (list.deleteTask(taskId)) {
                System.out.println("Task " + taskId + " removed from TaskList #" + listId);
                return true;
            } else {
                System.out.println("Task " + taskId + " not found at TaskList #" + listId);
                return false;
            }
        }

        /**
         * Удаление списка задач с заданным идентификатором
         */
        public boolean deleteTaskList(int listId) {
            TaskList taskList = getListById(listId);
            if (taskList == null) {
                System.out.println("Error: can't find TaskList #" + listId);
                return false;
            }
            taskLists.remove(taskList);
            System.out.println("TaskList \"" + taskList.getName() + "\" #" + taskList.getId() + " deleted successfully");
            return true;
        }

        public boolean editTaskStatus(int taskId, int listId, final TaskStatus status) {
            TaskList taskList = getListById(listId);
            if (taskList == null) {
                System.out.println("Error: can't find TaskList #" + listId);
                return false;
            }
            Task task = taskList.getTask(taskId);
            if (task == null) {
                System.out.println("Task " + taskId + " not found at TaskList #" + listId);
                return false;
            }
            if (task.getStatus().equals(status)) {
                System.out.println("Task " + taskId + " already have that status");
                return false;
            }
            task.setStatus(status);
            System.out.println("Task " + taskId + " status has been changed to `" + status.toString() + "`");
            return true;
        }

        public boolean editTaskText(int taskId, int listId, final String text) {
            TaskList taskList = getListById(listId);
            if (taskList == null) {
                System.out.println("Error: can't find TaskList #" + listId);
                return false;
            }
            Task task = taskList.getTask(taskId);
            if (task == null) {
                System.out.println("Task " + taskId + " not found at TaskList #" + listId);
                return false;
            }
            if (task.getText().equals(text)) {
                System.out.println("Task " + taskId + " already have that text");
                return false;
            }
            task.setText(text);
            System.out.println("Task " + taskId + " text has been changed to `" + text + "`");
            return true;
        }

        public boolean editListName(int listId, final String name) {
            TaskList taskList = getListById(listId);
            if (taskList == null) {
                System.out.println("Error: can't find TaskList #" + listId);
                return false;
            }
            if (taskList.getName().equals(name)) {
                System.out.println("Error: TaskList #" + listId + " already have that name");
                return false;
            }
            taskList.setName(name);
            System.out.println("TaskList #" + listId + " has been renamed to `" + name + "`");
            return true;
        }

        public boolean save(final String dir) {
            for (TaskList list: taskLists) {
                try {
                    String path = dir + "/" + list.getName() + ".xml";
                    TaskListsSerializer.serialize(list, path);
                } catch (Exception ex) {
                    System.out.println("Error: failed to save tasks - " + ex.getMessage());
                    return false;
                }
            }
            System.out.println("All task lists has been saved in folder: " + dir);
            return true;
        }

        public boolean importFromDirectory(final String path) {
            File F = new File(path);
            File[] fList = F.listFiles();
            if (fList == null) {
                File tempFile = new File(path);
                tempFile.mkdir();
                fList = tempFile.listFiles();
                System.out.println("Path not not exists. Created path: " + path);
            }
            for(int i = 0; i < fList.length; i++)
            {
                if(fList[i].isFile()) {
                    try {
                        TaskList list = TaskListsReader.read(path +"/"+ fList[i].getName());
                        TaskList tmpName = isListExists(list.getName());
                        TaskList tmpId = getListById(list.getId());
                        if (tmpName == null && tmpId == null) {
                            taskLists.add(TaskListsReader.read(path +"/"+ fList[i].getName()));
                            ++listIdToInsert;
                        }
                    } catch (Exception ex) {
                        System.out.println("Error: failed to import tasks - " + ex.getMessage());
                        return false;
                    }
                }
            }
            System.out.println("All task lists imported from " + path);
            return  true;
        }

        public boolean importFromFile(final String path) {
            try {
                taskLists.add(TaskListsReader.read(path));
                ++listIdToInsert;
            } catch (Exception ex) {
                System.out.println("Error: failed to import tasks - " + ex.getMessage());
                return false;
            }
            System.out.println("List imported " + path);
            return true;

        }

    }



    private InputCommandParser parser;
    private boolean running;
    private Controller controller;
    private int listIdToInsert;
    private ArrayList<TaskList> taskLists;

    public Controller getController() {
        return controller;
    }

    public void doMainLoop(InputStream stream) throws Exception {
        System.out.println("Welcome to TODO-List! Please, write some commands...\nOr type `help` for help.");
        Scanner scanner = new Scanner(stream);
        while (running) {
            System.out.print("> ");
            if (scanner.hasNextLine()) {
                dispatchInput(scanner.nextLine());
            } else {
                running = false;
            }
        }
    }

    private void dispatchInput(final String input) {
        try {
            InputCommand command = parser.parse(input);
            command.execute(controller);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    private TaskList getListById(int id) {
        for (TaskList list : taskLists) {
            if (list.getId() == id) {
                return list;
            }
        }
        return null;
    }

    private TaskList isListExists(final String name) {
        for (TaskList list : taskLists) {
            if (name.equals(list.getName())) {
                return list;
            }
        }
        return null;
    }

}
