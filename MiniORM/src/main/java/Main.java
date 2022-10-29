import entities.User;
import orm.Connector;
import orm.EntityManager;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        Connector.createConnection("root", "admin", "soft_uni");

        EntityManager<User> userManager = new EntityManager<>(Connector.getConnection());

//        User user = new User("First", 28, LocalDate.now());
//
//        userManager.persist(user); // user да стигне до базата

        User first = userManager.findFirst(User.class);
        System.out.println(first.getId() + " " + first.getName() + " " + first.getAge());

        User second = userManager.findFirst(User.class, "id = 2");
        System.out.println(second.getAge() + " " + second.getName());

        userManager.find(User.class,"age > 18 AND registration_date > 2022-06-06");
    }
}
