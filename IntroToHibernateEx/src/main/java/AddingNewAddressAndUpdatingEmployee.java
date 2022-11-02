import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class AddingNewAddressAndUpdatingEmployee {
    private static final String ADD_NEW_ADDRESS_IN_SOFIA =
            "UPDATE Employee e set e.address = :newAddress where e.lastName = :ln";

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("soft_uni");

        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        final String lastName = new Scanner(System.in).nextLine();

        Address address = new Address();
        address.setText("Vitoshka 15");

        entityManager.persist(address);

        int count = entityManager.createQuery("UPDATE Employee e set e.address = :newAddress where e.lastName = :ln")
                .setParameter("newAddress", address)
                .setParameter("ln", lastName)
                .executeUpdate();

        if (count > 0) {
            entityManager.getTransaction().commit();
        } else {
            entityManager.getTransaction().rollback();
        }

        entityManager.close();

    }
}
