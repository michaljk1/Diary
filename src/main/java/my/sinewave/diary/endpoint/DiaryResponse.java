package my.sinewave.diary.endpoint;

import my.sinewave.diary.AmplitudeStatistics;
import my.sinewave.diary.DiaryRatings;
import my.sinewave.diary.Rating;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;

public record DiaryResponse(List<LocalDate> labels, List<Float> data, String average, AmplitudeStatistics amplitudeStatistics) {

    public static DiaryResponse from(DiaryRatings diaryRatings) {
        var labels = diaryRatings.ratings().stream().map(Rating::day).toList();
        var data = diaryRatings.ratings().stream().map(Rating::value).toList();
        var average = new DecimalFormat("#.##").format(diaryRatings.average());
        var amplitudeStatistics = diaryRatings.amplitudeStatistics();

        return new DiaryResponse(labels, data, average, amplitudeStatistics);
    }

}
