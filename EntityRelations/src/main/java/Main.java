import entities.Bike;
import entities.Car;
import entities.Plane;
import entities.Vehicle;
import hasEntities.Article;
import hasEntities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("relations");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

//        Vehicle car = new Car("Ford", "Petrol", 5);
//        Vehicle bike = new Bike();
//        Vehicle plane = new Plane("Wizz Air", 200);
//
//        entityManager.persist(car);
//        entityManager.persist(bike);
//        entityManager.persist(plane);


        Article article = new Article("akdkaad");
        User user = new User("Ivan");
        user.addArticle(article);
        article.setAuthor(user);
        entityManager.persist(user);
        entityManager.persist(article);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
