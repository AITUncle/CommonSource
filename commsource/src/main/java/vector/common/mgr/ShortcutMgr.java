package vector.common.mgr;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Build;
import androidx.annotation.NonNull;

import vector.common.util.MyLog;


/**
 * Created by vectorzeng on 2018/2/28.
 */

public class ShortcutMgr {
    private static final String TG = "ShortcutMgr";
    private static final ShortcutMgr ourInstance = new ShortcutMgr();

    private int widthDefaultIcon = 0;
    private int heightDefaultIcon = 0;

    public static ShortcutMgr getInstance() {
        return ourInstance;
    }

    private ShortcutMgr() {
    }

    private Intent createInstallIntent(Intent intent, String name){
        Intent installIntent = new Intent();
        installIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        installIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent);
        installIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);
        return installIntent;
    }

    public boolean installShortcut(Intent intent, String name, Bitmap icon, Context context){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ShortcutManager shortcutManager =
                    context.getSystemService(ShortcutManager.class);
            if (shortcutManager != null && shortcutManager.isRequestPinShortcutSupported()) {
                ShortcutInfo pinShortcutInfo = new ShortcutInfo.Builder(context, name)
                        .setIcon(Icon.createWithBitmap(icon))
                        .setShortLabel(name)
                        .setIntent(intent)
                        .build();
                return shortcutManager.requestPinShortcut(pinShortcutInfo, null);
            }
            return false;
        }
        Intent installIntent = createInstallIntent(intent, name);
        installIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON, icon);
        context.sendBroadcast(installIntent);
        return true;
    }

    public boolean installShortcut(Intent intent, String name, int icon, Context context){
        MyLog.i(TG, "installShortcut name=" + name + ", intent=" + intent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ShortcutManager shortcutManager =
                    context.getSystemService(ShortcutManager.class);
            if (shortcutManager != null && shortcutManager.isRequestPinShortcutSupported()) {
                ShortcutInfo pinShortcutInfo = new ShortcutInfo.Builder(context, name)
                        .setIcon(Icon.createWithResource(context, icon))
                        .setShortLabel(name)
                        .setIntent(intent)
                        .build();
                return shortcutManager.requestPinShortcut(pinShortcutInfo, null);
            }
            return false;
        }

        Intent installIntent = createInstallIntent(intent, name);
        if(icon > 0) {
            Intent.ShortcutIconResource resIcon = Intent.ShortcutIconResource.fromContext(context, icon);
            installIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, resIcon);
        }
        context.sendBroadcast(installIntent);
        return true;
    }

    private boolean initDefaultIconSize(@NonNull Context context) {
        if (widthDefaultIcon > 0 && heightDefaultIcon > 0){
            return true;
        }
        PackageManager pm = context.getPackageManager();
        if (pm == null) {
            return false;
        }
        ApplicationInfo info = context.getApplicationInfo();
        if (info == null) {
            return false;
        }
        Drawable drawable = info.loadIcon(pm);
        if (drawable == null) {
            return false;
        }
        widthDefaultIcon = drawable.getIntrinsicWidth();
        heightDefaultIcon = drawable.getIntrinsicHeight();
        return true;
    }

    public int getWidthDefaultIcon(@NonNull Context context) {
        if (initDefaultIconSize(context)){
            return widthDefaultIcon;
        }
        return 192;
    }

    public int getHeightDefaultIcon(@NonNull Context context) {
        if (initDefaultIconSize(context)){
            return heightDefaultIcon;
        }
        return 192;
    }
}
