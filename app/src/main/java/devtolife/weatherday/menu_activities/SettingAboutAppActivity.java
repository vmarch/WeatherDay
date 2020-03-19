package devtolife.weatherday.menu_activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import devtolife.weatherday.BuildConfig;
import devtolife.weatherday.R;

public class SettingAboutAppActivity extends AppCompatActivity {
    //    SharedPreferences mSharedPref;
    private TextView tv_version;
    private Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        mSharedPref = PreferenceManager.getDefaultSharedPreferences(this);
//        setTheme(mSharedPref.getInt("mytheme", 0));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_about_app);

        myToolbar = findViewById(R.id.my_toolbar_settings);
        setupToolbar();

        String versionName = BuildConfig.VERSION_NAME;

        tv_version = findViewById(R.id.version_of_app);
        tv_version.setText("Version of APP: " + versionName);

    }

    private void setupToolbar() {
        setSupportActionBar(myToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            myToolbar.setNavigationIcon(R.drawable.baseline_arrow_back_black_24);
            myToolbar.setNavigationContentDescription(getResources().getString(R.string.context_description_arrow_back));
            myToolbar.setTitle("About App");
            myToolbar.setNavigationOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
