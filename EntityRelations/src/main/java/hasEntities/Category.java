package hasEntities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToMany(targetEntity = Article.class, mappedBy = "categories")
    private Set<Article> articleSet;

    public Category(String name) {
        this.name = name;
    }

    public Category() {

    }
}
