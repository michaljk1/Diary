package my.sinewave.diary.repository;


import lombok.RequiredArgsConstructor;
import my.sinewave.diary.DiaryRatings;
import my.sinewave.diary.Rating;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@Repository
@RequiredArgsConstructor
public class RatingRepositoryImpl implements RatingRepository {

    private final RatingRepositoryDao ratingRepositoryDao;

    @Override
    public void saveAll(DiaryRatings ratings) {
        Set<RatingEntity> ratingEntities = ratings.ratings()
                .stream().map(rating -> new RatingEntity(rating.day(), rating.value())).collect(toSet());
        ratingRepositoryDao.saveAll(ratingEntities);

    }

    public List<Rating> getAllRatingsByDay() {
        return ratingRepositoryDao.findAllByOrderByDayAsc().stream().map(entity -> Rating.from(entity.getDay(), entity.getRating())).collect(toList());
    }


}
