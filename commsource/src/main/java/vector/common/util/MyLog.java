package vector.common.util;

import android.util.Log;

import vector.common.BuildConfig;

public class MyLog {
    private static final boolean enable = BuildConfig.DEBUG;

    public static void d(String tag, String msg){
        if (!enable){
            return;
        }
        if (tag == null){
            tag = "";
        }
        Log.d(tag, msg);
    }

    public static void i(String tag, String msg){
        if (!enable){
            return;
        }
        if (tag == null){
            tag = "";
        }
        Log.i(tag, msg);
    }

    public static void w(String tag, String msg){
        if (!enable){
            return;
        }
        if (tag == null){
            tag = "";
        }
        Log.w(tag, msg);
    }

    public static void e(String tag, String msg){
        if (!enable){
            return;
        }
        if (tag == null){
            tag = "";
        }
        Log.e(tag, msg);
    }
}
