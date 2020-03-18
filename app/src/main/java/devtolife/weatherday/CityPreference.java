package devtolife.weatherday;

import android.app.Activity;
import android.content.SharedPreferences;

public class CityPreference {

    private SharedPreferences prefs;

    public CityPreference(Activity activity) {
        prefs = activity.getPreferences(Activity.MODE_PRIVATE);
    }

    // If the user has not chosen a city yet, return
    // Kiev as the default city
    public String getCity() {
        return prefs.getString("city", "Kiev, UA");
    }

    public void setCity(String city) {
        prefs.edit().putString("city", city).apply();
    }

}
