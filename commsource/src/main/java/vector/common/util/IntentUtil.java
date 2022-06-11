package vector.common.util;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import androidx.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import java.util.Arrays;

/**
 * Created by vectorzeng on 2018/2/28.
 */

public class IntentUtil {
    private static final String TG = "IntentUtil";

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

    /**
     * 应用内页
     */
    public static boolean startAppPage(@NonNull Context context, String pkgName, String action){
        if(TextUtils.isEmpty(pkgName)){
            return false;
        }
        Intent intent = new Intent();
        intent.setPackage(pkgName);
        if (!TextUtils.isEmpty(action)) {
            intent.setAction(action);
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        MyLog.i(TG, "startAppPage pkgName=" + pkgName + ", action=" + action);
        return true;
    }

    /**
     * TODO zengliang 是否需要try caught？
     */
    public static boolean startCallNumberDial(@NonNull Activity activity, @NonNull String phoneNumber) {
        if (TextUtils.isEmpty(phoneNumber)) {
            return false;
        }
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode(phoneNumber)));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        return true;
    }

    public static String activityInfoArrayToString(ActivityInfo[] array){
        if (array == null){
            return "null";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (ActivityInfo info : array){
            stringBuilder.append(info.name  + "->" + info.exported + "\n");
        }
        return stringBuilder.toString();
    }

    public static boolean startActivity(@NonNull Context context, String pkg, String className) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(pkg, className));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
