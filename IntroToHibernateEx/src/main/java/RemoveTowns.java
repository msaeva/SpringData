import entities.Address;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;

public class RemoveTowns {
    private static final String GET_ADDRESSES_TO_DELETE = "select a from Address a where a.town.name = :tn";
    private static final String GET_TOWN = "select t from Town t where t.name = :tn";

    public static void main(String[] args) {

        String townToDelete = new Scanner(System.in).nextLine();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        List<Address> addresses = entityManager.createQuery(GET_ADDRESSES_TO_DELETE, Address.class)
                .setParameter("tn", townToDelete)
                .getResultList();

        if (addresses.size() == 0) {
            System.out.println("No such town");
            return;
        }

        addresses.forEach(a -> {
            a.getEmployees().forEach(e -> e.setAddress(null));
            entityManager.remove(a);
        });

        Town townFromDBToDelete = entityManager.createQuery(GET_TOWN, Town.class)
                .setParameter("tn", townToDelete).getSingleResult();

        entityManager.remove(townFromDBToDelete);

        System.out.printf("%d address in %s deleted", addresses.size(), townToDelete);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
