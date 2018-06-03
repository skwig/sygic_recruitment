package sk.brecka.sygicrecruitment.util;

import android.content.Context;
import android.support.v4.widget.CircularProgressDrawable;

public class ViewUtils {
    public static CircularProgressDrawable getEmployeeAvatarPlaceholder(Context context) {
        final CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(context);

        circularProgressDrawable.setStrokeWidth(5);
        circularProgressDrawable.setCenterRadius(30f);
        circularProgressDrawable.start();

        return circularProgressDrawable;
    }
}
