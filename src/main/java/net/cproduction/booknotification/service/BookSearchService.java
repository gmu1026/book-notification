package net.cproduction.booknotification.service;

public interface BookSearchService {
    void searchByAuthor(String author) throws InterruptedException;
    void searchByTitle(String title);
    void searchByPublisher(String publisher);
}
