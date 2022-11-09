package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "magic_wand")
@Entity
@Setter @Getter
@NoArgsConstructor
public class MagicLand {
    @Id
    @Column
    private Long id;

    @Column(length = 100)
    private String creator;

    @Column(name = "magic_wand_size")
    private Long magicWandCreator;

}
