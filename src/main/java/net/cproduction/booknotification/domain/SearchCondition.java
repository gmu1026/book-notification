package net.cproduction.booknotification.domain;

import java.util.function.BiFunction;
import net.cproduction.booknotification.util.StringUtils;

public enum SearchCondition {
    AUTHOR((book, query) -> StringUtils.ignoreSpaceEquals(book.getAuthor().getKoreanName(), query)),
    TITLE((book, query) -> StringUtils.ignoreSpaceEquals(book.getTitle().getOriginalTitle(), query)),
    ISBN((book, query) -> book.getIsbn().equals(query)),
    PUBLISHER((book, query) -> StringUtils.ignoreSpaceEquals(book.getPublisher(), query)),;

    private final BiFunction<Book, String, Boolean> matcher;

    SearchCondition(BiFunction<Book, String, Boolean> matcher) {
        this.matcher = matcher;
    }

    public boolean matches(Book book, String query) {
        return matcher.apply(book, query);
    }
}
