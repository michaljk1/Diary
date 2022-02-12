package my.sinewave.config;

import my.sinewave.diary.parser.feign.GoogleDriveFeignClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackageClasses = GoogleDriveFeignClient.class)
public class FeignConfig {
}
