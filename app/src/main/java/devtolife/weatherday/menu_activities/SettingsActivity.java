package devtolife.weatherday.menu_activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import devtolife.weatherday.LocaleHelper;
import devtolife.weatherday.MyPreference;
import devtolife.weatherday.R;
import devtolife.weatherday.WeatherActivity;

public class SettingsActivity extends AppCompatActivity {
    private MyPreference myPreference;
    private TextView textViewLanguage;
    private String firstLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        LocaleHelper.onAttach(getBaseContext());
        firstLanguage = LocaleHelper.getLanguage(getBaseContext());

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

        getSupportActionBar().setTitle(getString(R.string.action_settings));

        textViewLanguage = findViewById(R.id.field_name_languages);
        textViewLanguage.setText(getString(R.string.settings_row_name_language));

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
                    changeViewLanguage();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void startWithRefresh() {
        if (!firstLanguage.equals(LocaleHelper.getLanguage(getBaseContext()))) {
            Intent refresh = new Intent(SettingsActivity.this, WeatherActivity.class);
            refresh.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(refresh);
        }
        finish();
    }

    private void changeLocale(String newLanguage) {
        LocaleHelper.setLanguage(this, newLanguage);
    }

    private Context setCurrentLocale(Context ctx) {
        return LocaleHelper.onAttach(ctx);
    }

    private void changeViewLanguage() {
        getSupportActionBar().setTitle(getString(R.string.action_settings));
        textViewLanguage.setText(getString(R.string.settings_row_name_language));
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(setCurrentLocale(base));
        applyOverrideConfiguration(new Configuration());
    }

    @Override
    public void applyOverrideConfiguration(Configuration overrideConfiguration) {
        super.applyOverrideConfiguration(overrideConfiguration);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startWithRefresh();
    }
}
