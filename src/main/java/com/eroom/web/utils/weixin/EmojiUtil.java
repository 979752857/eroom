package com.eroom.web.utils.weixin;

public class EmojiUtil {

    private static boolean containsEmoji(String source) {
        if (source == null || "".equals(source.trim()))
            return false;

        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (!isNotEmojiCharacter(codePoint)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNotEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD)
                || ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
                || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
                || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }

    public static String filterEmoji(String source) {
        if (!containsEmoji(source))
            return source;

        StringBuffer sb = null;
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (isNotEmojiCharacter(codePoint)) {
                if (sb == null)
                    sb = new StringBuffer();
                sb.append(codePoint);
            }
        }

        if (sb == null) {
            return null;
        } else {
            if (sb.length() == len) {
                sb = null;
                return source;
            } else {
                return sb.toString();
            }
        }
    }

}
