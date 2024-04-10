
import java.util.Scanner;

public class Student {
    private int id;
    private String name;
    private String address;
    private String dateOfBirth;
    private int age;
    private int encodedAge;
    private boolean isPrime;
    private int sum;

    public Student(int id, String name, String address, String dateOfBirth) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getEncodedAge() {
        return encodedAge;
    }

    public void setEncodedAge(int encodedAge) {
        this.encodedAge = encodedAge;
    }

    public boolean isPrime() {
        return isPrime;
    }

    public void setPrime(boolean isPrime) {
        this.isPrime = isPrime;
    }
    public void inputinfo(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Nhập MSV:");      
        this.setId(sc.nextInt());
        System.out.println("Nhập tên sinh viên:");    
        this.setName(sc.nextLine());
        System.out.println("Nhập địa chỉ:");    
        this.setAddress(sc.nextLine());
        System.out.println("Nhập ngày sinh:");    
        this.setDateOfBirth(sc.nextLine());
    }
}
