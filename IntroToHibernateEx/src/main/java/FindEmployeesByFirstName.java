import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class FindEmployeesByFirstName {
    private static final String GET_EMPLOYEE_WHICH_NAME_START_WITH_PATTERN
            = "select e from Employee e where e.firstName LIKE :pt";

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        String pattern = new Scanner(System.in).nextLine();

        entityManager.createQuery(GET_EMPLOYEE_WHICH_NAME_START_WITH_PATTERN, Employee.class)
                .setParameter("pt", pattern + "%")
                .getResultList().forEach(System.out::println);

    }
}
