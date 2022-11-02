import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmployeesFromDepartment {
    private static final String GET_ALL_EMPLOYEES_IN_RESEARCH_AND_DEVELOPMENT_DEPARTMENT =
            "select e from Employee as e where e.department.name = 'Research and Development'" +
                    " order by e.salary, e.id";
    private static final String PRINT_FORMAT = "%s %s from %s - $%.2f%n";

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createQuery(GET_ALL_EMPLOYEES_IN_RESEARCH_AND_DEVELOPMENT_DEPARTMENT, Employee.class)
                .getResultList().forEach(e -> System.out.printf(PRINT_FORMAT,
                        e.getFirstName(), e.getLastName(), e.getDepartment().getName(), e.getSalary()));

        entityManager.getTransaction().commit();
        entityManager.close();


    }
}
