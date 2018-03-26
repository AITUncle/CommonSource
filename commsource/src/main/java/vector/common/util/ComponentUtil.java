package vector.common.util;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;



/**
 * Created by vectorzeng on 2018/1/17.
 */

public class ComponentUtil {


    public static int getScreenWidth(Context context){
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static void touchPressedAlpha(final float pressedAlpha, final float normalAlpah, View ...views){
        for(View v:views) {
            v.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            v.setAlpha(pressedAlpha);
                            break;
                        case MotionEvent.ACTION_UP:
                        case MotionEvent.ACTION_CANCEL:
                            v.setAlpha(normalAlpah);
                            break;
                    }
                    return false;
                }
            });
        }
    }

}
