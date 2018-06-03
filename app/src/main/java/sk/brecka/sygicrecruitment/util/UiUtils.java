package sk.brecka.sygicrecruitment.util;

import android.content.res.Resources;
import android.util.TypedValue;

public class UiUtils {
    public static float dpToPixels(Resources res, float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, res.getDisplayMetrics());
    }
}
