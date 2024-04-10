import java.io.File;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
public class ReadFileThread implements Runnable {
    private BlockingQueue<Student> outputQueue;

    public ReadFileThread(BlockingQueue<Student> outputQueue) {
        this.outputQueue = outputQueue;
    }

    @Override
    public void run() {
        try {
            File file = new File("student.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            // Lấy danh sách các thẻ Student từ file XML
            NodeList nodeList = doc.getElementsByTagName("Student");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    int id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
                    String name = element.getElementsByTagName("name").item(0).getTextContent();
                    String address = element.getElementsByTagName("address").item(0).getTextContent();
                    String dateOfBirth = element.getElementsByTagName("dateOfBirth").item(0).getTextContent();

                    // Tạo đối tượng Student và đưa vào hàng đợi outputQueue
                    Student student = new Student(id, name, address, dateOfBirth);
                    outputQueue.put(student);
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
