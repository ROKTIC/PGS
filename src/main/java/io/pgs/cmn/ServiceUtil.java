package io.pgs.cmn;

import org.springframework.util.StringUtils;

public final class ServiceUtil {

    static final public int DUPLICATE_COUNT = 2;
    static final private String DASH = "-";
    static final private String COMMA = ",";
    static final private String COLON = ":";

    static final public String deleteDash(String s) {
        return StringUtils.deleteAny(s, DASH);
    }

    static final public String deleteComma(String s) {
        return StringUtils.deleteAny(s, COMMA);
    }

    static final public String deleteColon(String s) {
        return StringUtils.deleteAny(s, COLON);
    }

    static final public String trim(String s) {
        return StringUtils.trimAllWhitespace(s);
    }

    static final public String deleteDateformat(String s) {
        return deleteColon(deleteDash(trim(s)));
    }


}
