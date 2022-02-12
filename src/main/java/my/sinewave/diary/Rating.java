package my.sinewave.diary;

import java.time.LocalDate;

public record Rating(LocalDate day, Float value) {

    public static Rating from(LocalDate day, Float value) {
        return new Rating(day, value);
    }
}
