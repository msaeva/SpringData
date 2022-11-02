import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class ContainsEmployee {
    private static final String GET_EMPLOYEE_BY_FIRST_AND_LAST_NAME =
            "select count(e) from Employee e where e.firstName = :fn and e.lastName = :ln";

    public static void main(String[] args) {

        final EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        final String[] arguments = new Scanner(System.in).nextLine().split("\\s+");

        final String first_name = arguments[0];
        final String last_name = arguments[1];

        entityManager.getTransaction().begin();

        final Long singleResult = entityManager
                .createQuery(GET_EMPLOYEE_BY_FIRST_AND_LAST_NAME, Long.class)
                .setParameter("fn", first_name)
                .setParameter("ln", last_name)
                .getSingleResult();

        if (singleResult == 0) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
