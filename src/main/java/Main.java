import dto.Course;
import dto.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {

    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();

        Course course = session.get(Course.class, 1);
        System.out.println("Info about course:");
        System.out.println("id: " + course.getName());
        System.out.println("name: " + course.getName());
        System.out.println("description: " + course.getDescription());
        System.out.println("duration: " + course.getDuration());
        System.out.println("price: " + course.getPrice());
        System.out.println("price per hour: " + course.getPricePerHour());
        System.out.println("students count: " + course.getStudentsCount());
        System.out.println("teacher's name: " + course.getTeacher().getName());
        System.out.println("teacher's age: " + course.getTeacher().getAge());
        System.out.println("teacher's salary: " + course.getTeacher().getSalary());
        System.out.println("type: " + course.getType());
        System.out.println("Students on course:");
        for (Student student : course.getStudents()) {
            System.out.println("Student's name: " + student.getName());
            System.out.println("Student's age: " + student.getAge());
            System.out.println("Student's registration date: " + student.getRegistrationDate());
            System.out.println("---------------------------------");
        }
        System.out.println();

        sessionFactory.close();
    }
}
