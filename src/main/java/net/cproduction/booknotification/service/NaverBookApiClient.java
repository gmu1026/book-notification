package net.cproduction.booknotification.service;

import net.cproduction.booknotification.config.FeignConfig;
import net.cproduction.booknotification.dto.BookSearchResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "naverBookService",
        url = "https://openapi.naver.com/v1/search",
        configuration = FeignConfig.class)
public interface NaverBookApiClient {
    @GetMapping("/book.json")
    BookSearchResponse searchBook(
            @RequestParam("query") String query,
            @RequestParam(value = "display") int display,
            @RequestParam(value = "start") int start);
}
