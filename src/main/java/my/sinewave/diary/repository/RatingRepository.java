package my.sinewave.diary.repository;


import my.sinewave.diary.DiaryRatings;
import my.sinewave.diary.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.*;

import static java.util.stream.Collectors.*;

public interface RatingRepository extends JpaRepository<RatingEntity, LocalDate> {

    default void saveAll(DiaryRatings ratings) {
        Set<RatingEntity> ratingEntities = ratings.ratings()
                .stream().map(rating -> new RatingEntity(rating.day(), rating.value())).collect(toSet());
        this.saveAll(ratingEntities);

    }

    default List<Rating> getAllRatingsByDay() {
        return findAllByOrderByDayAsc().stream().map(entity -> Rating.from(entity.getDay(), entity.getRating())).collect(toList());
    }


    List<RatingEntity> findAllByOrderByDayAsc();


}
