package cn.tanlw.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class IntersectionTest {
    private List<Student> students = new ArrayList();
    private List<Student> requiredStudents = new ArrayList();
    private List<Customer> customers = new ArrayList<>();
    @Test
    public void test(){
        setUp();

        //exists
        //each object of requiredStudents should exists in customers, or else, throw an Exception.
        requiredStudents.stream().forEach(s -> customers.stream().filter( c -> c.getUsername().equalsIgnoreCase(s.getName()))
                .findAny().orElseThrow(() -> new RuntimeException("not exists.")));

        //intersection
        List<Student> intersection = students.stream()
                .filter(s -> customers.stream().anyMatch(c -> c.getUsername().equalsIgnoreCase(s.getName())))
                .collect(Collectors.toList());
        /**
         * OUTPUT:
         * [Student{name='Luck'}]
         */
        System.out.println(intersection);

        //override
        students.stream().filter(checkLength()).forEach(s -> s.setName(s.getName().substring(0, 3)));
        /**
         * OUTPUT:
         * [Student{name='Tom'}, Student{name='Jac'}, Student{name='Luc'}]
         */
        System.out.println(students);
    }

    private Predicate<Student> checkLength() {
        return s -> s.getName().length() > 3;
    }

    private void setUp() {
        students.add(new Student("Tom"));
        students.add(new Student("Jack"));
        students.add(new Student("Luck"));

        requiredStudents.add(new Student("Base"));
        requiredStudents.add(new Student("Luck"));

        customers.add(new Customer("Big"));
        customers.add(new Customer("Base"));
        customers.add(new Customer("Luck"));

    }
}
class Student{
    private String name;
    public Student(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Customer {
    private String username;
    public Customer(String username){
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "username='" + username + '\'' +
                '}';
    }
}
