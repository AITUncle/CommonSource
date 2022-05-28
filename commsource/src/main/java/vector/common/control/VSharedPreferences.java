package vector.common.control;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;


public class VSharedPreferences {
    protected static Application app;

    public static void init(@NonNull Application application) {
        app = application;
    }

    private final SharedPreferences sharedPreferences;

    protected VSharedPreferences(@NonNull Context c, @NonNull String file, int mode) {
        sharedPreferences = c.getSharedPreferences(file, mode);
    }

    public boolean putBoolean(String key, boolean value) {
        return sharedPreferences.edit().putBoolean(key, value).commit();
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }
}
