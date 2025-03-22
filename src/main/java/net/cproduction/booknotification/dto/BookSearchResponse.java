package net.cproduction.booknotification.dto;

import java.util.List;

public record BookSearchResponse(String lastBuildDate, Long total,
                                 Long start, Long display,
                                 List<BookItem> items) {
}
