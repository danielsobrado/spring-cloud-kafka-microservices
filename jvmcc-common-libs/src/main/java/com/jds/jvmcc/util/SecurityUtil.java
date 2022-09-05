package com.jds.jvmcc.util;

import org.apache.commons.text.StringEscapeUtils;

/**
 * @author J. Daniel Sobrado
 * @version 1.1
 * @since 2022-08-08
 */
public class SecurityUtil {

    private SecurityUtil() {
    }

    /**
     * Remove escape characters like Html/Js scripts from input if present
     * @param str Input string
     * @return sanitize string
     */
    public static String cleanIt(String str) {
        return StringEscapeUtils.escapeHtml4(str);
    }
}