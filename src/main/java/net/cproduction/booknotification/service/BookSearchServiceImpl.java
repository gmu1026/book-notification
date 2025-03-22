package net.cproduction.booknotification.service;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.cproduction.booknotification.domain.Book;
import net.cproduction.booknotification.domain.BookRepository;
import net.cproduction.booknotification.domain.SearchCondition;
import net.cproduction.booknotification.dto.BookItem;
import net.cproduction.booknotification.dto.BookSearchResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
public class BookSearchServiceImpl implements BookSearchService {
    private final BookRepository bookRepository;
    private final NaverBookApiClient naverBookApiClient;

    @Transactional
    @Override
    public void searchByAuthor(String author) {
        List<Book> books = search(author);
        saveBooks(books);
    }

    private Long getTotalResult(String query) {
        BookSearchResponse response = naverBookApiClient.searchBook(query, 1, 1);
        return response.total();
    }

    private List<Book> search(String query) {
        List<Book> resultBooks = new ArrayList<>();
        Long total = getTotalResult(query);
        int batchSize = 100;
        int batchCount = (int) Math.ceil(Math.min(total, 1000) / (double) batchSize);

        for (int searchCount = 0; searchCount < batchCount; searchCount++) {
            BookSearchResponse response = naverBookApiClient.searchBook(
                    query, batchSize, searchCount * batchSize + 1);

            resultBooks.addAll(response.items().stream().map(BookItem::toEntity).toList());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.warn("검색 중 인터럽트 발생, 일부 결과만 반환될 수 있습니다: {}", query, e);
                break; // 검색 중단하고 지금까지 모은 결과 반환
            }
        }
        return resultBooks;
    }

    private void saveBooks(List<Book> books) {
        bookRepository.saveAll(books);
    }

    @Override
    public void searchByTitle(String title) {
        BookSearchResponse response = naverBookApiClient.searchBook(title, 100, 1);
        List<Book> books = response.items().stream().map(BookItem::toEntity).toList();
        saveBooks(books);
    }

    @Override
    public void searchByPublisher(String publisher) {
        BookSearchResponse response = naverBookApiClient.searchBook(publisher, 100, 1);
        List<Book> books = response.items().stream().map(BookItem::toEntity).toList();
        saveBooks(books);
    }
}
