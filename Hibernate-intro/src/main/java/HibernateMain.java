
import entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

//        Student example = new Student();
//        example.setName("Ivan");
//        session.persist(example);


        Student student = new Student();

        Student studentFromDB = session.get(Student.class, 1);
        System.out.println(studentFromDB.getName() + " " + studentFromDB.getId());

        List<Student> students = session.createQuery("FROM Student", Student.class).list();

        for (Student st : students) {
            System.out.println(st.getId() + " " + st.getName());
        }

        session.getTransaction().commit();
        session.close();


    }
}
