import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class GetEmployeeWithProject {
    private static final String GET_EMPLOYEE_BY_ID = "select e from Employee e where e.id = :id";

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        int employeeId = new Scanner(System.in).nextInt();

        Employee employee = entityManager.createQuery(GET_EMPLOYEE_BY_ID, Employee.class)
                .setParameter("id", employeeId)
                .getSingleResult();

        System.out.printf("%s %s - %s%n", employee.getFirstName(), employee.getLastName(), employee.getJobTitle());

        System.out.println(employee.getProjects().stream()
                .sorted(Comparator.comparing(Project::getName))
                .map(Project::getName)
                .collect(Collectors.joining(System.lineSeparator())));

        entityManager.close();
    }
}
