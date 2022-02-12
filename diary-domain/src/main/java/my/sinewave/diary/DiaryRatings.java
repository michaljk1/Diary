package my.sinewave.diary;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
//TODO walidacja
public record DiaryRatings(List<Rating> ratings, Double average, AmplitudeStatistics amplitudeStatistics) {


    public static DiaryRatings from(Map<LocalDate, Float> ratings) {
        List<Rating> ratingList = ratings.entrySet().stream().map(entry -> Rating.from(entry.getKey(), entry.getValue())).collect(toList());
        return from(ratingList);

    }

    public static DiaryRatings from(List<Rating> ratings) {
        Double average = ratings.stream().mapToDouble(Rating::value).average().orElse(0.00);
        AmplitudeStatistics amplitudeStatistics = AmplitudeStatisticsFactory.calculateStatistics(ratings);
        return new DiaryRatings(ratings, average, amplitudeStatistics);
    }

}
