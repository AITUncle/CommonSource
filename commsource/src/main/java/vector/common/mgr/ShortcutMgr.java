package vector.common.mgr;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.os.Build;

import vector.common.R;
import vector.common.util.BitmapUtil;

/**
 * Created by vectorzeng on 2018/2/28.
 */

public class ShortcutMgr {
    private static final ShortcutMgr ourInstance = new ShortcutMgr();

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

    public void installShortcut(Intent intent, String name, Bitmap icon, Context context){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ShortcutManager shortcutManager =
                    context.getSystemService(ShortcutManager.class);
            if (shortcutManager != null && shortcutManager.isRequestPinShortcutSupported()) {
                ShortcutInfo pinShortcutInfo = new ShortcutInfo.Builder(context, name)
                        .setIcon(Icon.createWithBitmap(icon))
                        .setShortLabel(name)
                        .setIntent(intent)
                        .build();
                shortcutManager.requestPinShortcut(pinShortcutInfo, null);
            }
            return;
        }
        Intent installIntent = createInstallIntent(intent, name);
        installIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON, icon);
        context.sendBroadcast(installIntent);
    }

    public void installShortcut(Intent intent, String name, int icon, Context context){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ShortcutManager shortcutManager =
                    context.getSystemService(ShortcutManager.class);
            if (shortcutManager != null && shortcutManager.isRequestPinShortcutSupported()) {
                ShortcutInfo pinShortcutInfo = new ShortcutInfo.Builder(context, name)
                        .setIcon(Icon.createWithResource(context, icon))
                        .setShortLabel(name)
                        .setIntent(intent)
                        .build();
                shortcutManager.requestPinShortcut(pinShortcutInfo, null);
            }
            return;
        }

        Intent installIntent = createInstallIntent(intent, name);
        if(icon > 0) {
            Intent.ShortcutIconResource resIcon = Intent.ShortcutIconResource.fromContext(context, icon);
            installIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, resIcon);
        }
        context.sendBroadcast(installIntent);
    }
}
