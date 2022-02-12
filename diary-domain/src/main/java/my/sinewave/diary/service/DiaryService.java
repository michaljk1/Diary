package my.sinewave.diary.service;

import lombok.RequiredArgsConstructor;
import my.sinewave.diary.DiaryRatings;
import my.sinewave.diary.Rating;
import my.sinewave.diary.parser.DiaryParser;
import my.sinewave.diary.repository.RatingRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryParser diaryParser;
    private final RatingRepository ratingRepository;

    public DiaryRatings getSummaryRatings() {
        List<Rating> ratingList = ratingRepository.getAllRatingsByDay();
        return DiaryRatings.from(ratingList);
    }

    public void processFile(String filename) throws IOException {
        DiaryRatings diaryRatings = diaryParser.parse(filename);
        ratingRepository.saveAll(diaryRatings);
    }
}
