package my.sinewave.diary;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

@UtilityClass
public class AmplitudeStatisticsFactory {

    public static AmplitudeStatistics calculateStatistics(List<Rating> ratings) {
        List<Rating> ratingAmplitude = IntStream.iterate(0, i -> i + 1).limit(ratings.size() - 1).mapToObj(
                index -> {
                    Rating first = ratings.get(index);
                    Rating second = ratings.get(index + 1);
                    return Rating.from(first.day(), second.value() - first.value());
                }
        ).toList();
        Rating negative = ratingAmplitude.stream().min(Comparator.comparing(Rating::value)).orElse(Rating.from(LocalDate.now(), 0f));
        Rating positive = ratingAmplitude.stream().max(Comparator.comparing(Rating::value)).orElse(Rating.from(LocalDate.now(), 0f));

        return new AmplitudeStatistics(createAmplitude(negative), createAmplitude(positive));
    }

    private static Amplitude createAmplitude(Rating ratingAmplitude) {
        return new Amplitude(ratingAmplitude.day(), ratingAmplitude.day().plus(Period.ofDays(1)), ratingAmplitude.value());
    }


}
