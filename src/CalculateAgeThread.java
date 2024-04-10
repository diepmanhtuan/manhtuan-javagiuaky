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
import org.xml.sax.SAXException;
public class CalculateAgeThread implements Runnable {
    private BlockingQueue<Student> inputQueue;
    private BlockingQueue<Student> outputQueue;

    public CalculateAgeThread(BlockingQueue<Student> inputQueue, BlockingQueue<Student>outputQueue) {
        this.inputQueue = inputQueue;
        this.outputQueue = outputQueue;
    }

    @Override
    public void run() {
        try {
            Student student = inputQueue.take();
            String dateOfBirth = student.getDateOfBirth();

            // Tính tuổi dựa trên ngày sinh
           int age = ...;

            student.setAge(age);

            // Mã hoá tuổi thành chữ số
            int encodedAge = ...;

            student.setEncodedAge(encodedAge);

            // Đưa student vào hàng đợi outputQueue
            outputQueue.put(student);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
