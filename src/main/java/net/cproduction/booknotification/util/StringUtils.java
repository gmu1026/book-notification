package net.cproduction.booknotification.util;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtils {

    public static List<String> extractParenthesesContent(String text) {
        List<String> results = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\(([^)]+)\\)");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            results.add(matcher.group(1));
        }

        return results;
    }

    public static String removeParenthesesContent(String text) {
        return text.replaceAll("\\([^)]+\\)", "").trim();
    }

    public static String extractKoreanOnly(String text) {
        if (text == null) return "";
        StringBuilder koreanOnly = new StringBuilder();
        for (char c : text.toCharArray()) {
            if ((c >= '가' && c <= '힣') || (c >= 'ㄱ' && c <= 'ㅎ') ||
                    (c >= 'ㅏ' && c <= 'ㅣ') || c == ' ') {
                koreanOnly.append(c);
            }
        }
        return koreanOnly.toString().trim();
    }

    public static boolean ignoreSpaceEquals(String target, String source) {
        if (target == null && source == null) return true;
        if (target == null || source == null) return false;

        return target.replaceAll("\\s+", "").equals(source.replaceAll("\\s+", ""));
    }
}
