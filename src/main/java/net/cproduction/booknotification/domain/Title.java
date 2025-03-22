package net.cproduction.booknotification.domain;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.cproduction.booknotification.util.StringUtils;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Title {
    @Column(name = "original_title", nullable = false)
    @Getter
    private String originalTitle;

    @Column(name = "korean_title", nullable = true)
    @Getter
    private String koreanTitle;

    @ElementCollection
    @CollectionTable(
            name = "book_edition_info",
            joinColumns = @JoinColumn(name = "book_id")
    )
    @Column(name = "edition_info")
    private List<String> editionInfo = new ArrayList<>();

    private Title(String originalTitle, String koreanTitle, List<String> editionInfo) {
        this.originalTitle = originalTitle;
        this.koreanTitle = koreanTitle;
        this.editionInfo.addAll(editionInfo);
    }

    public static Title parse(String fullTitle) {
        String rawMainTitle = StringUtils.removeParenthesesContent(fullTitle);
        String koreanTitle = StringUtils.extractKoreanOnly(rawMainTitle);

        if (koreanTitle.isEmpty()) {
            koreanTitle = null;
        }

        Set<String> uniqueEditions = new LinkedHashSet<>(
                StringUtils.extractParenthesesContent(fullTitle));

        List<String> editionInfo = new ArrayList<>(uniqueEditions);

        return new Title(rawMainTitle, koreanTitle, editionInfo);
    }
}
