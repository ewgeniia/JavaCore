package Task;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;

public class TaskListsReader {
    public static TaskList read(final String filePath) throws Exception {
        File file = new File(filePath);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);

        document.getDocumentElement().normalize();


        NodeList nList = document.getElementsByTagName("TaskList");
        assert nList.getLength() == 1;
        Element taskListElement = (Element)nList.item(0);
        TaskList taskList = new TaskList(taskListElement.getAttribute("name"), Integer.parseUnsignedInt(taskListElement.getAttribute("id")));
        NodeList tasks = taskListElement.getChildNodes();
        for (int j = 0; j < tasks.getLength(); ++j) {
            Node taskNode = tasks.item(j);

            if (taskNode.getNodeType() == Node.ELEMENT_NODE) {
                Element taskNodeElement = (Element)taskNode;
                taskList.addTask(taskNodeElement.getTextContent());
                taskList.toArrayList().get(taskList.toArrayList().size() - 1).setStatus(toStatus(taskNodeElement.getAttribute("status")));
            }
        }
        return taskList;
    }

    private static TaskStatus toStatus(final String str) throws Exception {
        switch (str) {
            case "in progress":
                return TaskStatus.IN_PROGRESS;
            case "done":
                return TaskStatus.DONE;
            case "cancelled":
                return TaskStatus.CANCELLED;
            default:
                throw new Exception("error reading task status " + str);
        }
    }
}
