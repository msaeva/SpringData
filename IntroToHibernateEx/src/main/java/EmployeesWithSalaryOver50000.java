import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class EmployeesWithSalaryOver50000 {
    private static final String GET_ALL_NAMES_WHICH_SALARY_IS_MORE_THAN_50000 =
            "SELECT e.firstName from Employee e where e.salary > 50000";
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createQuery(GET_ALL_NAMES_WHICH_SALARY_IS_MORE_THAN_50000, String.class)
                .getResultList()
                .forEach(System.out::println);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
