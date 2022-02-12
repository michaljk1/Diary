package my.sinewave.diary;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public record Rating(@NotNull LocalDate day, @NotNull Float value) {

    public static Rating from(LocalDate day, Float value) {
        return new Rating(day, value);
    }
}
