package my.sinewave.diary.parser.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "googleDrive", url = "${diary.google-drive.content}")
public interface GoogleDriveFeignClient {

    @GetMapping(path = "/{filename}")
    ContentResponse getContent(@PathVariable("filename") String filename);

}
