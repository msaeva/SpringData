
import org.example.Bike;
import org.example.Car;
import org.example.Truck;
import org.example.Vehicle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("relations2");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Vehicle bike = new Bike();
        Vehicle car = new Car();

//        entityManager.persist(bike);
//        entityManager.persist(car);

        Vehicle truck = new Truck();

        entityManager.persist(truck);
        entityManager.persist(car);


        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
