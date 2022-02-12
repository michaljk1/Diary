package my.sinewave.diary.repository;


import my.sinewave.diary.DiaryRatings;
import my.sinewave.diary.Rating;

import java.util.List;

public interface RatingRepository {

    void saveAll(DiaryRatings ratings);

    List<Rating> getAllRatingsByDay();


}
