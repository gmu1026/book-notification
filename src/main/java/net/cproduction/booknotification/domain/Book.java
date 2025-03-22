package net.cproduction.booknotification.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @Column(nullable = false)
    private Title title;

    @Embedded
    @Column(nullable = false)
    private Author author;

    @Column(nullable = false)
    private String publisher;

    @Column(nullable = false)
    private String isbn;

    @Column
    private Long discount;

    @Column(nullable = true)
    private LocalDate publishedDate;

    @Column(nullable = true)
    private String thumbnailLink;

    @Builder
    public Book(String title, String author, String publisher, String isbn, Long discount,
                LocalDate publishedDate, String thumbnailLink) {
        this.title = Title.parse(title);
        this.author = Author.parse(author);
        this.publisher = publisher;
        this.isbn = isbn;
        this.discount = discount;
        this.publishedDate = publishedDate;
        this.thumbnailLink = thumbnailLink;
    }
}
