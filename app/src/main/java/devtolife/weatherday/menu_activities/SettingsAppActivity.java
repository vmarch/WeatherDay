package devtolife.weatherday.menu_activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Locale;

import devtolife.weatherday.BuildConfig;
import devtolife.weatherday.LocaleHelper;
import devtolife.weatherday.R;

public class SettingsAppActivity extends AppCompatActivity {

    public final String MY_LANGUAGE_SP_KEY = "MY_LANGUAGE_SP_KEY";
    public final String MY_CURRENT_LANGUAGE_KEY = "MY_CURRENT_LANGUAGE_KEY";
    public final String MY_FIRST_LANGUAGE_KEY = "MY_FIRST_LANGUAGE_KEY";
    private SharedPreferences mSharedPref;

    Context context;

    private Button btnEng, btnDeu, btnUkr, btnRus, btnDef;
    private TextView textViewHeaderLanguage, textViewHeaderName;
    private Toolbar myToolbar;
    Resources resources;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        mSharedPref = PreferenceManager.getDefaultSharedPreferences(this);
//        setTheme(mSharedPref.getInt("mytheme", 0));

        context = getApplicationContext();
        mSharedPref = getSharedPreferences(MY_LANGUAGE_SP_KEY, Context.MODE_PRIVATE);

        if (mSharedPref.getString(MY_FIRST_LANGUAGE_KEY, "").equals("")) {
            System.out.println("MY_FIRST_LANGUAGE_KEY == null");
            mSharedPref.edit().putString(MY_FIRST_LANGUAGE_KEY, Locale.getDefault().getLanguage()).apply();
            System.out.println("MY_FIRST_LANGUAGE_KEY == " + mSharedPref.getString(MY_FIRST_LANGUAGE_KEY, ""));
        }

