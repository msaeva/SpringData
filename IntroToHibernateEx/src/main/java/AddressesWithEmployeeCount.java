import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class AddressesWithEmployeeCount {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        List<Address> resultList = entityManager.createQuery("SELECT a from Address a order by a.employees.size desc", Address.class)
                .setMaxResults(10)
                .getResultList();

        for (Address address : resultList) {
            System.out.println(address.toString());
        }
        System.out.println(resultList.size());

        entityManager.close();
    }
}
