package my.sinewave.diary.endpoint;

import lombok.RequiredArgsConstructor;
import my.sinewave.diary.DiaryRatings;
import my.sinewave.diary.service.DiaryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/diary")
@RequiredArgsConstructor
public class DiaryEndpoint {

    private final DiaryService diaryService;

    @GetMapping
    public DiaryResponse getRatings() {
        DiaryRatings diaryRatings = diaryService.getSummaryRatings();
        return DiaryResponse.from(diaryRatings);
    }

    @PostMapping("/{filename}")
    public void addRatings(@PathVariable String filename) throws IOException {
        diaryService.processFile(filename);
    }


}
