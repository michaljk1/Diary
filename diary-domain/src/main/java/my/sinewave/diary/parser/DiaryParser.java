package my.sinewave.diary.parser;

import my.sinewave.diary.DiaryRatings;

import java.io.IOException;

public interface DiaryParser {

    DiaryRatings parse(String fileName) throws IOException;

}

