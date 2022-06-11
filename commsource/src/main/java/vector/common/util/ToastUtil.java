package vector.common.util;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by vectorzeng on 2017/10/17.
 */

public class ToastUtil {
    public static void showToast(Context context, String msg){
        if(TextUtils.isEmpty(msg)|| context == null){
            return;
        }
        Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    public static void showToast(Context context, @StringRes int msgId){
        if(msgId == 0|| context == null){
            return;
        }
        String msg = context.getString(msgId);
        showToast(context, msg);
    }
}
