package devtolife.weatherday.menu_activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.widget.TextView;

import devtolife.weatherday.BuildConfig;
import devtolife.weatherday.R;

public class SettingAboutAppActivity extends AppCompatActivity {
    SharedPreferences mSharedPref;
    TextView tv_version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        setTheme(mSharedPref.getInt("mytheme", 0));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_about_app);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        try {
            getSupportActionBar().setTitle("About Application");
        } catch (Exception e) {
        }

        String versionName = BuildConfig.VERSION_NAME;

        tv_version = findViewById(R.id.version_of_app);
        tv_version.setText("Version of APP: " + versionName);

    }
}
