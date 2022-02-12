package my.sinewave.diary.repository;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "ratings")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RatingEntity {

    @Id
    @NotNull
    @Column(name = "day")
    private LocalDate day;

    @NotNull
    @Column(name = "rating")
    private Float rating;

}
