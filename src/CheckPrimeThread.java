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
public class CheckPrimeThread implements Runnable {
    private BlockingQueue<Student> inputQueue;
    private BlockingQueue<Student> outputQueue;

    public CheckPrimeThread(BlockingQueue<Student> inputQueue, BlockingQueue<Student> outputQueue) {
        this.inputQueue = inputQueue;
        this.outputQueue = outputQueue;
    }

   @Override
    public void run() {
        try {
            Student student = inputQueue.take();
            int encodedAge = student.getEncodedAge();

            // Tính tổng các chữ số
            int sum = 0;
            int temp = encodedAge;
            while (temp != 0) {
                int digit = temp % 10;
                sum += digit;
                temp /= 10;
            }

            // Kiểm tra tổng các chữ số có phải là số nguyên tố hay không
            boolean isPrime = isPrime(sum);

            student.setSum(sum);
            student.setPrime(isPrime);

            // Đưa student vào hàng đợi outputQueue
            outputQueue.put(student);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Kiểm tra một số có phải là số nguyên tố hay không
    private boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }
}