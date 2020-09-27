package devtolife.weatherday.menu_activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Locale;

import devtolife.weatherday.MyPreference;
import devtolife.weatherday.R;
import devtolife.weatherday.WeatherActivity;

public class SettingsActivity extends AppCompatActivity {
    MyPreference myPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        mSharedPref = PreferenceManager.getDefaultSharedPreferences(this);
//        setTheme(mSharedPref.getInt("mytheme", 0));

        myPreference = new MyPreference(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);

        Toolbar myToolbar = findViewById(R.id.my_settings_toolbar);
        setSupportActionBar(myToolbar);

        myToolbar.setNavigationIcon(R.drawable.outline_arrow_back_black_24);
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startWithRefresh();
            }
        });

        try {
            getSupportActionBar().setTitle("Settings");
        } catch (Exception e) {
        }

        Spinner spinnerLanguage = findViewById(R.id.spinner_languages);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.languages_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLanguage.setAdapter(adapter);


        spinnerLanguage.setSelection(myPreference.getLanguage());

        spinnerLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                myPreference.setLanguage(pos);

                String languageToLoad;
                if (pos == 0) {
                    languageToLoad = "en";
                } else if (pos == 1) {
                    languageToLoad = "de";
                } else if (pos == 2) {
                    languageToLoad = "uk";
                } else if (pos == 3) {
                    languageToLoad = "ru";
                } else {
                    languageToLoad = "en";
                }

                changeLocale(languageToLoad);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });
    }

    private void startWithRefresh() {
        Intent refresh = new Intent(SettingsActivity.this, WeatherActivity.class);
        refresh.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(refresh);
        finish();
    }

    private void changeLocale(String newLanguage) {

        Locale locale = new Locale(newLanguage);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());

    }
}
