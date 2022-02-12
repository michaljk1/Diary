package my.sinewave.diary.parser;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import my.sinewave.diary.DiaryRatings;
import my.sinewave.diary.parser.feign.GoogleDriveFeignClient;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
@Log4j2
public class DiaryParserImpl implements DiaryParser {

    //TODO case sensitive
    private static final String RATING_PREFIX = "ocena: ";
    private static final Pattern pattern = Pattern.compile(RATING_PREFIX + "\\d+(\\.\\d)*");
    private final GoogleDriveFeignClient googleDriveClient;

    public DiaryRatings parse(String fileName) {
        final String content = googleDriveClient.getContent(fileName).content();
        final Matcher matcher = pattern.matcher(content);
        Map<LocalDate, Float> ratingByDay = new TreeMap<>();
        final int month = getMonth(fileName);
        int counter = 1;
        while(matcher.find()) {
            ratingByDay.put(LocalDate.of(LocalDate.now().getYear(), month, counter), Float.valueOf(matcher.group().split(RATING_PREFIX)[1]));
            counter++;
        }
        return DiaryRatings.from(ratingByDay);
    }

    private int getMonth(String fileName) {
        return Integer.parseInt(fileName.split(".txt")[0]);
    }


}

