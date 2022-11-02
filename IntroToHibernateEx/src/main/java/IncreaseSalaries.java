import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class IncreaseSalaries {
    private static final String UPDATE_EMPLOYEES_SALARY = "UPDATE Employee as e set e.salary = e.salary * 1.12 " +
            "where e.department.name IN ('Engineering', 'Tool Design','Marketing', 'Information Services')";

    private static final String GET_INFO_ABOUT_EMPLOYEE = "select e from Employee e " +
            "where e.department.name IN ('Engineering', 'Tool Design','Marketing', 'Information Services')";


    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.createQuery(UPDATE_EMPLOYEES_SALARY);

        entityManager.createQuery(GET_INFO_ABOUT_EMPLOYEE, Employee.class)
                .getResultList().forEach(e -> System.out.println(e.getFirstName() + " " + e.getLastName() + " " + e.getSalary()));

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
