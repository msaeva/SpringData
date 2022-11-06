package entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("relations");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Vehicle car = new Car("Ford", "Petrol", 5);
        Vehicle bike = new Bike();
        Vehicle plane = new Plane("Wizz Air", 200);

        entityManager.persist(car);
        entityManager.persist(bike);
        entityManager.persist(plane);

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
