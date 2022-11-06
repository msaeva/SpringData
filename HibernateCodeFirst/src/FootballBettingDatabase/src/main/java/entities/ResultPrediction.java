package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@Table
@Entity
public class ResultPrediction extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private ResultPredictionValues resultPredictionValues;

}
