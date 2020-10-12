package devtolife.weatherday;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class MyPreference {

    private SharedPreferences prefs;

    public MyPreference(Activity activity) {
        prefs = activity.getPreferences(Activity.MODE_PRIVATE);
    }

// If the user has not chosen a city yet, return
    // Kiev as the default city

    // English as the default language

    public String getCity() {
        return prefs.getString("city", "Kiev, UA");
    }
    public int getLanguage() {
        return prefs.getInt("language", 0);
    }

    public void setCity(String city) {
        prefs.edit().putString("city", city).apply();
    }
    public void setLanguage(int id) {
        prefs.edit().putInt("language", id).apply();
    }
}
