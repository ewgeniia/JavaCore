package Task;

import java.util.ArrayList;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class TaskListsSerializer {
    public static void serialize(final TaskList taskList, final String filePath) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();

        Element root = document.createElement("Root");
        document.appendChild(root);

        Element taskListElement = document.createElement("TaskList");
        taskListElement.setAttribute("id", String.valueOf(taskList.getId()));
        taskListElement.setAttribute("name", taskList.getName());

        for (Task task : taskList.toArrayList()) {
            Element taskElement = document.createElement("Task");
            taskElement.setAttribute("id", String.valueOf(task.getId()));
            taskElement.setAttribute("status", task.getStatus().toString());
            taskElement.appendChild(document.createTextNode(task.getText()));
            taskListElement.appendChild(taskElement);
        }
        root.appendChild(taskListElement);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(filePath));

        transformer.transform(source, result);
    }
}
