package entities;

import orm.annotations.Column;
import orm.annotations.Entity;
import orm.annotations.Id;

import java.time.LocalDate;

@Entity(name = "users") // този клас е таблицата users в базата
public class User {
    @Id
    private long id;
    @Column(name = "user_name")
    private String name;
    @Column(name = "age")
    private int age;
    @Column(name = "registration_date")
    private LocalDate registration;

    public User() {
    }

    public User(String name, int age, LocalDate registration) {
        this.name = name;
        this.age = age;
        this.registration = registration;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public LocalDate getRegistration() {
        return registration;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", registration=" + registration +
                '}';
    }
}
