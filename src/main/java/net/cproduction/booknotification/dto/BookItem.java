package net.cproduction.booknotification.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.time.LocalDate;
import net.cproduction.booknotification.domain.Book;

public record BookItem(String title, String thumbnailUrl, String author,
                       Long discount, String publisher,
                       String isbn, @JsonFormat(shape = Shape.STRING, pattern = "yyyyMMdd") LocalDate pubdate) {
    public Book toEntity() {
        return Book.builder()
                .title(title)
                .thumbnailLink(thumbnailUrl)
                .author(author)
                .discount(discount)
                .publisher(publisher)
                .isbn(isbn)
                .publishedDate(pubdate)
                .build();
    }
}
