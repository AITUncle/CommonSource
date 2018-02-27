package vector.common.util;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;

/**
 * Created by vectorzeng on 2018/2/28.
 */

public class IntentUtil {

    /**
     * 应用详情页
     * @param context
     * @param pkgName
     * @return
     */
    public static boolean startAppInfoAct(@NonNull Context context, @NonNull String pkgName){
        if(TextUtils.isEmpty(pkgName)){
            return false;
        }
        Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + pkgName));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        return true;
    }
}
