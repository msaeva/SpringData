import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FindLatest10Projects {
    private static final String GET_ALL_PROJECTS = "select p from Project p order by p.name";

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.createQuery(GET_ALL_PROJECTS, Project.class)
                .setMaxResults(10).getResultList()
                .forEach(System.out::println);

        entityManager.close();
    }
}
