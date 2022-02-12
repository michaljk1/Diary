package my.sinewave.diary.parser;

import lombok.RequiredArgsConstructor;
import my.sinewave.diary.DiaryRatings;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class DiaryParserImpl implements DiaryParser {

    //TODO case sensitive
    private static final String RATING_PREFIX = "ocena: ";
    private static final Pattern pattern = Pattern.compile(RATING_PREFIX + "\\d+(\\.\\d)*");

    public DiaryRatings parse(String fileName) throws IOException {
        final Matcher matcher = pattern.matcher(getContent(fileName));
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

    private String getContent(String fileName) throws IOException {
        return Files.readString(Path.of(fileName));
    }

}

