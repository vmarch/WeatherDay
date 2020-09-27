package devtolife.weatherday;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import devtolife.weatherday.fragments.DataFragment;
import devtolife.weatherday.menu_activities.AboutAppActivity;
import devtolife.weatherday.menu_activities.PrivacyPolicy;
import devtolife.weatherday.menu_activities.SettingsActivity;

public class WeatherActivity extends AppCompatActivity {
    EditText editCity;
    Button btnCheckWeather;
    MyPreference myPreference;
    private String city;
    String newCity;
    DataFragment wf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        wf = (DataFragment) getSupportFragmentManager()
                .findFragmentById(R.id.data_fragment);

        myPreference = new MyPreference(this);

        city = myPreference.getCity();

        if (city.equals("")) {
            myPreference.setCity("Kiev");
        }


        editCity = findViewById(R.id.edit_city);
        editCity.setInputType(InputType.TYPE_CLASS_TEXT);

        btnCheckWeather = findViewById(R.id.btn_check_weather);
        btnCheckWeather.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                newCity = getNewCity();

                if (!newCity.equals("")) {
                    changeCity(newCity);
                }
            }
        });


        editCity.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                newCity = getNewCity();
                if (!newCity.equals("")) {

                    if (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                        changeCity(newCity);
                        return true;
                    } else if (!newCity.equals("") && actionId == EditorInfo.IME_ACTION_GO) {
                        changeCity(newCity);
                        return true;
                    } else if (!newCity.equals("") && actionId == EditorInfo.IME_ACTION_DONE) {
                        changeCity(newCity);
                        return true;
                    }

                }
                return false;
            }
        });
    }

    public void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(
                Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    public String getNewCity() {
        return editCity.getText().toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.weather_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;

        if (item.getItemId() == R.id.action_policy) {
            intent = new Intent(this, PrivacyPolicy.class);
            startActivity(intent);
            return true;

        } else if (item.getItemId() == R.id.action_about_app) {
            intent = new Intent(this, AboutAppActivity.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.action_settings) {
            intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void changeCity(String city) {
        editCity.setText("");
        hideSoftKeyboard();
        wf.updateWeatherData(city);
    }
}
