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
        System.out.println("teacher id: " + course.getTeacherId());
        System.out.println("type: " + course.getType());
        System.out.println();

        Student student = session.get(Student.class, 1);
        System.out.println("Info about student:");
        System.out.println("id: " + student.getId());
        System.out.println("name: " + student.getName());
        System.out.println("registration date: " + student.getRegistrationDate());
        System.out.println("age: " + student.getAge());

        sessionFactory.close();
    }
}
