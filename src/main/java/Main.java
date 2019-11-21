import dto.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernateCreate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        metadata = new MetadataSources(registry).getMetadataBuilder().build();
        sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();

        List<Purchase> purchaseList = session.createQuery("From " + Purchase.class.getSimpleName()).getResultList();

        for (Purchase purchase : purchaseList) {
            String hql = "From " + Student.class.getSimpleName() + " where name = '" + purchase.getStudentName() + "'";
            Student student = (Student)session.createQuery(hql).getSingleResult();
            int studentId = student.getId();

            hql = "From " + Course.class.getSimpleName() + " where name = '" + purchase.getCourseName() + "'";
            Course course = (Course) session.createQuery(hql).getSingleResult();
            int courseId = course.getId();

            PurchaseId purchaseId = new PurchaseId();
            purchaseId.setStudentId(studentId);
            purchaseId.setCourseId(courseId);
            session.save(purchaseId);
        }

        sessionFactory.close();
    }
}
