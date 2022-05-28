package vector.common.util;

import android.text.TextUtils;

public class VTextUtils {
    public static boolean isSomeOneEmpty(String... content) {
        if (content == null) {
            return true;
        }
        for (String s : content) {
            if (TextUtils.isEmpty(s)) {
                return true;
            }
        }
        return false;
    }
}
