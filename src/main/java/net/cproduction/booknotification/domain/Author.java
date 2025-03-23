package net.cproduction.booknotification.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.cproduction.booknotification.util.StringUtils;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable
public class Author {
    @Column(name = "original_name", nullable = false)
    private String originalName;

    @Column(name = "korean_name", nullable = true)
    private String koreanName;

    private Author(String originalName, String koreanName) {
        this.originalName = originalName;
        this.koreanName = koreanName;
    }

    public static Author parse(String originalName) {
        String koreanName = StringUtils.extractKoreanOnly(originalName);
        if (koreanName.isEmpty()) {
            koreanName = null;
        }

        return new Author(originalName, koreanName);
    }
}