        setResourcesLocale();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_app);

        myToolbar = findViewById(R.id.my_toolbar_settings);
        setupToolbar();


        System.out.println("1-st e is:" + LocaleHelper.getLanguage(this));
        System.out.println("1-st isDef:" + Locale.getDefault());
        System.out.println("1-st isL:" + Locale.getDefault().getLanguage());
        System.out.println("1-st is:" + Locale.getDefault().getDisplayLanguage());
        System.out.println("1-st isc:" + Locale.getDefault().getCountry());
        System.out.println("1-st isdc:" + Locale.getDefault().getDisplayCountry());
        System.out.println("1-st isn:" + Locale.getDefault().getDisplayName() + "\n");


        textViewHeaderName = findViewById(R.id.textViewLanguageName);
        textViewHeaderName.setText(resources.getString(R.string.text_view_field_language));

        textViewHeaderLanguage = findViewById(R.id.textViewLanguageText);

        btnEng = findViewById(R.id.buttonEng);
        btnDeu = findViewById(R.id.buttonDeu);
        btnUkr = findViewById(R.id.buttonUkr);
        btnRus = findViewById(R.id.buttonRus);
        btnDef = findViewById(R.id.buttonDefaultLanguage);

        changeBtnCondition();
        changeHeaderText();
        //TODO hiding of layoutLanguageBox ;

        btnEng.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                changeLanguage("en");
            }
        });

        btnDeu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                changeLanguage("de");
            }
        });

        btnUkr.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                changeLanguage("uk");
            }
        });

        btnRus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                changeLanguage("ru");
            }
        });

        btnDef.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                changeLanguage(mSharedPref.getString(MY_FIRST_LANGUAGE_KEY, ""));
            }
        });

        System.out.println("language is:" + LocaleHelper.getLanguage(this));
        System.out.println("lokale isDef:" + Locale.getDefault());
        System.out.println("lokale isL:" + Locale.getDefault().getLanguage());
        System.out.println("lokale is:" + Locale.getDefault().getDisplayLanguage());
        System.out.println("lokale isc:" + Locale.getDefault().getCountry());
        System.out.println("lokale isdc:" + Locale.getDefault().getDisplayCountry());
        System.out.println("lokale isn:" + Locale.getDefault().getDisplayName());

    }

    private void changeLanguage(String myLocale) {
        if (myLocale.equals("")) {
            mSharedPref.edit().putString(MY_CURRENT_LANGUAGE_KEY, mSharedPref.getString(MY_FIRST_LANGUAGE_KEY, "")).apply();
        } else {
            mSharedPref.edit().putString(MY_CURRENT_LANGUAGE_KEY, myLocale).apply();
        }

        setResourcesLocale();
        changeBtnCondition();
        changeHeaderText();
    }

    private void changeBtnCondition() {
        switch (mSharedPref.getString(MY_CURRENT_LANGUAGE_KEY, "")) {
            case "en":
                btnEng.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                btnDeu.setBackgroundColor(getResources().getColor(R.color.colorGray));
                btnUkr.setBackgroundColor(getResources().getColor(R.color.colorGray));
                btnRus.setBackgroundColor(getResources().getColor(R.color.colorGray));
                btnDef.setBackgroundColor(getResources().getColor(R.color.colorGray));
                break;

            case "de":
                btnEng.setBackgroundColor(getResources().getColor(R.color.colorGray));
                btnDeu.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                btnUkr.setBackgroundColor(getResources().getColor(R.color.colorGray));
                btnRus.setBackgroundColor(getResources().getColor(R.color.colorGray));
                btnDef.setBackgroundColor(getResources().getColor(R.color.colorGray));
                break;

            case "uk":
                btnEng.setBackgroundColor(getResources().getColor(R.color.colorGray));
                btnDeu.setBackgroundColor(getResources().getColor(R.color.colorGray));
                btnUkr.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                btnRus.setBackgroundColor(getResources().getColor(R.color.colorGray));
                btnDef.setBackgroundColor(getResources().getColor(R.color.colorGray));
                break;

            case "ru":
                btnEng.setBackgroundColor(getResources().getColor(R.color.colorGray));
                btnDeu.setBackgroundColor(getResources().getColor(R.color.colorGray));
                btnUkr.setBackgroundColor(getResources().getColor(R.color.colorGray));
                btnRus.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                btnDef.setBackgroundColor(getResources().getColor(R.color.colorGray));
                break;

            case "":
                btnEng.setBackgroundColor(getResources().getColor(R.color.colorGray));
                btnDeu.setBackgroundColor(getResources().getColor(R.color.colorGray));
                btnUkr.setBackgroundColor(getResources().getColor(R.color.colorGray));
                btnRus.setBackgroundColor(getResources().getColor(R.color.colorGray));
                btnDef.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                break;
        }
    }

    private void changeHeaderText() {
        if (mSharedPref.getString(MY_CURRENT_LANGUAGE_KEY, "").equals("")) {
            textViewHeaderLanguage.setText(mSharedPref.getString(MY_FIRST_LANGUAGE_KEY, ""));
        } else {
            textViewHeaderLanguage.setText(LocaleHelper.getLanguage(this));
        }
        setupToolbar();
        textViewHeaderName.setText(resources.getString(R.string.text_view_field_language));
    }

    private void setResourcesLocale() {

        if (!mSharedPref.getString(MY_CURRENT_LANGUAGE_KEY, "").equals("")) {
            context = LocaleHelper.setLocale(this, mSharedPref.getString(MY_CURRENT_LANGUAGE_KEY, ""));
        } else {
            context = LocaleHelper.setLocale(this, mSharedPref.getString(MY_FIRST_LANGUAGE_KEY, ""));
        }

        resources = context.getResources();

//        LocaleHelper.setLocale(SettingsAppActivity.this, mSharedPref.getString(MY_CURRENT_LANGUAGE_KEY, ""));
//        //Пересоздаем Активити с новым языком.
//        recreate();
    }

    private void setupToolbar() {
        setSupportActionBar(myToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            myToolbar.setNavigationIcon(R.drawable.baseline_arrow_back_black_24);
            myToolbar.setNavigationContentDescription(resources.getString(R.string.context_description_arrow_back));
            myToolbar.setTitle(resources.getString(R.string.title_activity_settings));
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
