import java.io.File;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class Main {
    public static void main(String[] args) throws TransformerException {
        
        BlockingQueue<Student> queue1 = new LinkedBlockingQueue<>();
        BlockingQueue<Student> queue2 = new LinkedBlockingQueue<>();
        BlockingQueue<Student> queue3 = new LinkedBlockingQueue<>();
        Thread thread1 = new Thread(new ReadFileThread(queue1));
        Thread thread2 = new Thread(new CalculateAgeThread(queue1, queue2));
        Thread thread3 = new Thread(new CheckPrimeThread(queue2, queue3));

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();

            Student student = queue3.take();

            // Tạo file kq.xml
            createResultFile(student);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void createResultFile(Student student) throws TransformerConfigurationException, TransformerException {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // Tạo root element
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Student");
            doc.appendChild(rootElement);
            Element ageElement = doc.createElement("age");
            ageElement.setTextContent(String.valueOf(student.getAge()));
            rootElement.appendChild(ageElement);

            Element sumElement = doc.createElement("sum");
            sumElement.setTextContent(String.valueOf(student.getSum()));
            rootElement.appendChild(sumElement);

            Element isPrimeElement = doc.createElement("isPrime");
            isPrimeElement.setTextContent(String.valueOf(student.isPrime()));
            rootElement.appendChild(isPrimeElement);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("kq.xml"));

            transformer.transform(source, result);

            System.out.println("File kq.xml đã được tạo thành công.");
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }
}

