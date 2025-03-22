package net.cproduction.booknotification.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.cproduction.booknotification.domain.SearchCondition;
import net.cproduction.booknotification.service.BookSearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/book")
@RestController
public class BookSearchController {
    private final BookSearchService bookSearchService;

    @GetMapping("/search")
    public void search(@RequestParam("query") String query, @RequestParam("condition") SearchCondition condition) {
        try {
            switch (condition) {
                case AUTHOR:
                    bookSearchService.searchByAuthor(query);
                    break;
                case TITLE:
                    bookSearchService.searchByTitle(query);
                    break;
                case PUBLISHER:
                    bookSearchService.searchByPublisher(query);
                    break;
            }
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
    }
}
